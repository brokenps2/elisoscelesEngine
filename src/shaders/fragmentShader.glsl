#version 330 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in float visibility;

precision mediump float;

out vec4 outColor;

uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform vec3 skyColor;

void main()
{
    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDot1 = dot(unitNormal, unitLightVector);
    float brightness = max(nDot1, 0.2);
    vec3 diffuse = brightness * lightColor;

    //outColor = vec4(diffuse,1.0) * texture(textureSampler, pass_textureCoords);
    //outColor = mix(vec4(skyColor,1.0), outColor, visibility);

    //per vertex lighting
    outColor = mix(vec4(skyColor,1.0), vec4(diffuse,1.0) * texture(textureSampler, pass_textureCoords), visibility);
}
