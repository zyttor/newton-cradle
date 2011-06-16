void main()
{
	gl_FragColor[0] = (gl_FragCoord[0] / 200.0 + gl_Color[0])/2;
	gl_FragColor[1] = (gl_FragCoord[1] / 200.0 + gl_Color[1])/2;
	gl_FragColor[2] = gl_Color[2];
}

