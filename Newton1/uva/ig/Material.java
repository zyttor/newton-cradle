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


    private static float ambiente1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float difusa1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float especular1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float brillo1 = 100.0f;

    private static float ambiente2[] = {0.9f,0.9f,0.8f,1.0f};
    private static float difusa2[] = {0.85f,0.85f,0.85f,1.0f};
    private static float especular2[] = {0.96f,0.96f,0.85f,1.0f};
    private static float brillo2 = 20.0f;

    IMaterialListener materialListener;

    public Material() {
    }

    public void setMaterial (int mat){
        if (mat==SUAVE){
            materialListener=new Material1Listener(this);
        }else if (mat==VIVO){
            materialListener=new Material2Listener(this);
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

}
