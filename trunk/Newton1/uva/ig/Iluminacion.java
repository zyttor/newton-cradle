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
    private static float posL0[] ={-10.0f,0.0f,10.0f,1.0f};

    public static float rotacion =0f;

    public static float modoRotacion=0f; //Si 0 no se mueve si 1 si
//
//    private static float ambienteL1[] = {0.7f,0.7f,0.0f,1.0f };
//    private static float diffusaL1[] = {0.7f,0.7f,0.0f,1.0f };
//    private static float especularL1[] = {0.7f,0.7f,0.0f,1.0f };
    private static float posL1[] ={100.0f,-100f,100.0f,1.0f};

    //azul
    private static float ambienteL1[] = {0.3f,0.3f,1f,1.0f };
    private static float diffusaL1[] = {0.3f,0.3f,1f,1.0f };
    private static float especularL1[] = {0.3f,0.3f,1f,1.0f };

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


        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, posL0, 0);
//


        // firefly lights
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT1, GL.GL_CONSTANT_ATTENUATION,5f);
        gl.glLightf(GL.GL_LIGHT1, GL.GL_LINEAR_ATTENUATION,2f);
        gl.glLightf(GL.GL_LIGHT1, GL.GL_QUADRATIC_ATTENUATION,0.3f);

        gl.glLightfv(gl.GL_LIGHT2, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT2, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT2, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT2, GL.GL_CONSTANT_ATTENUATION,5f);
        gl.glLightf(GL.GL_LIGHT2, GL.GL_LINEAR_ATTENUATION,2f);
        gl.glLightf(GL.GL_LIGHT2, GL.GL_QUADRATIC_ATTENUATION,0.3f);

        gl.glLightfv(gl.GL_LIGHT3, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT3, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT3, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT3, GL.GL_CONSTANT_ATTENUATION,5f);
        gl.glLightf(GL.GL_LIGHT3, GL.GL_LINEAR_ATTENUATION,2f);
        gl.glLightf(GL.GL_LIGHT3, GL.GL_QUADRATIC_ATTENUATION,0.3f);

        gl.glLightfv(gl.GL_LIGHT4, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT4, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT4, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT4, GL.GL_CONSTANT_ATTENUATION,5f);
        gl.glLightf(GL.GL_LIGHT4, GL.GL_LINEAR_ATTENUATION,2f);
        gl.glLightf(GL.GL_LIGHT4, GL.GL_QUADRATIC_ATTENUATION,0.3f);

        gl.glLightfv(gl.GL_LIGHT5, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT5, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT5, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT5, GL.GL_CONSTANT_ATTENUATION,5f);
        gl.glLightf(GL.GL_LIGHT5, GL.GL_LINEAR_ATTENUATION,2f);
        gl.glLightf(GL.GL_LIGHT5, GL.GL_QUADRATIC_ATTENUATION,0.3f);

        gl.glLightfv(gl.GL_LIGHT6, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT6, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT6, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT6, GL.GL_CONSTANT_ATTENUATION,0.5f);
        gl.glLightf(GL.GL_LIGHT6, GL.GL_LINEAR_ATTENUATION,0.5f);
        gl.glLightf(GL.GL_LIGHT6, GL.GL_QUADRATIC_ATTENUATION,2f);

        gl.glLightfv(gl.GL_LIGHT7, gl.GL_AMBIENT, ambienteL1, 0);
        gl.glLightfv(gl.GL_LIGHT7, gl.GL_DIFFUSE, diffusaL1, 0);
        gl.glLightfv(gl.GL_LIGHT7, gl.GL_SPECULAR, especularL1, 0);
        gl.glLightf(GL.GL_LIGHT7, GL.GL_CONSTANT_ATTENUATION,0.5f);
        gl.glLightf(GL.GL_LIGHT7, GL.GL_LINEAR_ATTENUATION,0.5f);
        gl.glLightf(GL.GL_LIGHT7, GL.GL_QUADRATIC_ATTENUATION,0.5f);


        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posL1, 0);
        gl.glLightfv(gl.GL_LIGHT2, gl.GL_POSITION, posL1, 0);
        gl.glLightfv(gl.GL_LIGHT3, gl.GL_POSITION, posL1, 0);
        gl.glLightfv(gl.GL_LIGHT4, gl.GL_POSITION, posL1, 0);
        gl.glLightfv(gl.GL_LIGHT5, gl.GL_POSITION, posL1, 0);
        gl.glLightfv(gl.GL_LIGHT6, gl.GL_POSITION, posL1, 0);
        gl.glLightfv(gl.GL_LIGHT7, gl.GL_POSITION, posL1, 0);







        gl.glLightModelf(gl.GL_LIGHT_MODEL_LOCAL_VIEWER, gl.GL_TRUE);
    }

    public void moverLuz(GL gl){
        gl.glPushMatrix();
        gl.glRotatef(rotacion*modoRotacion,0f,1f,1f);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, posL0, 0);
        gl.glPopMatrix();
    }

}
