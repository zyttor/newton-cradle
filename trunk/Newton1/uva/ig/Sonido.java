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

    public static final int SONIDO_SIN_SONIDO=-1;
    public static final int SONIDO_DEFECTO=0;
    public static final int SONIDO_METAL=1;
    public static final int SONIDO_VACA=2;
    public static final int SONIDO_TOON=3;
    public static final int SONIDO_LUCIERNAGA=4;
    public static final int SONIDO_TRANSLUCIDO=5;

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
        if ((t==SONIDO_DEFECTO) || (t==SONIDO_TOON)|| (t==SONIDO_LUCIERNAGA)|| (t==SONIDO_TRANSLUCIDO)){
            rutaSonido="src/uva/ig/default.wav";
        }else if (t==SONIDO_METAL){
            rutaSonido="src/uva/ig/metal.wav";
        }else if (t==SONIDO_VACA){
            rutaSonido="src/uva/ig/cow.wav";
        }else if (t==SONIDO_SIN_SONIDO){
            rutaSonido="";
        }
        modo=t;
    }

    public int getModo() {
        return modo;
    }

}
