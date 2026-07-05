#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform DynamicColorConfig {
    float Time;
    float Intensity;
    int Enabled;
};

const vec3 Gray = vec3(0.3, 0.59, 0.11);

out vec4 fragColor;

vec3 rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
    if (Enabled == 1) {
        vec4 InTexel = texture(InSampler, texCoord);
        float Luma = dot(InTexel.rgb, Gray);
        vec3 Chroma = InTexel.rgb - Luma;
        vec3 OutColor = Chroma + Luma;

        float hueStrength = Intensity;

        vec3 hsv = rgb2hsv(OutColor);

        hsv.x += Time * 0.0015;
        hsv.x = fract(hsv.x);

        vec3 hueShiftedColor = hsv2rgb(hsv);

        OutColor = mix(OutColor, hueShiftedColor, hueStrength);

        fragColor = vec4(OutColor, 1.0);
    } else {
        fragColor = texture(InSampler, texCoord);
    }
}