/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

/**
 *
 * @author cld
 */

import javax.media.opengl.GL;

public interface IObjetoGL {

    public void dibujar (GL gl);

    public void cambiarPosicion (float x, float y, float z);

    public void cambiarGiro (float gx, float  gy, float gz);

    public void cambiarEscalado (float x, float y, float z);

}
