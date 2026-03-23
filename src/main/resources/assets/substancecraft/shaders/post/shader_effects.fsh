#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform Config {
    int colorEnhancementEnabled;
    int colorResolutionEnabled;
    int dynamicColorEnabled;
    int mosaicEnabled;
    float saturation;
    float resolution;
    float time;
    float hueIntensity;
    float mosaicSize;
};

out vec4 fragColor;

const vec3 gray = vec3(0.3, 0.59, 0.11);
const float saturationConst = 0.95;

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

vec4 applyColorSaturation(vec4 value) {
    vec2 oneTexel = 1.0 / InSize;
    float RedValue = dot(value.rgb, vec3(1.0, 0.0, 0.0));
    float GreenValue = dot(value.rgb, vec3(0.0, 1.0, 0.0));
    float BlueValue = dot(value.rgb, vec3(0.0, 0.0, 1.0));
    vec3 OutColor = vec3(RedValue, GreenValue, BlueValue);
    float Luma = dot(OutColor, gray);
    vec3 Chroma = OutColor - Luma;
    OutColor = (Chroma * saturation) + Luma;
    return vec4(OutColor, 1.0);
}

vec4 applyResolution(vec4 value) {
    vec3 quantized = floor(value.rgb * resolution) / resolution;
    float luma = dot(quantized, vec3(0.3, 0.59, 0.11));
    vec3 chroma = (quantized - luma) * saturationConst;
    return vec4(luma + chroma, 1.0);
}

vec4 applyDynamicColor(vec4 value) {
    float Luma = dot(value.rgb, gray);
    vec3 Chroma = value.rgb - Luma;
    vec3 OutColor = Chroma + Luma;
    float hueStrength = hueIntensity;
    vec3 hsv = rgb2hsv(OutColor);
    hsv.x += time * 0.0015;
    hsv.x = fract(hsv.x);
    vec3 hueShiftedColor = hsv2rgb(hsv);
    OutColor = mix(OutColor, hueShiftedColor, hueStrength);
    return vec4(OutColor, 1.0);
}

vec4 applyMosaic() {
    vec2 pixelCoord = texCoord * InSize;
    vec2 mosaicPixel = floor(pixelCoord / mosaicSize) * mosaicSize + mosaicSize * 0.5;
    vec2 mosaicUV = mosaicPixel / InSize;
    return texture(InSampler, mosaicUV);
}

void main() {
    vec4 value = texture(InSampler, texCoord);
    if (mosaicEnabled == 1) {
        value = applyMosaic();
    }
    if (colorEnhancementEnabled == 1) {
       value = applyColorSaturation(value);
    }
    if (colorResolutionEnabled == 1) {
        value = applyResolution(value);
    }
    if (dynamicColorEnabled == 1) {
        value = applyDynamicColor(value);
    }
    fragColor = value;
}