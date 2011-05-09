package uva.ig;

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

    GLU glu;

    Iluminacion iluminacion;
    Camara camara;

    Limon limones[];

    Material material;


    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        glu = new GLU();

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

        gl.glEnable(gl.GL_DEPTH_TEST);

        iluminacion= new Iluminacion();
        iluminacion.setIluminacion(gl);

        camara= new Camara();
        camara.cambiarPuntoOjo(0.0, 0.0, -20.0);

        limones=new Limon[4];
        for (int i=0;i<4;i++){
            limones[i]=new Limon(1.0, 25, 20);
            limones[i].cambiarPosicion(i*3.0f, 0.0f, -2.0f);
        }

        material= new Material();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
//        gl.glMatrixMode(GL.GL_MODELVIEW);
//        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"


        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        camara.setCamara(glu);
        gl.glColor3d(1.0, 0.0, 0.0);

        gl.glDisable(GL.GL_BLEND);

        for (int i=0;i<4;i++){
            if (i==0){
                material.setMaterial(Material.SUAVE);
                material.ponerMaterial(gl);
            }else if (i==3){
                material.setMaterial(Material.VIVO);
                material.ponerMaterial(gl);
            }
            limones[i].dibujar(gl);
        }

        gl.glEnable(GL.GL_BLEND);
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

