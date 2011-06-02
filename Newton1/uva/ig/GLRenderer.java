package uva.ig;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    public static final int MODO_SIN_TEXTURA=0;
    public static final int MODO_VACA=1;
    public static final int MODO_QUARZO=2;

    GLUT glut;
    GLU glu;
    GL gl;
    private int modoActual=0;

    Iluminacion iluminacion;
    Camara camara;

    Bola bolasInicio[];
    Bola bolasDemas[];


    Material material;
    Movimiento movimiento;
    TexturaBola texturaBola;


    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        gl = drawable.getGL();
        glu = new GLU();
        glut=new GLUT();
        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

        gl.glEnable(gl.GL_DEPTH_TEST);

        iluminacion= new Iluminacion();
        iluminacion.setIluminacion(gl);

        camara= new Camara();


        bolasInicio=new Bola[5];
        for (int i=0;i<bolasInicio.length;i++){
            bolasInicio[i]=new Bola(0.5f, 20, 20);
            bolasInicio[i].cambiarPosicion(i*3.0f, 0.0f, -2.0f);
        }
//        bolasDemas=new Bola[4];
//        for (int i=0;i<bolasDemas.length;i++){
//            bolasDemas[i]=new Bola(0.5f, 20, 20);
//            bolasDemas[i].cambiarPosicion(-(i+1)*3.0f, 0.0f, -2.0f);
//        }

        material= new Material();
        //movimiento= new Movimiento(bolasInicio,bolasDemas);
        movimiento= new Movimiento(bolasInicio,1);

        movimiento.setMovimiento(Movimiento.MOVIMIENTO_CUADRATICO);

        texturaBola=new TexturaBola();
        texturaBola.cambiarTextura(gl, TexturaBola.TEXTURA_SIN_TEXTURA);

    }

    public void setTodasMovimiento(){
        int modo=movimiento.getModo();
        int mueven=bolasInicio.length;
        movimiento=new Movimiento(bolasInicio, mueven);
        movimiento.setMovimiento(modo);
    }

    public void cambiarAnguloMax(int a){
        movimiento.setMaxAngulo(a);
    }

    public void cambiarMovimiento(int cual){
        if (cual==1){
            movimiento.setMovimiento(Movimiento.MOVIMIENTO_VERTICAL);
        }else if (cual==2){
            movimiento.setMovimiento(Movimiento.MOVIMIENTO_CUADRATICO);
        }
    }

    public void moverCamara (int donde){
        //1 iz
        if (donde==1){
            camara.moverGiroH(2.0f);
        }else if (donde==2){
            camara.moverGiroH(-2.0f);
        }else if (donde==3){
            camara.moverGiroV(2.0f);
        }else if (donde==4){
            camara.moverGiroV(-2.0f);
        }else if (donde==5){
            camara.cambiarZoom(0.5f);
        }else if (donde==6){
            camara.cambiarZoom(-0.5f);
        }
    }

    public void restartCamara (){
        camara=new Camara();
    }

    public void pararMovimiento() {
        restartCamara();
        movimiento.setMovimiento(Movimiento.QUIETO);
    }

    public void cambiarNumeroBolas (int bolas){
        //TODO: Por ahora pone en movimiento auto con un cierto numero de bolas
        //pero deberia parar y entrar en modo "drag"
        restartCamara();
        bolasInicio=new Bola[bolas];
        for (int i=0;i<bolasInicio.length;i++){
            bolasInicio[i]=new Bola(0.5f, 20, 20);
            bolasInicio[i].cambiarPosicion(i*3.0f, 0.0f, -2.0f);
        }
        int modo=movimiento.getModo();
        int mueven=0;
        if (bolas%2==0){
            mueven=((int)Math.floor(bolas/2))-1;
        }else{
            mueven=(int)Math.floor(bolas/2);
        }
        movimiento=new Movimiento(bolasInicio, mueven);
        movimiento.setMovimiento(modo);
    }

    public void cambiarModo(GLAutoDrawable panel,int modo){
        if (modo==MODO_VACA){
            texturaBola.cambiarTextura(gl, TexturaBola.TEXTURA_VACA);
        }else if (modo==MODO_QUARZO){
            texturaBola.cambiarTextura(gl, TexturaBola.TEXTURA_PRUEBA);
        }else if (modo==MODO_SIN_TEXTURA){
            texturaBola.cambiarTextura(gl, TexturaBola.TEXTURA_SIN_TEXTURA);
        }
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
        glu.gluPerspective(45.0f, h, 1.0, 20.0);

        texturaBola=new TexturaBola();
        int modo=texturaBola.getModo();
        texturaBola.cambiarTextura(gl, modo);
//        gl.glMatrixMode(GL.GL_MODELVIEW);
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

        camara.setCamara(glu,gl);
        gl.glColor3d(1.0, 0.0, 0.0);

        gl.glDisable(GL.GL_BLEND);

        texturaBola.setTextura(gl);

        gl.glPushMatrix();

        gl.glTranslatef(0.0f, 3.375f, 0.0f);
        
//        for (int i=0;i<bolasDemas.length;i++){
//            material.setMaterial(Material.SUAVE);
//            material.ponerMaterial(gl);
//            bolasDemas[i].dibujar(gl);
//        }
        material.setMaterial(Material.VIVO);
        material.ponerMaterial(gl);
        for (int i=0;i<bolasInicio.length;i++){
            bolasInicio[i].dibujar(gl);
        }

        gl.glPopMatrix();

        gl.glEnable(GL.GL_BLEND);
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }


}

