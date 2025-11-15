#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform Config {
    float MosaicSize;
};

out vec4 fragColor;

void main() {
    vec2 pixelCoord = texCoord * InSize;

    vec2 mosaicPixel = floor(pixelCoord / MosaicSize) * MosaicSize
    + MosaicSize * 0.5;

    vec2 mosaicUV = mosaicPixel / InSize;

    fragColor = texture(InSampler, mosaicUV);
}