/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

/**
 *
 * @author cld
 */

import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;

public class Bola implements IObjetoGL{

    public float diametro=1.0f;

    public float traslacion[]={0.0f,0.0f,0.0f};
    public float giro[]={0.0f,0.0f,0.0f};
    public float escalado[]={1.0f,1.0f,1.0f};

    private float radio=0.5f;
    private int slices=10;
    private int stacks=10;

    private GLUT glut;


    public Bola() {
        glut = new GLUT();
    }

    public Bola(float radio, int slices, int stacks) {
        glut = new GLUT();
        this.radio=radio;
        this.diametro=radio*2;
        this.slices=slices;
        this.stacks=stacks;
    }

    public void dibujar (GL gl){
        gl.glPushMatrix();
        gl.glRotatef(giro[2], 0.0f, 0.0f, 1.0f);
        gl.glTranslatef(traslacion[0], traslacion[1], traslacion[2]);

        gl.glPushMatrix();
        glut.glutSolidSphere(radio,slices,stacks);
        gl.glPopMatrix();

        //pintar hilos

        gl.glPopMatrix();
    }

    public void cambiarPosicion (float x, float y, float z){
        traslacion[0]=x;
        traslacion[1]=y;
        traslacion[2]=z;
    }

    public void cambiarGiro (float gx, float gy, float gz){
        giro[0]=gx;
        giro[1]=gy;
        giro[2]=gz;
    }

    public void cambiarEscalado (float x, float y, float z){
        escalado[0]=x;
        escalado[1]=y;
        escalado[2]=z;
    }

}
