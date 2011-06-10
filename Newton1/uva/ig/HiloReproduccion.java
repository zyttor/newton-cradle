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
   private ReproducirWav reproducir;

   public HiloReproduccion (String mifichero) { // Constructor

       fichero = mifichero;
   }
   
   @Override
   public void run() { // Sobrecargando al método run

        reproducir = ReproducirWav.getInstancia();

        reproducir.ReproducirFicheroWav(fichero);

   }

   @Override
   public void destroy() {
       reproducir.getSonido().close();
   }

}
