/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uva.ig;

public class Movimiento {

     
    private float angulomax = 50;       //Angulo maximo
    private float incrementoAngulo = 3.0f;  //Incremento del angulo en cada pasada.Marca velocidad.
    private float angulo;  //Angulo de la bola
    private boolean sentidoHorario = false;     //para controlar el sentido en que se mueven las bolas
    public static final int MOVIMIENTO_VERTICAL = 1;
    public static final int MOVIMIENTO_DIAGONAL = 2;
    private Bola bolasInicio[];
    private Bola bolasDemas[];
    private int numMovimiento;

    //public Movimiento(Bola inicio[], Bola demas[]) {
    public Movimiento(Bola inicio[], int movimiento) {
        bolasInicio = inicio;
        numMovimiento=movimiento;
        //bolasDemas = demas;
    }
    private IMovimientoListener movimiento;

    public void setMovimiento(int mov) {
        if (mov == MOVIMIENTO_VERTICAL) {
            movimiento = new MovimientoVerticalListener(bolasInicio, numMovimiento);
        }
    }

    public void mover() {
        movimiento.manejarEventoMovimiento();
    }

    private interface IMovimientoListener {

        public void manejarEventoMovimiento();
    }

    private class MovimientoVerticalListener implements IMovimientoListener {

        private Bola bolasInicio[];
        private Bola bolasDemas[];
        private float ro=0.017453292519943295f;
        private float largoHilo=2.8125f;
        private int numBolasTotal;
        private int numBolasMovimiento;
        private int numBolasDemas;
        private int i;

        //public MovimientoVerticalListener(Bola inicio[], Bola demas[]) {
        public MovimientoVerticalListener(Bola inicio[],int movimiento) {
            bolasInicio = inicio;
            //bolasDemas = demas;
            numBolasMovimiento = movimiento;
            //numBolasDemas = demas.length;
            //numBolasTotal = numBolasMovimiento + numBolasDemas;
            numBolasTotal=inicio.length;

//            for (i=0;i<bolasInicio.length;i++){
//              //  bolasInicio[i].hilo[0]=-numBolasTotal/2.0f-bolasInicio[i].diametro/2.0f+i*bolasInicio[i].diametro;
//              //  bolasInicio[i].hilo[1]=largoHilo;
//            }
            angulo=angulomax;  
        }

        public void manejarEventoMovimiento() {
            
            calcularAnguloActual();

            for (i=0;i<numBolasTotal;i++){
                bolasInicio[i].traslacion[0]=-numBolasTotal/2.0f-bolasInicio[i].diametro/2.0f+(i+1)*bolasInicio[i].diametro;
                bolasInicio[i].traslacion[1]=-largoHilo;
                if (i<numMovimiento && angulo<0){
                    bolasInicio[i].giro[2]=angulo;
                }else if (i>=numBolasTotal-numMovimiento && angulo>0){
                    bolasInicio[i].giro[2]=angulo;
                }else{
                    bolasInicio[i].giro[2]=0.0f;
                }
                //=(angulo<0 && (i>=numBolasDemas && angulo>0))?angulo:0.0f;
                //bolasInicio[i].giro[2]=0.0f;
                bolasInicio[i].hilo[0]= Double.valueOf(Math.sin(angulo*ro)*largoHilo).floatValue();
                bolasInicio[i].hilo[1]= Double.valueOf(Math.cos(angulo*ro)*largoHilo).floatValue();
            }

//            for (i=0;i<numBolasDemas;i++){
//                bolasDemas[i].traslacion[0]=-numBolasTotal/2.0f-bolasDemas[i].diametro/2.0f+(i+numBolasMovimiento)*bolasDemas[i].diametro;
//                bolasDemas[i].traslacion[1]=-largoHilo;
//                bolasDemas[i].giro[2]=((i+numBolasMovimiento)>=numBolasDemas && angulo>0)?angulo:0.0f;
//                //bolasDemas[i].giro[2]=0.0f;
//                bolasDemas[i].hilo[0]= Double.valueOf(Math.sin(angulo*ro)*largoHilo).floatValue();
//                bolasDemas[i].hilo[1]= Double.valueOf(Math.cos(angulo*ro)*largoHilo).floatValue();
//            }
        }
        /**
         * Calculamos el angulo actual teniendo en cuenta el sentido de la bola,y el angulo que tiene
         * con respecto al angulo máximo.
         */
        private void calcularAnguloActual() {
            //TODO:introducir un coeficiente de fricción para que vaya parando la bola.
            if (sentidoHorario && angulo <= -angulomax) {
                sentidoHorario = false;
            } else if (!sentidoHorario && angulo >= angulomax) {
                sentidoHorario = true;
            }
            if (sentidoHorario) {
                angulo -= incrementoAngulo;
            } else {
                angulo += incrementoAngulo;
            }
        }
    }
}


