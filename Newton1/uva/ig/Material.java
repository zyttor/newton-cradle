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
public class Material {

    public static final int SUAVE=1;
    public static final int VIVO=2;
    public static final int TRANSPARENTE=3;


    private static float ambiente1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float difusa1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float especular1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float brillo1 = 100.0f;

    private static float ambiente2[] = {0.9f,0.9f,0.8f,1.0f};
    private static float difusa2[] = {0.85f,0.85f,0.85f,1.0f};
    private static float especular2[] = {0.96f,0.96f,0.85f,1.0f};
    private static float brillo2 = 20.0f;

    private static float ambienteT[] = {1.0f,1.0f,1.0f,0.6f};
    private static float difusaT[] = {0.45f,0.50f,0.50f,0.6f};
    private static float especularT[] = {0.96f,0.20f,0.60f,0.7f};
    private static float brilloT = 100.0f;

    IMaterialListener materialListener;

    public Material() {
    }

    public void setMaterial (int mat){
        if (mat==SUAVE){
            materialListener=new Material1Listener(this);
        }else if (mat==VIVO){
            materialListener=new Material2Listener(this);
        }else if (mat==TRANSPARENTE){
            materialListener=new MaterialTransparenteListener(this);
        }
    }

    public void ponerMaterial (GL gl){
        materialListener.manejarEventoMaterial(gl);
    }


    private interface IMaterialListener{
        public void manejarEventoMaterial(GL gl);
    }

    private class Material1Listener implements IMaterialListener{
        private Material material;

        public Material1Listener (Material m){
            material=m;
        }

        public void manejarEventoMaterial(GL gl) {
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, material.ambiente1,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, material.difusa1,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, material.especular1,0);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, material.brillo1);
        }
    }

    private class Material2Listener implements IMaterialListener{
        private Material material;

        public Material2Listener (Material m){
            material=m;
        }

        public void manejarEventoMaterial(GL gl) {
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, material.ambiente2,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, material.difusa2,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, material.especular2,0);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, material.brillo2);
        }

    }

    private class MaterialTransparenteListener implements IMaterialListener{
        private Material material;

        public MaterialTransparenteListener (Material m){
            material=m;
        }

        public void manejarEventoMaterial(GL gl) {
            gl.glEnable (gl.GL_BLEND);
            gl.glBlendFunc (gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
            gl.glDepthFunc(gl.GL_LESS);
            gl.glAlphaFunc(GL.GL_GREATER, 0.5f);
            gl.glEnable(GL.GL_ALPHA_TEST);
            //gl.glDisable(GL.GL_DEPTH_TEST);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, material.ambienteT,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_DIFFUSE, material.difusaT,0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_SPECULAR, material.especularT,0);
            gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, material.brilloT);
        }

    }

}
