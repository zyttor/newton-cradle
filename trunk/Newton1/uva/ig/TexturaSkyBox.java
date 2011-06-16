/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.GL;


public class TexturaSkyBox {

    public static final int SKYBOX_MONTANA=1;
    public static final int SKYBOX_MARIO=2;

     private Texture texturaFront,texturaRight,texturaBack,texturaLeft,texturaTop,texturaBottom;

    private int modo;

    public TexturaSkyBox (){}

    private ITexturaSkyBoxListener textura;

    public void cambiarTextura(GL gl, int t){
        if (t==SKYBOX_MARIO){
            textura=new TexturaMarioListener(gl);
        }else if (t==SKYBOX_MONTANA){
            textura=new TexturaMontanaListener(gl);
        }
        modo=t;
    }

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

    public int getModo(){
        return modo;
    }

    public void desactivarTextura(GL gl){
        gl.glDisable(GL.GL_TEXTURE_2D);
    }

    public void renderTextura(GL gl){
        textura.manejarEventoTextura(gl);
    }

    public void activarFront(GL gl){
        texturaFront.bind();
    }

     public void activarBack(GL gl){
         texturaBack.bind();
    }

     public void activarLeft(GL gl){
         texturaLeft.bind();
    }

     public void activarRight(GL gl){
         texturaRight.bind();
    }

     public void activarTop(GL gl){
         texturaTop.bind();
    }

     public void activarBottom(GL gl){
         texturaBottom.bind();
    }

    private interface ITexturaSkyBoxListener{
        public void manejarEventoTextura(GL gl);
        public void cargarTexturas();
    }

    private class TexturaMarioListener implements ITexturaSkyBoxListener{

        


        private TexturaMarioListener (GL gl){
       texturaFront=getTexture("front.png");
       texturaRight=getTexture("right.png");
       texturaBack=getTexture("back.png");
       texturaLeft=getTexture("left.png");
       texturaTop=getTexture("top.png");
       texturaBottom=getTexture("bottom1.png");
        }
        
        public void cargarTexturas()
      {

       texturaFront=getTexture("front.png");
       texturaRight=getTexture("right.png");
       texturaBack=getTexture("back.png");
       texturaLeft=getTexture("left.png");
       texturaTop=getTexture("top.png");
       texturaBottom=getTexture("bottom1.png");



      }  // end of loadTextures()

        public void manejarEventoTextura(GL gl) {


    gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);
    gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);

    gl.glEnable(gl.GL_TEXTURE_GEN_S);
    gl.glEnable(gl.GL_TEXTURE_GEN_T);


    gl.glEnable(gl.GL_TEXTURE_2D);
    gl.glDisable(gl.GL_TEXTURE_GEN_S);
    gl.glDisable(gl.GL_TEXTURE_GEN_T);


    gl.glTexEnvi(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE ,gl.GL_MODULATE);

    gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP);
    gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP);

        }

    }

    private class TexturaMontanaListener implements ITexturaSkyBoxListener{

        public TexturaMontanaListener (GL gl){

           texturaFront=getTexture("Left.bmp");
           texturaRight=getTexture("Front.bmp");
           texturaBack=getTexture("Right.bmp");
           texturaLeft=getTexture("Back.bmp");
           texturaTop=getTexture("Top.bmp");
           texturaBottom=getTexture("Bottom.bmp");

        }

        public void manejarEventoTextura(GL gl) {


        gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);
        gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_EYE_LINEAR);

        gl.glEnable(gl.GL_TEXTURE_GEN_S);
        gl.glEnable(gl.GL_TEXTURE_GEN_T);


        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glDisable(gl.GL_TEXTURE_GEN_S);
        gl.glDisable(gl.GL_TEXTURE_GEN_T);


        gl.glTexEnvi(gl.GL_TEXTURE_ENV,gl.GL_TEXTURE_ENV_MODE ,gl.GL_MODULATE);

        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP);

        }

         public void cargarTexturas()
      {

       texturaFront=getTexture("Front.bmp");
       texturaRight=getTexture("Right.bmp");
       texturaBack=getTexture("Back.bmp");
       texturaLeft=getTexture("Left.bmp");
       texturaTop=getTexture("Top.bmp");
       texturaBottom=getTexture("Bottom.bmp");



      }  // end of loadTextures()

    }


}
