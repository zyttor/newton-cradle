/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

/**
 *
 * @author cld
 */
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GL;


public class Camara {

    private static final double MAX_ZOOM=-16.0;
    private static final double MIN_ZOOM=-6.0;

    private double zoom=-13.0;
    private float giroH = 0.0f;
    private float giroV = 0.0f;

    public Camara(){
        
    }
    
    
    public void setCamara(GLU glu, GL gl){
        gl.glTranslated(0.0, -1.0, zoom);
        gl.glRotatef(giroV, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(giroH, 0.0f, 1.0f, 0.0f);
        
    }


     public void moverGiroH(float a){
         giroH+=a;
         if (giroH<0){
             giroH+=360;
         }
         if (giroH>360){
             giroH-=360;
         }
     }
     public void moverGiroV(float a){
         giroV+=a;
         if (giroV>90){
            giroV=90;
         }
         if (giroV<-90){
             giroV=-90;
         }
     }

     public void cambiarZoom (float z){
         zoom+=z;
         if (zoom>MIN_ZOOM){
             zoom=MIN_ZOOM;
         }
         if (zoom<MAX_ZOOM){
             zoom=MAX_ZOOM;
         }
     }
}
