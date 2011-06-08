/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




package uva.ig;

// ------- LIBRERIAS INCLUIDAS POR MANU ---------
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 *
 * @author Cotri
 */
public class ReproducirWav {

     private static ReproducirWav instancia = null;

     // Private constructor suppresses
    private ReproducirWav() {}

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private synchronized static void crearInstancia() {
        if (instancia == null) {
            instancia = new ReproducirWav();
        }
    }

    public static ReproducirWav getInstancia() {
        if (instancia == null) crearInstancia();
        return instancia;
    }

    public void ReproducirFicheroWav(String ficheroSonido){

    try {

            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();

            // Se carga con un fichero wav

            sonido.open(AudioSystem.getAudioInputStream(new File(ficheroSonido)));

            // Comienza la reproducci�n
            sonido.start();

            // Espera mientras se est� reproduciendo.
            do {
                Thread.sleep(0);
            } while (sonido.isRunning());


            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }


    }

    public void ReproducirFicheroWav(String respuesta,int numero){

    try {

            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();

            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir")+"/sonidos/"+respuesta+"/"+respuesta+numero+".wav")));
            //sonido.open(AudioSystem.getAudioInputStream(new File("build/classes/Logica/Wav/WavCreado.wav")));

            // Comienza la reproducción
            sonido.start();

            // Espera mientras se esté reproduciendo.
            while (sonido.isRunning())
                Thread.sleep(1000);


            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }


    }

}
