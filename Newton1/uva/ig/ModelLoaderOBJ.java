package uva.ig;


import java.io.*;
import java.io.IOException;

import javax.media.opengl.*;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

/**
 * Image loading class that converts BufferedImages into a data
 * structure that can be easily passed to OpenGL.
 * @author Pepijn Van Eeckhoudt
 */


// Uses the class GLModel from JautOGL to load and display obj files.
public class ModelLoaderOBJ {

//  protected TextureManager texture_manager;
  protected GLModel model1;
  protected GLModel model2;
  protected GLModel model3;

    private static float ambiente1[] =  {0.5f,0.5f,0.5f,1.0f};
    private static float difusa1[] =    {0.8f,0.7f,0.7f,1.0f};
    private static float especular1[] = {0.8f,0.7f,0.7f,1.0f};
    private static float brillo1 = 125.0f;
    private static float mat_emiss[] = {0f, 0f, 0f, 0.0f};

    private static float ambiente2[] = {0.65f,0.65f,0.65f,1.0f};
    private static float difusa2[] = {0.55f,0.55f,0.65f,1.0f};
    private static float especular2[] = {0.55f,0.55f,0.65f,1.0f};
    private static float brillo2 = 51.2f;


    private static float ambiente3[] = {0.7f,0.7f,0.7f,1.0f};
    private static float difusa3[] = {0.36f,0.36f,0.45f,1.0f};
    private static float especular3[] = {0.39f,0.39f,0.45f,1.0f};


   private Texture TMesa;
   private Texture TCraddle1;
   private Texture TCraddle2;

  // Load some models.
  public void init( GL gl, String objFile, String objFile2, String objFile3 ) {

    System.out.println("OBJ Loading...");


     // Load  texture.
        try {
            InputStream stream = getClass().getResourceAsStream("wood2.jpg");
            TextureData data = TextureIO.newTextureData(stream, true, "jpg");
            TMesa = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

        //textura 2

        try {
            InputStream stream = getClass().getResourceAsStream("rock.jpg");
            TextureData data = TextureIO.newTextureData(stream, true, "jpg");
            TCraddle1 = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

    //textura 3

        try {
            InputStream stream = getClass().getResourceAsStream("water.jpg");
            TextureData data = TextureIO.newTextureData(stream, true, "jpg");
            TCraddle2 = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }



//       texture_manager = TextureManager.getInstance( gl, glu );
       gl.glEnable( GL.GL_TEXTURE_2D );
       gl.glShadeModel( GL.GL_SMOOTH );

       //Paths of the 3D models.
       //String path1 = "models/formula.obj";
       //String path1 = "models/heli.obj";
       String path1 = "files/"+objFile+".obj";
       String path2 = "files/"+objFile2+".obj";
       String path3 = "files/"+objFile3+".obj";

   //here the needed textures for the race-ground are loaded
       try{
//               texture_manager.createManagedTexture
//                       ("road", "flame.jpg",
//                        GL.GL_TEXTURE_2D, GL.GL_RGB, GL.GL_RGB, GL.GL_LINEAR,
//                        GL.GL_LINEAR, true, true );
                       // ...
                       //in the same way we load each texture is needed...
                       //...

               //a file input stream reads the data and stores them in 
               //a buffer reader for each 3D model
               FileInputStream r_path1 = new FileInputStream(path1);
               BufferedReader b_read1 =
                    new BufferedReader(new InputStreamReader(r_path1));
               model1 = new GLModel(b_read1, true, "files/"+objFile+".mtl", gl);
               r_path1.close();
               b_read1.close();

                FileInputStream r_path2 = new FileInputStream(path2);
                BufferedReader b_read2 =
                     new BufferedReader(new InputStreamReader(r_path2));
                model2 = new GLModel(b_read2, true,"files/"+objFile2+".mtl", gl);
                r_path2.close();
                b_read2.close();

                FileInputStream r_path3 = new FileInputStream(path3);
                BufferedReader b_read3 =
                     new BufferedReader(new InputStreamReader(r_path3));
                model3 = new GLModel(b_read3, true,"files/"+objFile3+".mtl", gl);
                r_path3.close();
                b_read3.close();
       }
       catch( Exception e ){
               System.out.println("LOADING ERROR" +  e);
       }

       System.out.println("ModelLoaderOBJ init() done"); //ddd
   }


  public void draw( GL gl ) 
    {

            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, ambiente1,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, difusa1,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, especular1,0);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, brillo1);
            gl.glMaterialfv( GL.GL_FRONT_AND_BACK, GL.GL_EMISSION, mat_emiss,0);

 // Enable 2D Texture Mapping
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE) ;

       gl.glEnable(gl.GL_TEXTURE_GEN_S);
       gl.glEnable(gl.GL_TEXTURE_GEN_T);

        // Apply texture.
        TMesa.enable();
        TMesa.bind();

        gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_OBJECT_LINEAR);
        gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_OBJECT_LINEAR);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);


           gl.glPushMatrix();


            gl.glTranslatef(0, -2, 0);

//                        gl.glScalef(0.150F, 0.150F, 0.150F); //TOO BIG
           model1.opengldraw(gl);

           gl.glPopMatrix();

           TMesa.disable();
           gl.glDisable(GL.GL_TEXTURE_2D);



           
           // THE TABLE*************************************************************
           gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, ambiente3,0);
           gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, difusa3,0);
           gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, especular3,0);
           gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, 0f);

            gl.glEnable(GL.GL_TEXTURE_2D);
            gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE) ;

            gl.glEnable(gl.GL_TEXTURE_GEN_S);
            gl.glEnable(gl.GL_TEXTURE_GEN_T);

        TCraddle1.enable();
        TCraddle1.bind();

        gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_OBJECT_LINEAR);
        gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_OBJECT_LINEAR);
//            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
//            gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);

            gl.glPushMatrix();
            gl.glScalef(0.15F, 0.15F, 0.15F); //TOO BIG
            gl.glTranslated(0, -72, 0);

            //draws the model
            gl.glMatrixMode(gl.GL_TEXTURE);
            gl.glLoadIdentity();

            gl.glScalef(0.007F, 0.007F, 0.07F);
            
            model2.opengldraw(gl);
            gl.glLoadIdentity();
            gl.glMatrixMode(gl.GL_MODELVIEW);
            TCraddle1.disable();
            gl.glDisable(GL.GL_TEXTURE_2D);

            gl.glPopMatrix();
  

            // THE COLUMNS*************************************************************


            gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, ambiente2,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, difusa2,0);
            gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, especular2,0);
            gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, brillo2);


            gl.glEnable(GL.GL_TEXTURE_2D);
            gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_MODULATE) ;

            gl.glEnable(gl.GL_TEXTURE_GEN_S);
            gl.glEnable(gl.GL_TEXTURE_GEN_T);

        TCraddle2.enable();
        TCraddle2.bind();

        gl.glTexGeni(gl.GL_S, gl.GL_TEXTURE_GEN_MODE, gl.GL_OBJECT_LINEAR);
        gl.glTexGeni(gl.GL_T, gl.GL_TEXTURE_GEN_MODE, gl.GL_OBJECT_LINEAR);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP);
        gl.glTexParameteri(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP);


        // TOP CYLINDERS**********************
            gl.glPushMatrix();

            gl.glTranslated(0, 3.5, 2.0);
            gl.glRotatef(90, 1, 90, 1);
            gl.glScalef(0.001F, 0.001F, 0.008F); //TOO BIG
            model3.opengldraw(gl);

            gl.glPopMatrix();

             gl.glPushMatrix();

            gl.glTranslated(0, 3.5, -2.0);
            gl.glRotatef(90, 1, 90, 1);
            gl.glScalef(0.001F, 0.001F, 0.008F); //TOO BIG
            model3.opengldraw(gl);

            gl.glPopMatrix();


            // END TOP CYLINDER*********************



            gl.glPushMatrix();

            gl.glTranslated(3.2, 0.9, 2.0);
            gl.glRotatef(90, 90, -1, 1);
            gl.glScalef(0.001F, 0.001F, 0.006F);
            model3.opengldraw(gl);

            gl.glPopMatrix();

            gl.glPushMatrix();

            gl.glTranslated(-3.2, 0.9, -2.0);
            gl.glRotatef(90, 90, -1, 1);
            gl.glScalef(0.001F, 0.001F, 0.006F);
            model3.opengldraw(gl);

            gl.glPopMatrix();

            gl.glPushMatrix();

            gl.glTranslated(3.2, 0.9, -2.0);
            gl.glRotatef(90, 90, -1, 1);
            gl.glScalef(0.001F, 0.001F, 0.006F);
            model3.opengldraw(gl);

            gl.glPopMatrix();

             gl.glPushMatrix();

            gl.glTranslated(-3.2, 0.9, 2.0);
            gl.glRotatef(90, 90, -1, 1);
            gl.glScalef(0.001F, 0.001F, 0.006F);
            model3.opengldraw(gl);

            gl.glPopMatrix();


            TCraddle2.disable();
            gl.glDisable(GL.GL_TEXTURE_2D);
    }
}
