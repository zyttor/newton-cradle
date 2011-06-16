attribute float wave;

void main() {
    vec4 vertex = gl_Vertex;
    vertex.y += sin(wave)/16;
    gl_Position = gl_ModelViewProjectionMatrix * vertex;

    vec4 ambient, diffuse, specular;
    
    vec4 eyePosition = gl_ModelViewMatrix * gl_Vertex;
    vec4 eyeLightPos = gl_LightSource[0].position;
    vec3 N = normalize(gl_NormalMatrix * gl_Normal);
    vec3 L = normalize(eyeLightPos.xyz - eyePosition.xyz);
    vec3 E = -normalize(eyePosition.xyz);
    vec3 H = normalize(L + E);

    float Kd = max(dot(L, N), 0.0);
    float Ks = pow(max(dot(N, H), 0.0), gl_FrontMaterial.shininess);
    float Ka = 0.0;
    ambient = Ka*gl_FrontLightProduct[0].ambient;
    diffuse = Kd*gl_FrontLightProduct[0].diffuse;
    specular = Ks*gl_FrontLightProduct[0].specular;

    gl_FrontColor = ambient+diffuse+specular;

}
