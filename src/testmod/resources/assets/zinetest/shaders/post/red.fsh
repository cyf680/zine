#version 150

uniform sampler2D InSampler;

in vec2 texCoord;

out vec4 fragColor;

void main(){
    vec4 diffuseColor = texture(InSampler, texCoord);
    fragColor = vec4(diffuseColor.r, 0.0, 0.0, 1.0);
}