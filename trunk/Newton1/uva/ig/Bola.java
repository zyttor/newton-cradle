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
    public float hilo[]={1.0f,1.0f,2.125f};

    private float anchoCubo = 0.125f;

    private float radio=0.5f;
    private int slices=10;
    private int stacks=10;

    private GLUT glut;

    private static Shader shader;
    private static TexturaBola textura;


    private float largoHilo=2.8125f;

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

    public static void setShader (Shader s){
        shader=s;
    }

    public static void setTextura (TexturaBola t){
        textura=t;
    }

    public void dibujar (GL gl){
        gl.glPushMatrix();
        gl.glTranslatef(traslacion[0], 0.0f, 0.0f);
        gl.glPushMatrix();

        gl.glRotatef(giro[2], 0.0f, 0.0f, 1.0f);
        gl.glTranslatef(0.0f, traslacion[1], 0.0f);

        glut.glutSolidCube(anchoCubo);

        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -radio, 0.0f);
        textura.renderTextura(gl);
        shader.iniciarShaders(gl);
        glut.glutSolidSphere(radio,slices,stacks);
        shader.pararShaders(gl);
        textura.desactivarTextura(gl);
        gl.glPopMatrix();

        gl.glRotatef(-giro[2], 0.0f, 0.0f, 1.0f);

        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        //Hacia tubo posterior
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        if (giro[2]==0.0f){
            gl.glVertex3f(0.0f, largoHilo, -hilo[2]);
        }else{
            gl.glVertex3f(-hilo[0], hilo[1], -hilo[2]);
        }
        

        //Hacia tubo frontal
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        if (giro[2]==0.0f){
            gl.glVertex3f(0.0f, largoHilo, hilo[2]);
        }else{
            gl.glVertex3f(-hilo[0], hilo[1], hilo[2]);
        }
        gl.glEnd();

        gl.glPopMatrix();

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
