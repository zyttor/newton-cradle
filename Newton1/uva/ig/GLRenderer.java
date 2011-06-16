package uva.ig;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.Color;

/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    public static final int SIN_SONIDO = -1;
    public static final int MODO_SIN_TEXTURA = 0;
    public static final int MODO_VACA = 1;
    public static final int MODO_METAL = 2;
    public static final int MODO_TRANSPARENTE = 3;
    public static final int MODO_TOON = 4;
    public static final int TEXTURA_MONTANA=1;
    public static final int TEXTURA_MARIO=2;
    private int modosSonido[] = {Sonido.SONIDO_DEFECTO, Sonido.SONIDO_VACA, Sonido.SONIDO_METAL, Sonido.SONIDO_TRANSLUCIDO, Sonido.SONIDO_TOON};
    private int modosTextura[] = {TexturaBola.TEXTURA_SIN_TEXTURA, TexturaBola.TEXTURA_VACA, TexturaBola.TEXTURA_PRUEBA, TexturaBola.TEXTURA_SIN_TEXTURA, TexturaBola.TEXTURA_SIN_TEXTURA};
    private int modosShader[] = {Shader.SIN_SHADER, Shader.SIN_SHADER, Shader.SIN_SHADER, Shader.SIN_SHADER, Shader.SHADER_NUBES};
    private int modosMaterial[] = {Material.VIVO, Material.VIVO, Material.VIVO,  Material.TRANSPARENTE, Material.VIVO};
    GLUT glut;
    GLU glu;
    GL gl;
    private int modoActual = 0;
    private boolean modoArrastre = true;
    private boolean rozamiento = true;
    private int modoMovimiento = 2;
    Iluminacion iluminacion;
    Camara camara;
    Bola bolasInicio[];
    Bola bolasDemas[];
    SkyBox skyBox;
    TexturaSkyBox texturaSkyBox;
    Texto texto;
    Material material;
    Movimiento movimiento;
    TexturaBola texturaBola;
    Sonido sonido;
    Shader shader;
    private ModelLoaderOBJ obj_file = new ModelLoaderOBJ();


    /*************************************************************
     * Movimiento de la bola
     *
     */

    public void cambiarMovimiento(int cual) {
        if (cual == 1) {
            movimiento.setMovimiento(Movimiento.MOVIMIENTO_LINEAL);
        } else if (cual == 2) {
            movimiento.setMovimiento(Movimiento.MOVIMIENTO_CUADRATICO);
        }
        modoActual=cual;
        cambiarRozamiento(rozamiento);
    }

    public void cambiarRozamiento(boolean activado) {
        if (activado) {
            movimiento.setRozamiento(1.5f);
        } else {
            movimiento.setRozamiento(0.0f);
        }
        rozamiento = activado;
    }

    public void setTodasMovimiento() {
        int mueven = bolasInicio.length;
        movimiento = new Movimiento(bolasInicio, mueven,this);
        movimiento.setMovimiento(modoMovimiento);
    }

    public void cambiarAnguloMax(int a) {
        movimiento.setMaxAngulo(a);
    }

    /*****************************************************************
     * Movimiento de la cámara
     *
     */

    public void moverCamara(int donde) {
        //1 iz
        if (!modoArrastre) {
            if (donde == 1) {
                camara.moverGiroH(2.0f);
            } else if (donde == 2) {
                camara.moverGiroH(-2.0f);
            } else if (donde == 3) {
                camara.moverGiroV(2.0f);
            } else if (donde == 4) {
                camara.moverGiroV(-2.0f);
            } else if (donde == 5) {
                camara.cambiarZoom(0.5f);
            } else if (donde == 6) {
                camara.cambiarZoom(-0.5f);
            }
        }
    }

    /********************************************************
     * Reinicios
     */

    public void restartCamara() {
        camara = new Camara();
    }

    public void pararMovimiento() {
        restartCamara();
        movimiento.setMovimiento(Movimiento.QUIETO);
        modoArrastre = true;
    }

    /********************************************************
     * Arrastre de la bola
     *
     */

    public boolean ponerArrastre(int numBolas, boolean derecha) {
        if (modoArrastre) {
            movimiento = new Movimiento(bolasInicio, numBolas,this);
            movimiento.setSentidoHorario(derecha);
            movimiento.setMaxAngulo(0.0f);
            movimiento.setMovimiento(Movimiento.ARRASTRE);
            return true;
        }
        return false;
    }

    public void arrastrarBola(float angulo) {
        if (modoArrastre) {
            movimiento.setMaxAngulo(angulo);
        }
    }

    public void lanzarBola() {
        if (modoArrastre) {
            movimiento.setMovimiento(modoMovimiento);
            if (movimiento.getMaxAngulo() < 0.0f) {
                movimiento.setMaxAngulo(-movimiento.getMaxAngulo());
            }
            modoArrastre = false;
        }
        cambiarRozamiento(rozamiento);
    }

    /*********************************************************************
     * Cambiar de modo visual
     *
     */

    public void cambiarModo(GLAutoDrawable panel, int modo) {
        shader.cambiarShader(modosShader[modo], gl);
        texturaBola.cambiarTextura(gl, modosTextura[modo]);
        Bola.setTextura(texturaBola);
        Bola.setShader(shader);
        sonido.cambiarSonido(modosSonido[modo]);
        material.setMaterial(modosMaterial[modo]);
        modoActual = modo;
    }


    public void cambiarSkyBox(GLAutoDrawable panel,int modo){
        if (modo==TEXTURA_MONTANA){
           texturaSkyBox.cambiarTextura(gl, TexturaSkyBox.SKYBOX_MONTANA);
            SkyBox.setTextura(texturaSkyBox);
        }else if (modo==TEXTURA_MARIO){
             texturaSkyBox.cambiarTextura(gl, TexturaSkyBox.SKYBOX_MARIO);
            SkyBox.setTextura(texturaSkyBox);
        }
    }

    /*********************************************************************
     * Numéro de Bolas
     * 
     */

    public void cambiarNumeroBolas(int bolas) {
        //TODO: Por ahora pone en movimiento auto con un cierto numero de bolas
        //pero deberia parar y entrar en modo "drag"
        restartCamara();
        bolasInicio = new Bola[bolas];
        for (int i = 0; i < bolasInicio.length; i++) {
            bolasInicio[i] = new Bola(0.5f, 20, 20);
            bolasInicio[i].cambiarPosicion(i * 3.0f, 0.0f, -2.0f);
        }
        int modo = movimiento.getModo();
        int mueven = 0;
        if (bolas % 2 == 0) {
            mueven = ((int) Math.floor(bolas / 2)) - 1;
        } else {
            mueven = (int) Math.floor(bolas / 2);
        }
        movimiento = new Movimiento(bolasInicio, mueven,this);
        movimiento.setMovimiento(Movimiento.QUIETO);
        modoArrastre=true;
        cambiarRozamiento(rozamiento);
    }

    public int getNumBolas() {
        return bolasInicio.length;
    }


    /**************************************************************+
     * Velocidad
     */

    public float subirVelocidad (){
        movimiento.cambiarVelocidad(-0.2f);
        comprobarSonido();
        return movimiento.getVelocidad();
    }

    public float bajarVelocidad (){
        movimiento.cambiarVelocidad(0.2f);
        comprobarSonido();
        return movimiento.getVelocidad();
    }

    /******************************************************************
     * Sonido
     */

   public void comprobarSonido(){
        if ((movimiento.getVelocidadDef() != movimiento.getIncrementoAngulo())
                && (sonido.getModo() != sonido.SONIDO_SIN_SONIDO)){
            sonido.cambiarSonido(SIN_SONIDO);
        } else if ((movimiento.getVelocidadDef() == movimiento.getIncrementoAngulo())
                && (sonido.getModo() == sonido.SONIDO_SIN_SONIDO)){
            sonido.cambiarSonido(modosSonido[modoActual]);
        }
    }

    /********************************************************+++
     * Métodos opengl
     *
     */

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

        gl.glEnable(gl.GL_DEPTH_TEST);

        iluminacion = new Iluminacion();
        iluminacion.setIluminacion(gl);

        camara = new Camara();

        skyBox = new SkyBox();

        texturaSkyBox=new TexturaSkyBox();
        texturaSkyBox.cambiarTextura(gl, TexturaSkyBox.SKYBOX_MONTANA);
        SkyBox.setTextura(texturaSkyBox);

        texto=new Texto();


        bolasInicio = new Bola[5];
        for (int i = 0; i < bolasInicio.length; i++) {
            bolasInicio[i] = new Bola(0.5f, 20, 20);
            //bolasInicio[i].cambiarPosicion(i*3.0f, 0.0f, -2.0f);
        }

        material = new Material();
        movimiento = new Movimiento(bolasInicio, 1,this);

        movimiento.setMovimiento(Movimiento.QUIETO);

        texturaBola = new TexturaBola();

        sonido = Sonido.getInstancia();

        shader = new Shader();

        cambiarModo(drawable, modoActual);

        obj_file.init(gl, "cradle", "table2", "Cylinder");
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        //gl=drawable.getGL();
        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 200.0);
        // Para poner perspectiva ortho (eliminar el gluperspective), si quereis que se vea mas o menos pantalla, cambiad las 4 primeras coordenadas
        //gl.glOrtho(-20, 20, -20, 20, 0, 50);
        //gl.glFrustum(-10, 10, -10, 10, -10, 10);
        texturaBola = new TexturaBola();
        int modo = texturaBola.getModo();
        texturaBola.cambiarTextura(gl, modo);
//        gl.glMatrixMode(GL.GL_MODELV-------IEW);
//        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        movimiento.mover();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

       // iluminacion.moverLuz(gl);

        camara.setCamara(glu, gl);
        gl.glColor3d(1.0, 0.0, 0.0);

        gl.glDisable(GL.GL_BLEND);

        

        gl.glPushMatrix();
        skyBox.dibujar(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        obj_file.draw(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();

        gl.glTranslatef(0.0f, 3.375f, 0.0f);

        gl.glDisable(GL.GL_BLEND);

        material.ponerMaterial(gl);
        if (camara.getGiroH()>=0&&camara.getGiroH()<=180){
            for (int i = bolasInicio.length-1; i >= 0; i--) {
                bolasInicio[i].dibujar(gl);
            }
            
        }else{
            for (int i = 0; i < bolasInicio.length; i++) {
                bolasInicio[i].dibujar(gl);
            }
        }

        gl.glPopMatrix();

        texto.dibujar(gl, -2.15f, -2.3f,3.06f, "Aldearaban", Color.black);


        gl.glEnable(GL.GL_BLEND);
        // Flush all drawing operations to the graphics card
        gl.glFlush();
        
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
