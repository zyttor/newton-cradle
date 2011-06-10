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


public class TexturaBola {

    public static final int TEXTURA_SIN_TEXTURA=0;
    public static final int TEXTURA_PRUEBA=1;
    public static final int TEXTURA_VACA=2;

    private int modo;

    public TexturaBola (){}

    private ITexturaListener textura;

    public void cambiarTextura(GL gl, int t){
        if (t==TEXTURA_PRUEBA){
            textura=new TexturaPruebaListener(gl);
        }else if (t==TEXTURA_SIN_TEXTURA){
            textura=new TexturaVaciaListener(gl);
        }else if (t==TEXTURA_VACA){
            textura=new TexturaVacaListener(gl);
        }
        modo=t;
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

    private interface ITexturaListener{
        public void manejarEventoTextura(GL gl);
    }

    private class TexturaPruebaListener implements ITexturaListener{

        private Texture textura;
        private float zPlane[]={0.0f, 0.0f, 1.0f, 0.0f};

        public TexturaPruebaListener (GL gl){
            gl.glEnable(GL.GL_TEXTURE_2D);      // Enable 2D Texture Mapping
            try {
                InputStream stream = getClass().getResourceAsStream("metal.jpg");
                TextureData data = TextureIO.newTextureData(stream, false, "jpg");
                textura = TextureIO.newTexture(data);
            }catch (IOException exc) {
                System.out.println("Error al cargar la textura de prueba");
                System.out.println(exc.toString());
                System.exit(1);
            }
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        }

        public void manejarEventoTextura(GL gl) {


            gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_SPHERE_MAP);
            gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_SPHERE_MAP);


            gl.glEnable(gl.GL_TEXTURE_GEN_S);
            gl.glEnable(gl.GL_TEXTURE_GEN_T);

            textura.enable();
            textura.bind();

            gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE) ;

            gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
            gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);

        }

    }

    private class TexturaVacaListener implements ITexturaListener{

        private Texture textura;
        //private float zPlane[]={0.0f, 0.0f, 1.0f, 0.0f};

        public TexturaVacaListener (GL gl){
            gl.glEnable(GL.GL_TEXTURE_2D);      // Enable 2D Texture Mapping
            try {
                InputStream stream = getClass().getResourceAsStream("vaca.jpg");
                TextureData data = TextureIO.newTextureData(stream, false, "jpg");
                textura = TextureIO.newTexture(data);
            }catch (IOException exc) {
                System.out.println("Error al cargar la textura de vaca");
                System.exit(1);
            }
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        }

        public void manejarEventoTextura(GL gl) {


//            gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_SPHERE_MAP);
//            gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_SPHERE_MAP);
//
//
//            gl.glEnable(gl.GL_TEXTURE_GEN_S);
//            gl.glEnable(gl.GL_TEXTURE_GEN_T);

            textura.enable();
            textura.bind();

            gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE) ;

            gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
            gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);

        }

    }

    private class TexturaVaciaListener implements ITexturaListener{


        public TexturaVaciaListener (GL gl){

        }

        public void manejarEventoTextura(GL gl) {
            gl.glDisable(gl.GL_TEXTURE);
        }

    }

}
