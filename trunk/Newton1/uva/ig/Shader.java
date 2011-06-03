/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.media.opengl.GL;

/**
 *
 * @author cld
 */
public class Shader {

    public static final int SIN_SHADER=0;
    public static final int SHADER_NUBES=1;

    private IShaderListener shaderListener;

    public static boolean shaderSoportado (GL gl){
        String extensions = gl.glGetString(GL.GL_EXTENSIONS);
        return extensions.indexOf("GL_ARB_vertex_shader") != -1;
    }

    public Shader() {
    }

    public void cambiarShader (int cual,GL gl){
        if (cual==SIN_SHADER){
            shaderListener=new SinShaderListener(gl);
        }else if (cual==SHADER_NUBES){
            shaderListener=new NubeListener(gl);
        }
    }

    public void iniciarShaders (GL gl){
        shaderListener.iniciarShader(gl);
    }

    public void pararShaders (GL gl){
        shaderListener.pararShader(gl);
    }

    private interface IShaderListener{
        public void iniciarShader(GL gl);
        public void pararShader(GL gl);
    }

    /************************************++
     * Shader vacío
     */

    private class SinShaderListener implements IShaderListener{

        public SinShaderListener (GL gl){
            gl.glUseProgram(0);
        }

        public void iniciarShader(GL gl) {}

        public void pararShader(GL gl) {}
    }

     /************************************++
     * Shader que parece unas nubes
     */

    private class NubeListener implements IShaderListener{
        private int programas;

        private String nombreVertex="v.glsl";
        private String nombreFragment="f.glsl";

        public NubeListener (GL gl){
            String vertexSource=null;
            String fragmentSource=null;
            try {
                vertexSource = leerFicheroAString(nombreVertex);
            } catch (IOException ex) {
                System.err.println("Error al leer el fichero vertex shader "+nombreVertex);
                System.exit(-1);
            }
            try {
                fragmentSource = leerFicheroAString(nombreFragment);
            } catch (IOException ex) {
                System.err.println("Error al leer el fichero fragment shader "+nombreFragment);
                System.exit(-1);
            }

            int vShader = gl.glCreateShaderObjectARB(GL.GL_VERTEX_SHADER_ARB);
            gl.glShaderSourceARB(vShader, 1, new String []{vertexSource}, (int[]) null, 0);
            gl.glCompileShaderARB(vShader);
            gl.glAttachObjectARB(programas, vShader);
            
            int fShader = gl.glCreateShaderObjectARB(GL.GL_FRAGMENT_SHADER_ARB);
            gl.glShaderSourceARB(fShader, 1, new String []{fragmentSource}, (int[]) null, 0);
            gl.glCompileShaderARB(fShader);

            programas = gl.glCreateProgramObjectARB();
            gl.glAttachObjectARB(programas, vShader);
            gl.glAttachObjectARB(programas, fShader);
            gl.glLinkProgramARB(programas);
            gl.glValidateProgramARB(programas);

            gl.glFlush();
        }

        public void iniciarShader(GL gl) {
            gl.glUseProgram(programas);
        }

        public void pararShader(GL gl) {
            gl.glUseProgram(0);
        }

        private String leerFicheroAString (String filePath) throws java.io.IOException{
            byte[] buffer = new byte[(int) new File(filePath).length()];
            BufferedInputStream f = null;
            try {
                f = new BufferedInputStream(new FileInputStream(filePath));
                f.read(buffer);
            } finally {
                if (f != null) try { f.close(); } catch (IOException ignored) { }
            }
        return new String(buffer);
}
    }

}
