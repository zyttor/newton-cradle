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

public class Limon implements IObjetoGL{

    private float traslacion[]={0.0f,0.0f,0.0f};
    private float giro[]={0.0f,0.0f,0.0f};
    private float escalado[]={1.0f,1.0f,1.0f};

    private double radio=1.0;
    private int slices=10;
    private int stacks=10;

    private GLUT glut;


    public Limon() {
        glut = new GLUT();
    }

    public Limon(double radio, int slices, int stacks) {
        glut = new GLUT();
        this.radio=radio;
        this.slices=slices;
        this.stacks=stacks;
    }

    public void dibujar (GL gl){
        gl.glPushMatrix();
        gl.glTranslatef(traslacion[0], traslacion[1], traslacion[2]);
        gl.glRotatef(giro[0], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(giro[1], 1.0f, 0.0f, 0.0f);
        gl.glRotatef(giro[2], 1.0f, 0.0f, 0.0f);
        gl.glScalef(escalado[0], escalado[1], escalado[2]);

        glut.glutSolidSphere(radio,slices,stacks);

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
