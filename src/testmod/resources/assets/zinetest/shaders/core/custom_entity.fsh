#version 150

#moj_import <minecraft:fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform vec2 ScreenSize;
uniform int EntityId;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    #ifdef ALPHA_CUTOUT
    if (color.a < ALPHA_CUTOUT) {
        discard;
    }
    #endif
    color *= vertexColor * ColorModulator;
    #ifndef NO_OVERLAY
    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    #endif
    #ifndef EMISSIVE
    color *= lightMapColor;
    #endif
    float b = (EntityId % 4) / 3.0;
    fragColor = linear_fog(vec4(gl_FragCoord.x / ScreenSize.x, gl_FragCoord.y / ScreenSize.y, b, 1.0), vertexDistance, FogStart, FogEnd, FogColor);
}