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

    private static float luzAmbiente[] = {0.6f,0.6f,0.6f,1.0f};

    private static float ambienteL0[] = {0.9f, 0.8f, 0.8f, 1.0f};
    private static float diffusaL0[] = {0.8f, 0.8f, 0.8f, 1.0f};
    private static float especularL0[] = {0.8f,0.8f,0.8f, 1.0f};
    private static float posL0[] ={-10.0f,0.0f,-10.0f,1.0f};

    public static float rotacion []={0f,0f,0f};

//    private static float ambienteL1[] = {0.2f, 0.2f, 0.2f, 1.0f};
//    private static float diffusaL1[] = {0.1f, 0.1f, 0.1f, 1.0f};
//    private static float especularL1[] = {0.8f,0.8f,0.8f, 1.0f};
//    private static float posL1[] ={0.0f,10.0f,-10.0f,0.0f};

    public Iluminacion (){

    }

    public void setIluminacion (GL gl){
        gl.glEnable(gl.GL_LIGHTING);

        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT,luzAmbiente,0);

        gl.glEnable(gl.GL_LIGHT0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, ambienteL0, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, diffusaL0, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_SPECULAR, especularL0, 0);

//        gl.glEnable(gl.GL_LIGHT1);
//        gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, ambienteL1, 0);
//        gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, diffusaL1, 0);
//        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPECULAR, especularL1, 0);

        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, posL0, 0);
//        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posL1, 0);

        gl.glLightModelf(gl.GL_LIGHT_MODEL_LOCAL_VIEWER, gl.GL_TRUE);
    }

    public void moverLuz(GL gl){
//        gl.glEnable(gl.GL_LIGHTING);
//        gl.glPushMatrix();
//        gl.glRotatef(1.0f,rotacion[0],rotacion[1],rotacion[2]);
//        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, posL0, 0);
//        gl.glPopMatrix();
//        System.out.println(rotacion[0]);
    }

}
