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
    public static final int FIREFLY=6;


    private static float ambiente1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float difusa1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float especular1[] = {0.5f,0.5f,0.5f,1.0f};
    private static float brillo1 = 100.0f;

    private static float ambiente2[] = {0.4f,0.4f,0.35f,1.0f};
    private static float difusa2[] = {0.55f,0.52f,0.51f,1.0f};
    private static float especular2[] = {0.65f,0.65f,0.60f,1.0f};
    private static float brillo2 = 20.0f;

    private static float ambienteT[] = {0.8f,0.8f,0.8f,0.6f};
    private static float difusaT[] = {0.45f,0.50f,0.50f,0.6f};
    private static float especularT[] = {0.96f,0.20f,0.60f,0.7f};
    private static float brilloT = 100.0f;

    // amarillo solar
//    private static float ambienteFF[] = {0.0f,0.0f,1.0f,1.0f};
//    private static float difusaFF[] = {0.0f,0.0f,1.0f,1.0f};
//    private static float especularFF[] = {0.0f,0.0f,1.0f,1.0f};
//    private static float brilloFF = 10f;
//    private static float mat_emiss[] = {1f, 1f, 0f, 0.0f};

     // azul celeste
    private static float ambienteFF[] = {0.1f,0.1f,0.1f,1.0f};
    private static float difusaFF[] = {0.1f,0.1f,0.1f,1.0f};
    private static float especularFF[] = {0.1f,0.1f,0.1f,1.0f};
    private static float brilloFF = 1000f;
    private static float mat_emiss[] = {0f, 0.4f, 1f, 0.0f};

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
        }else if (mat==FIREFLY){
            materialListener=new MaterialFireflyListener(this);
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
            gl.glEnable(gl.GL_LIGHT0);
            gl.glEnable(gl.GL_LIGHT_MODEL_AMBIENT);
            gl.glDisable(gl.GL_LIGHT1);
            gl.glDisable(gl.GL_LIGHT2);
            gl.glDisable(gl.GL_LIGHT3);
            gl.glDisable(gl.GL_LIGHT4);
            gl.glDisable(gl.GL_LIGHT5);
            gl.glDisable(gl.GL_LIGHT6);
            gl.glDisable(gl.GL_LIGHT7);
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
            gl.glEnable(gl.GL_LIGHT0);
            gl.glEnable(gl.GL_LIGHT_MODEL_AMBIENT);
            gl.glDisable(gl.GL_LIGHT1);
            gl.glDisable(gl.GL_LIGHT2);
            gl.glDisable(gl.GL_LIGHT3);
            gl.glDisable(gl.GL_LIGHT4);
            gl.glDisable(gl.GL_LIGHT5);
            gl.glDisable(gl.GL_LIGHT6);
            gl.glDisable(gl.GL_LIGHT7);
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
            gl.glEnable(gl.GL_LIGHT0);
            gl.glEnable(gl.GL_LIGHT_MODEL_AMBIENT);
            gl.glDisable(gl.GL_LIGHT1);
            gl.glDisable(gl.GL_LIGHT2);
            gl.glDisable(gl.GL_LIGHT3);
            gl.glDisable(gl.GL_LIGHT4);
            gl.glDisable(gl.GL_LIGHT5);
            gl.glDisable(gl.GL_LIGHT6);
            gl.glDisable(gl.GL_LIGHT7);
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

    private class MaterialFireflyListener implements IMaterialListener{
        private Material material;

        public MaterialFireflyListener (Material m){
            material=m;
        }

        public void manejarEventoMaterial(GL gl) {

            gl.glDisable(gl.GL_LIGHT0);
            gl.glDisable(gl.GL_LIGHT_MODEL_AMBIENT);


            gl.glEnable(gl.GL_LIGHT1);
            gl.glEnable(gl.GL_LIGHT2);
            gl.glEnable(gl.GL_LIGHT3);
            gl.glEnable(gl.GL_LIGHT4);
            gl.glEnable(gl.GL_LIGHT5);
            gl.glEnable(gl.GL_LIGHT6);
            gl.glEnable(gl.GL_LIGHT7);
            
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, ambienteFF,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, difusaFF,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, especularFF,0);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, brilloFF);
            gl.glMaterialfv( GL.GL_FRONT_AND_BACK, GL.GL_EMISSION, mat_emiss,0);




        }

    }

}
