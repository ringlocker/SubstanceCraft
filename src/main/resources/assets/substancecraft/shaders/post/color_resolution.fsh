#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform Config {
    float Resolution;
};

out vec4 fragColor;

const float Saturation = 0.95;

void main() {
    vec4 baseTexel = texture(InSampler, texCoord);

    vec3 quantized = floor(baseTexel.rgb * Resolution) / Resolution;

    float luma = dot(quantized, vec3(0.3, 0.59, 0.11));
    vec3 chroma = (quantized - luma) * Saturation;

    fragColor = vec4(luma + chroma, 1.0);
}