#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform ColorEnhancementConfig {
   float Saturation;
   int Enabled;
};

const vec3 Gray = vec3(0.3, 0.59, 0.11);

out vec4 fragColor;

void main() {
    if (Enabled == 1) {
        vec2 oneTexel = 1.0 / InSize;
        vec4 InTexel = texture(InSampler, texCoord);

        float RedValue = dot(InTexel.rgb, vec3(1.0, 0.0, 0.0));
        float GreenValue = dot(InTexel.rgb, vec3(0.0, 1.0, 0.0));
        float BlueValue = dot(InTexel.rgb, vec3(0.0, 0.0, 1.0));
        vec3 OutColor = vec3(RedValue, GreenValue, BlueValue);

        float Luma = dot(OutColor, Gray);
        vec3 Chroma = OutColor - Luma;
        OutColor = (Chroma * Saturation) + Luma;

        fragColor = vec4(OutColor, 1.0);
    } else {
        fragColor = texture(InSampler, texCoord);
    }
}
