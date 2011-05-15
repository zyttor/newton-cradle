/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uva.ig;

public class Movimiento {
    public static final int MOVIMIENTO_VERTICAL=1;    
    public static final int MOVIMIENTO_DIAGONAL=2;

    private Limon limones[];

    public Movimiento(Limon lims[]){
        limones=lims;
    }

    private IMovimientoListener movimiento;

    public void setMovimiento(int mov){
        if (mov==MOVIMIENTO_VERTICAL)
            movimiento=new MovimientoVerticalListener(limones);
        else if (mov==MOVIMIENTO_DIAGONAL)
            movimiento=new MovimientoDiagonalListener(limones);
    }

    public void mover(){
        movimiento.manejarEventoMovimiento();
    }

    private interface IMovimientoListener{
        public void manejarEventoMovimiento();
    }

    private class MovimientoVerticalListener implements IMovimientoListener{
        private Limon limones[];
        private int numLimones;
        private int i;
        private int direccion;

        public MovimientoVerticalListener (Limon lims[]){
            limones=lims;
            numLimones=limones.length;
            direccion=1;
        }

        public void manejarEventoMovimiento() {
            if (limones[0].getPosicionY()>=4 || limones[0].getPosicionY()<=-4)
                direccion*=-1;
            for(i=0;i<numLimones;i++){
                limones[i].setPosicionY(limones[i].getPosicionY()+(0.1f*(i+1)*direccion));
            }
        }
    }

    private class MovimientoDiagonalListener implements IMovimientoListener{
        private Limon limones[];
        private int numLimones;
        private int i;
        private int direccion;

        public MovimientoDiagonalListener (Limon lims[]){
            limones=lims;
            numLimones=limones.length;
            direccion=1;
        }

        public void manejarEventoMovimiento() {
            if (limones[0].getPosicionY()>=4 || limones[0].getPosicionY()<=-4)
                direccion*=-1;
            for(i=0;i<numLimones;i++){
                limones[i].setPosicionX(limones[i].getPosicionX()+(0.1f*(i+1)*direccion));
                limones[i].setPosicionY(limones[i].getPosicionY()+(0.1f*(i+1)*direccion));
            }
        }
    }

}
