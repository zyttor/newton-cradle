package uva.ig;

import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;
import java.awt.*;

/**
 *
 * @author Cotri
 */

/**
 * Clase que imprime texto en OpenGL.
 *
 */
public class Texto implements IObjetoGL{

    GLUT glut=new GLUT();

    public Texto(){

    }

    public void dibujar(GL gl){
    }


    /**
     *
     * @param gl el gl que usamos en el display
     * @param x posicion x donde queremos que empiece a pintarse
     * @param y posicion y donde queremos que empiece a pintarse
     * @param text texto a imprimir
     * @param color color del texto
     */
    public void dibujar(GL gl, float x, float y,float z, String text, Color color){
        char c;
        boolean lightingState;
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        float height = glut.glutStrokeLengthf(GLUT.STROKE_ROMAN, "X");
        float factor = 0.5f / height;
        gl.glScaled(factor, factor, 0);
        gl.glTranslatef(x, y, 0);

        lightingState = gl.glIsEnabled(GL.GL_LIGHTING);
        gl.glDisable(GL.GL_LIGHTING);
        gl.glColor3d(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0);

        //nos da el grosor
        gl.glLineWidth(2.0f);

        for (int offset = 0; offset < text.length(); offset++) {
            c = text.charAt(offset);
            glut.glutStrokeCharacter(GLUT.STROKE_ROMAN, c);
        }
        if (lightingState) {
            gl.glEnable(GL.GL_LIGHTING);
        }
        gl.glPopMatrix();
    }

    public void cambiarPosicion (float x, float y, float z){

    }

    public void cambiarGiro (float gx, float gy, float gz){

    }

    public void cambiarEscalado (float x, float y, float z){

    }
}
