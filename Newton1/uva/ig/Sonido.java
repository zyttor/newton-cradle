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
    public static final int SONIDO_VACA=1;
    public static final int SONIDO_METAL=2;
    public static final int SONIDO_TRANSLUCIDO=3;
    public static final int SONIDO_VIBRACION=4;
    public static final int SONIDO_COLORIZE=5;
    public static final int SONIDO_FIREFLY=6;

    //public static final int SONIDO_LUCIERNAGA=4;    

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
//        if ((t==SONIDO_DEFECTO) || (t==SONIDO_LUCIERNAGA)){
        if (t==SONIDO_DEFECTO){
            rutaSonido="src/uva/ig/default.wav";
        }else if (t==SONIDO_METAL){
            rutaSonido="src/uva/ig/metal.wav";
        }else if (t==SONIDO_VACA){
            rutaSonido="src/uva/ig/cow.wav";
        }else if (t==SONIDO_SIN_SONIDO){
            rutaSonido="";
        }else if (t==SONIDO_TRANSLUCIDO){
            rutaSonido="src/uva/ig/Bulle2.wav";
        }else if (t==SONIDO_VIBRACION){
            rutaSonido="src/uva/ig/buzz2.wav";
        }else if (t==SONIDO_COLORIZE){
            rutaSonido="src/uva/ig/whistle.wav";
        }else if (t==SONIDO_FIREFLY){
            rutaSonido="src/uva/ig/cricket.wav";
        }
        modo=t;
    }

    public int getModo() {
        return modo;
    }

    public String getRutaSonido() {
        return rutaSonido;
    }

}
