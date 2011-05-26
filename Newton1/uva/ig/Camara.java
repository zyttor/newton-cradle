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

    private double ojo[]={0.0,0.0,-0.5};
    private double centro[]={0.0,0.0,0.0};
    private double arriba[]={0.0,1.0,0.0};
    private float giroH = 0.0f;
    private float giroV = 0.0f;

    public Camara(){
        
    }
    
    
    public void setCamara(GLU glu, GL gl){
        glu.gluLookAt(ojo[0], ojo[1], ojo[2], centro[0], centro[1], centro[2], arriba[0], arriba[1], arriba[2]);
        gl.glRotatef(giroV, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(giroH, 0.0f, 1.0f, 0.0f);
        
    }
    
    public void cambiarPuntoOjo (double x,double y, double z){
        ojo[0]=x;
        ojo[1]=y;
        ojo[2]=z;
    }

    public void cambiarPuntoCentro (double x,double y, double z){
        centro[0]=x;
        centro[1]=y;
        centro[2]=z;
    }

     public void cambiarPuntoArriba (double x,double y, double z){
        arriba[0]=x;
        arriba[1]=y;
        arriba[2]=z;
    }

     public void moverX(float x){
         ojo[0]+=x;
     }

     public void moverY(float y){
         ojo[1]+=y;
     }

     public void moverZ(float z){
         ojo[2]+=z;
     }

     public void moverGiroH(float a){
         giroH+=a;
     }
     public void moverGiroV(float a){
         giroV+=a;
     }

     public void cambiarZoom (float z){
         ojo[2]+=z;
     }
}
