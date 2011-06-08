/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

/**
 *
 * @author pA
 */
public class Sonido {

    private static Sonido instancia = null;

    public static final int SONIDO_DEFECTO=0;
    public static final int SONIDO_METAL=1;
    public static final int SONIDO_VACA=2;

    private int modo;

    public String rutaSonido;


    public Sonido (){}

    private synchronized static void crearInstancia() {
        if (instancia == null) {
            instancia = new Sonido();
        }
    }

    public static Sonido getInstancia() {
        if (instancia == null) crearInstancia();
        return instancia;
    }

    public void cambiarSonido(int t){
        if (t==SONIDO_DEFECTO){
            rutaSonido="src/uva/ig/default.wav";
        }else if (t==SONIDO_METAL){
            rutaSonido="src/uva/ig/metal.wav";
        }else if (t==SONIDO_VACA){
            rutaSonido="src/uva/ig/cow.wav";
        }
        modo=t;
    }

}
