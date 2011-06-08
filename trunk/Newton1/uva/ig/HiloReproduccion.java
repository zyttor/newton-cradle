/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

/**
 *
 * @author pA
 */

public class HiloReproduccion extends Thread{

   private String fichero;

   public HiloReproduccion (String mifichero) { // Constructor

       fichero = mifichero;
   }
   
   @Override
   public void run() { // Sobrecargando al método run

        ReproducirWav reproducir = ReproducirWav.getInstancia();

        reproducir.ReproducirFicheroWav(fichero);

   }

}
