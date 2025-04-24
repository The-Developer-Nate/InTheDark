#version 150

vec4 linear_fog(vec4 inColor, float vertexDistance, float fogStart, float fogEnd, vec4 fc) {
    if (vertexDistance <= fogStart) {
        return inColor;
    }

    vec4 fogColor = fc;
    float brightness = 0.2989 * inColor.r + 0.5870 * inColor.g + 0.1140 * inColor.b;
    float targetAttenuation = brightness/10; // 1% visibility at fog ending
    float cfogEnd = max(fogEnd, 0.0001);
    float density = -log(targetAttenuation) / cfogEnd;
    float factor = exp(-density * max(vertexDistance, 0.0));

    fogColor = vec4(mix(fogColor.rgb, inColor.rgb, brightness/10), inColor.a);

    factor = clamp(factor, 0.0, 1.0);

    vec3 finalColor = mix(fogColor.rgb, inColor.rgb, factor);

    return vec4(finalColor, inColor.a);
}

float linear_fog_fade(float vertexDistance, float fogStart, float fogEnd) {
    if (vertexDistance <= fogStart) {
        return 1.0;
    } else if (vertexDistance >= fogEnd) {
        return 0.0;
    }

    return smoothstep(fogEnd, fogStart, vertexDistance);
}

float fog_distance(vec3 pos, int shape) {
    if (shape == 0) {
        return length(pos);
    } else {
        float distXZ = length(pos.xz);
        float distY = abs(pos.y);
        return max(distXZ, distY);
    }
}