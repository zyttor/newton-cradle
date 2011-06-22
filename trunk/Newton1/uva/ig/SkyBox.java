/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;



import javax.media.opengl.*;
import com.sun.opengl.util.texture.*;

/**
 *Clase que renderiza el Skybox
 * @author Aldearaban
 */
public class SkyBox implements IObjetoGL{

    public float traslacion[]={0.0f,0.0f,0.0f};
    public float giro[]={0.0f,0.0f,0.0f};
    public float escalado[]={1.0f,1.0f,1.0f};
    public float hilo[]={1.0f,1.0f,2.125f};

   private static TexturaSkyBox textura;

    public SkyBox() {

    }

    public static void setTextura (TexturaSkyBox t){
        textura=t;
    }

    private static float ambienteSB[] = {0.8f,0.8f,0.7f,1.0f};
    private static float difusaSB[] = {0.75f,0.75f,0.75f,1.0f};
    private static float especularSB[] = {0.86f,0.86f,0.85f,1.0f};
    private static float brilloSB = 20.0f;

    public void dibujar (GL gl){
        
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, ambienteSB,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, difusaSB,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, especularSB,0);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, brilloSB);



    textura.renderTextura(gl);

    gl.glTranslatef(0f, 50.75f, 0f);
    gl.glScalef(-70.0f, -70.0f, 70.0f);

    textura.activarFront(gl);
    gl.glBegin(gl.GL_QUADS);
    // Front Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
    gl.glEnd();

    textura.activarBack(gl);
    gl.glBegin(gl.GL_QUADS);
    // Back Face
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
    gl.glEnd();

    textura.activarBottom(gl);
    gl.glBegin(gl.GL_QUADS);
    // Bottom Face
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
    gl.glEnd();

    textura.activarTop(gl);
    gl.glBegin(gl.GL_QUADS);
    // Top Face
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
    gl.glEnd();

    textura.activarRight(gl);
    gl.glBegin(gl.GL_QUADS);
    // Right face
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
    gl.glEnd();

    textura.activarLeft(gl);
    gl.glBegin(gl.GL_QUADS);
    // Left Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
    gl.glEnd();

    textura.desactivarTextura(gl);

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
