/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

import javax.media.opengl.GL;

/**
 *
 * @author cld
 */
public class Iluminacion {

    private static float luzAmbiente[] = {0.3f,0.3f,0.3f,1.0f};

    private static float ambienteL0[] = {0.2f, 0.2f, 0.2f, 1.0f};
    private static float diffusaL0[] = {0.3f, 0.3f, 0.3f, 1.0f};
    private static float especularL0[] = {0.3f,0.3f,0.3f, 1.0f};
    private static float posL0[] ={-10.0f,0.0f,-20.0f,1.0f};

    private static float ambienteL1[] = {0.2f, 0.2f, 0.2f, 1.0f};
    private static float diffusaL1[] = {0.4f, 0.4f, 0.4f, 1.0f};
    private static float especularL1[] = {0.3f,0.3f,0.3f, 1.0f};
    private static float posL1[] ={10.0f,0.0f,-20.0f,0.0f};

    public Iluminacion (){

    }

    public void setIluminacion (GL gl){
        gl.glEnable(gl.GL_LIGHTING);

        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT,luzAmbiente,0);

        gl.glEnable(gl.GL_LIGHT0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambienteL0, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, diffusaL0, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_SPECULAR, especularL0, 0);

        gl.glEnable(gl.GL_LIGHT1);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPECULAR, especularL1, 0);

        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, posL0, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posL1, 0);

        gl.glLightModelf(gl.GL_LIGHT_MODEL_LOCAL_VIEWER, gl.GL_TRUE);
    }

}
