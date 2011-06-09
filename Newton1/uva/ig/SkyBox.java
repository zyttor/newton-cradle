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

   


     // for the floor
  private final static int FLOOR_LEN = 40;  // should be even

     private Texture texturaFront,texturaRight,texturaBack,texturaLeft,texturaTop,texturaBottom;

     
    public SkyBox(GL migl) {
        gl=migl;
        this.loadTextures();
    }

    public void dibujar (GL migl){

       

        
    //gl.glDisable(GL.GL_LIGHTING);

    // enable texturing and choose the 'stars' texture
    //gl.glEnable(GL.GL_TEXTURE_2D);
    //starsTex.bind();
    // rTex.bind();

//    gl.glMatrixMode(GL.GL_TEXTURE);
//    gl.glLoadIdentity();
//    gl.glScalef(0.100f, 0.100f, 1f);
//    gl.glMatrixMode(gl.GL_MODELVIEW);

     gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);
            gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);


            gl.glEnable(gl.GL_TEXTURE_GEN_S);
            gl.glEnable(gl.GL_TEXTURE_GEN_T);

//    TextureCoords tc = texturasSkyBox0.getImageTexCoords();
//    float left = tc.left()*4;
//    float right = tc.right()*4;
//    float bottom = tc.bottom()*4;
//    float top = tc.top()*4;


    gl.glEnable(gl.GL_TEXTURE_2D);
    gl.glDisable(gl.GL_TEXTURE_GEN_S);
    gl.glDisable(gl.GL_TEXTURE_GEN_T);
    //gl.glDisable(gl.GL_DEPTH_TEST);
    //gl.glDisable(gl.GL_LIGHTING);
    //gl.glDisable(gl.GL_BLEND);

//     gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);
//        gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);

//    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
//    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

    gl.glTexEnvi(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE ,gl.GL_DECAL);
//
    gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP);
    gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP);



    int edge = (FLOOR_LEN);

    

    
    
    gl.glTranslatef(0f, 50.75f, 0f);
    gl.glScalef(-70.0f, -70.0f, 70.0f);

    texturaFront.bind();
    gl.glBegin(gl.GL_QUADS);
    // Front Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
    gl.glEnd();

    texturaBack.bind();
    gl.glBegin(gl.GL_QUADS);
    // Back Face
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
    gl.glEnd();

    texturaBottom.bind();
    gl.glBegin(gl.GL_QUADS);
    // Bottom Face
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
    gl.glEnd();

    texturaTop.bind();
    gl.glBegin(gl.GL_QUADS);
    // Top Face
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
    gl.glEnd();

    texturaRight.bind();
    gl.glBegin(gl.GL_QUADS);
    // Right face
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Left Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
    gl.glEnd();

    texturaLeft.bind();
    gl.glBegin(gl.GL_QUADS);
    // Left Face
		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Left Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
    gl.glEnd();
    

//    gl.glBegin(gl.GL_QUADS);
//		// Front Face
//		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Left Of The Texture and Quad
//
//		// Back Face
//		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Bottom Left Of The Texture and Quad
//		// Top Face
//		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
//		// Bottom Face
//		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Top Right Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Top Left Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
//		// Right face
//		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);	// Bottom Right Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);	// Top Right Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);	// Top Left Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);	// Bottom Left Of The Texture and Quad
//		// Left Face
//		gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);	// Bottom Left Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);	// Bottom Right Of The Texture and Quad
//		gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);	// Top Right Of The Texture and Quad
//		gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);	// Top Left Of The Texture and Quad
//	gl.glEnd();

//    gl.glBegin(GL.GL_QUADS);
//      // back wall
//
//      //gl.glTexCoord2f(left, bottom);
//      gl.glVertex3i(-edge, -edge, -edge);
////      gl.glTexCoord2f(right, bottom);
//      gl.glVertex3i(edge, -edge, -edge);
////      gl.glTexCoord2f(right, top);
//      gl.glVertex3i(edge, edge, -edge);
////      gl.glTexCoord2f(left, top);
//      gl.glVertex3i(-edge, edge, -edge);
//    gl.glEnd();
// texturasSkyBox3.bind();



//    gl.glBegin(GL.GL_QUADS);
//      // right wall
//
////      gl.glTexCoord2f(left, bottom);
//      gl.glVertex3i(edge, -edge, -edge);
////      gl.glTexCoord2f(right, bottom);
//      gl.glVertex3i(edge, -edge, edge);
////      gl.glTexCoord2f(right, top);
//      gl.glVertex3i(edge, edge, edge);
////      gl.glTexCoord2f(left, top);
//      gl.glVertex3i(edge, edge, -edge);
//    gl.glEnd();
//texturasSkyBox0.bind();
//    gl.glBegin(GL.GL_QUADS);
//      // front wall
//
////      gl.glTexCoord2f(left, bottom);
//      gl.glVertex3i(edge, -edge, edge);
////      gl.glTexCoord2f(right, bottom);
//      gl.glVertex3i(-edge, -edge, edge);
////      gl.glTexCoord2f(right, top);
//      gl.glVertex3i(-edge, edge, edge);
////      gl.glTexCoord2f(left, top);
//      gl.glVertex3i(edge, edge, edge);
// gl.glEnd();
// texturasSkyBox1.bind();
//      gl.glBegin(GL.GL_QUADS);
//      // left wall
//
////      gl.glTexCoord2f(left, bottom);
//      gl.glVertex3i(-edge, -edge, edge);
////      gl.glTexCoord2f(right, bottom);
//      gl.glVertex3i(-edge, -edge, -edge);
////      gl.glTexCoord2f(right, top);
//      gl.glVertex3i(-edge, edge, -edge);
////      gl.glTexCoord2f(left, top);
//      gl.glVertex3i(-edge, edge, edge);
// gl.glEnd();
//
//      texturasSkyBox4.bind();
//      gl.glBegin(GL.GL_QUADS);
//      // top wall
////      gl.glTexCoord2f(left, bottom);
//      gl.glVertex3i(edge, edge, edge);
////      gl.glTexCoord2f(right, bottom);
//      gl.glVertex3i(-edge, edge, edge);
////      gl.glTexCoord2f(right, 2*top);
//      gl.glVertex3i(-edge, edge, -edge);
////      gl.glTexCoord2f(left, 2*top);
//      gl.glVertex3i(edge, edge, -edge);
//    gl.glEnd();
//
//    texturasSkyBox5.bind();
//    gl.glBegin(GL.GL_QUADS);
//      // bottom wall
////      gl.glTexCoord2f(left, bottom);
//      gl.glVertex3i(edge, -edge, edge);
//////      gl.glTexCoord2f(right, bottom);
//      gl.glVertex3i(-edge, -edge, edge);
////      gl.glTexCoord2f(right, top);
//      gl.glVertex3i(-edge, -edge, -edge);
////      gl.glTexCoord2f(left, top);
//      gl.glVertex3i(edge, -edge, -edge);
//    gl.glEnd();

     
  //gl.glPopMatrix();

   

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


  public void loadTextures()
  {
    texturaFront=getTexture("Front.bmp");
   texturaRight=getTexture("Right.bmp");
   texturaBack=getTexture("Back.bmp");
   texturaLeft=getTexture("Left.bmp");
   texturaTop=getTexture("Top.bmp");
   texturaBottom=getTexture("bottom.bmp");



  }  // end of loadTextures()

  public Texture getTexture(String textureName){
        TextureData data=null;

    try {
            InputStream stream = getClass().getResourceAsStream(textureName);
            data = TextureIO.newTextureData(stream, false, "jpg");

        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    return TextureIO.newTexture(data);
    }

}
