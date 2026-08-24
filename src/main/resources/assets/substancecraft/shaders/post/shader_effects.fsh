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
    int surfaceWarpEnabled;
    int doubleVisionEnabled;
    float saturation;
    float resolution;
    float time;
    float hueIntensity;
    float mosaicSize;
    float warpIntensity;
    float doubleVisionIntensity;
    float doubleVisionDistance;
    float doubleVisionStretch;
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

vec2 applySurfaceWarpUV(vec2 pixelCoord) {
    float intensity = warpIntensity;
    float centeredX = (pixelCoord.x - InSize.x * 0.5) / InSize.x;
    float centeredY = (pixelCoord.y - InSize.y * 0.5) / InSize.y;
    float breath = (sin(time * 0.014) * 0.7 + sin(time * 0.021 + 1.7) * 0.3) * 0.5 + 0.5;
    float bandSize = mix(3.0, 3.65, breath * intensity);
    float bandCoord =pixelCoord.y / bandSize;
    float swell = sin(time * 0.025 + centeredX * 3.0 + centeredY * 1.5);
    float wave = sin(time * 0.060 + bandCoord * 0.16) * 1.05;
    float pulse = 1.0 + 0.08 * sin(time * 0.03);
    float strength = intensity * bandSize;

    wave += sin(time * 0.036 + centeredX * 7.0 + bandCoord * 0.05) * 0.50;
    wave += swell * 0.35;
    wave *= pulse;
    wave =  tanh(wave * 0.9);

    pixelCoord.y += wave * intensity * bandSize;
    pixelCoord.x += wave * intensity * 0.8;

    return pixelCoord;
}

vec2 circles(vec2 pixelCoord) {
    float intensity = warpIntensity;

    vec2 centered = (pixelCoord - InSize * 0.5) / InSize;

    float aspect = InSize.x / InSize.y;
    vec2 radialCoord = centered;
    radialCoord.x *= aspect;

    float dist = length(radialCoord);

    float breath = (sin(time * 0.014) * 0.7
    + sin(time * 0.021 + 1.7) * 0.3) * 0.5 + 0.5;

    float waveSpeed = 0.055;
    float waveFrequency = 22.0;

    float wave = sin(dist * waveFrequency - time * waveSpeed);

    float centerReduction = smoothstep(0.05, 0.18, dist);
    float falloff = centerReduction * (1.0 - smoothstep(0.0, 0.75, dist));

    float pulse = 1.0 + 0.08 * sin(time * 0.03);

    wave *= falloff;
    wave *= pulse;
    wave *= (0.75 + breath * 0.25);

    vec2 direction = radialCoord / max(dist, 0.0001);

    float displacement = wave * intensity * 12.0;

    radialCoord += direction * displacement / InSize.y;

    radialCoord.x /= aspect;

    return radialCoord * InSize + InSize * 0.5;
}

vec2 applyMosaicUV(vec2 pixelCoord) {
    float blockSize = max(1.0, floor(mosaicSize + 0.5));
    vec2 snappedPixel = floor(pixelCoord / blockSize) * blockSize;
    return snappedPixel + 0.5;
}

vec4 applyDoubleVision(vec4 value) {
    vec3 newColor = value.rgb * max(0.2, 1.0 - (0.05 * doubleVisionIntensity));
    newColor += texture(InSampler, vec2(0.5 + (texCoord.s - 0.5) / doubleVisionStretch + doubleVisionDistance, texCoord.t)).rgb * min(0.4, (0.025 * doubleVisionIntensity));
    newColor += texture(InSampler, vec2(0.5 + (texCoord.s - 0.5) / doubleVisionStretch - doubleVisionDistance, texCoord.t)).rgb * min(0.4, (0.025 * doubleVisionIntensity));
    return vec4(mix(value.rgb, newColor, 0.95), value.a);
}

void main() {
    // pixel modifications
    vec2 pixelCoord = texCoord * InSize;
    if (surfaceWarpEnabled == 1) {
        pixelCoord = applySurfaceWarpUV(pixelCoord);
        pixelCoord = circles(pixelCoord);
    }
    if (mosaicEnabled == 1) {
        pixelCoord = applyMosaicUV(pixelCoord);
    } else {
        pixelCoord += 0.5;
    }
    vec2 uv = pixelCoord / InSize;
    uv = clamp(uv, vec2(0.5) / InSize, (InSize - 0.5) / InSize);

    // color modifications
    vec4 value = texture(InSampler, uv);
    if (colorEnhancementEnabled == 1) {
       value = applyColorSaturation(value);
    }
    if (colorResolutionEnabled == 1) {
        value = applyResolution(value);
    }
    if (dynamicColorEnabled == 1) {
        value = applyDynamicColor(value);
    }
    if (doubleVisionEnabled == 1) {
        value = applyDoubleVision(value);
    }

    fragColor = value;
}