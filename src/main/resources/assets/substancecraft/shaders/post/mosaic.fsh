#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BitsConfig {
    float Resolution;
    float MosaicSize;
};

out vec4 fragColor;

const float Saturation = 1.0;

void main() {
    vec2 pixelCoord = texCoord * InSize;

    vec2 mosaicPixel = floor(pixelCoord / MosaicSize) * MosaicSize
    + MosaicSize * 0.5;

    vec2 mosaicUV = mosaicPixel / InSize;

    vec4 baseTexel = texture(InSampler, mosaicUV);

    vec3 fractTexel = floor(baseTexel.rgb * Resolution) / Resolution;

    float luma = dot(fractTexel, vec3(0.3, 0.59, 0.11));
    vec3 chroma = (fractTexel - luma) * Saturation;

    fragColor = vec4(luma + chroma, 1.0);
}