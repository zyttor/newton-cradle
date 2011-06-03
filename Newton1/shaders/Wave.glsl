const vec4 red = vec4(1.0, 0.0, 0.0, 1.0);
void main() {
	vec4 vertex = gl_Vertex;
    gl_Position = gl_ModelViewProjectionMatrix * vertex;
    gl_FrontColor = red;
    gl_BackColor = gl_Color;
}
