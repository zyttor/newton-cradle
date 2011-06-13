/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import java.awt.font.*;
import java.text.*;
import java.awt.geom.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;
import com.sun.opengl.util.texture.*;
import com.sun.opengl.util.j2d.*;

/**
 *
 * @author Cotri
 */
public class SkyBox implements IObjetoGL{

    public float traslacion[]={0.0f,0.0f,0.0f};
    public float giro[]={0.0f,0.0f,0.0f};
    public float escalado[]={1.0f,1.0f,1.0f};
    public float hilo[]={1.0f,1.0f,2.125f};

   private GL gl;

   private static TexturaSkyBox textura;


     private Texture texturaFront,texturaRight,texturaBack,texturaLeft,texturaTop,texturaBottom;


    public SkyBox() {

    }

    public static void setTextura (TexturaSkyBox t){
        textura=t;
    }

    public void dibujar (GL gl){

//     gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);
//            gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);
//
//
//            gl.glEnable(gl.GL_TEXTURE_GEN_S);
//            gl.glEnable(gl.GL_TEXTURE_GEN_T);
//
//
//    gl.glEnable(gl.GL_TEXTURE_2D);
//    gl.glDisable(gl.GL_TEXTURE_GEN_S);
//    gl.glDisable(gl.GL_TEXTURE_GEN_T);
//
//
//    gl.glTexEnvi(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE ,gl.GL_DECAL);
//
//    gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP);
//    gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP);

    textura.renderTextura(gl);

    gl.glTranslatef(0f, 50.75f, 0f);
    gl.glScalef(-70.0f, -70.0f, 70.0f);

    textura.activarFront(gl);
    gl.glBegin(gl.GL_QUADS);
    // Front Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
    gl.glEnd();

    textura.activarBack(gl);
    gl.glBegin(gl.GL_QUADS);
    // Back Face
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
    gl.glEnd();

    textura.activarBottom(gl);
    gl.glBegin(gl.GL_QUADS);
    // Bottom Face
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
    gl.glEnd();

    textura.activarTop(gl);
    gl.glBegin(gl.GL_QUADS);
    // Top Face
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
    gl.glEnd();

    textura.activarRight(gl);
    gl.glBegin(gl.GL_QUADS);
    // Right face
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
    gl.glEnd();

    textura.activarLeft(gl);
    gl.glBegin(gl.GL_QUADS);
    // Left Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
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
