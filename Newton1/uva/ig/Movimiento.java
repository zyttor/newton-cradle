/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uva.ig;

public class Movimiento {

     
    private float angulomax = 70;       //Angulo maximo
    private float rozamiento=0f;
    private float incrementoAngulo = 1.0f;  //Incremento del angulo en cada pasada.Marca velocidad.
    private float incrementoAnguloDef = incrementoAngulo;
    private float angulo;  //Angulo de la bola
    private boolean sentidoHorario = false;     //para controlar el sentido en que se mueven las bolas
    public static final int QUIETO =0;
    public static final int MOVIMIENTO_LINEAL = 1;
    public static final int MOVIMIENTO_CUADRATICO = 2;
    public static final int ARRASTRE = 3;
    private Bola bolasInicio[];
    private int numMovimiento;
    private int modo;

    private Sonido sonido;

    HiloReproduccion miThread;

    //public Movimiento(Bola inicio[], Bola demas[]) {
    public Movimiento(Bola inicio[], int movimiento) {
        bolasInicio = inicio;
        numMovimiento=movimiento;
        //bolasDemas = demas;

        sonido = Sonido.getInstancia();
    }
    private IMovimientoListener movimiento;

    public void setMovimiento(int mov) {
        if (mov == MOVIMIENTO_LINEAL) {
            movimiento = new MovimientoLineal(bolasInicio, numMovimiento);
        }else if (mov==QUIETO){
            movimiento = new MovimientoQuieto(bolasInicio);
        }else if (mov==MOVIMIENTO_CUADRATICO){
            movimiento = new MovimientoCuadratico(bolasInicio,numMovimiento);
        }else if (mov==ARRASTRE){
            movimiento = new MovimientoArrastre(bolasInicio, numMovimiento);
        }
        modo=mov;
    }

    public int getModo(){
        return modo;
    }

    public float getMaxAngulo(){
        return angulomax;
    }

    public void setMaxAngulo(float a){
        angulomax=a;
    }

    public void setRozamiento(float r){
        rozamiento=r;
    }

    public void setSentidoHorario(boolean sentido){
        sentidoHorario=sentido;
    }

    public void cambiarIncremento (float m){

        float incrementoTemp=incrementoAngulo;

        incrementoAngulo*=(100-m)/100;
        if (incrementoAngulo >= 4 || incrementoAngulo <=0.4){
            incrementoAngulo=incrementoTemp;
        }
        System.out.println(incrementoAngulo);
    }

    public float getIncrementoAnguloDef(){
        return incrementoAnguloDef;
    }

    public float getIncrementoAngulo(){
        return incrementoAngulo;
    }

    public void mover() {
        movimiento.manejarEventoMovimiento();
    }

    private interface IMovimientoListener {

        public void manejarEventoMovimiento();
    }
    
    private class MovimientoLineal implements IMovimientoListener {

        private Bola bolasInicio[];
        private float ro=0.017453292519943295f;
        private float largoHilo=2.8125f;
        private int numBolasTotal;
        private int numBolasMovimiento;


        private int i;

        //public MovimientoVerticalListener(Bola inicio[], Bola demas[]) {
        public MovimientoLineal (Bola inicio[],int movimiento) {
            bolasInicio = inicio;
            numBolasMovimiento = movimiento;
            numBolasTotal=inicio.length;
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
                bolasInicio[i].hilo[0]= Double.valueOf(Math.sin(angulo*ro)*largoHilo).floatValue();
                bolasInicio[i].hilo[1]= Double.valueOf(Math.cos(angulo*ro)*largoHilo).floatValue();
            }





        }
        /**
         * Calculamos el angulo actual teniendo en cuenta el sentido de la bola,y el angulo que tiene
         * con respecto al angulo m�ximo.
         */
        private void calcularAnguloActual() {
            if (angulomax<=0){
                miThread.destroy();
                angulo=0.0f;
//                ReproducirWav reproducir = ReproducirWav.getInstancia();
//                reproducir.ReproducirFicheroWav(sonido.rutaSonido);
                return;
            }
            if (sentidoHorario && angulo <= -angulomax) {
                sentidoHorario = false;
                angulomax-=rozamiento;
            } else if (!sentidoHorario && angulo >= angulomax) {
                sentidoHorario = true;
                angulomax-=rozamiento;                
            }

            if (sentidoHorario) {
                if (angulo>=0 && angulo-incrementoAngulo<=0){                    
                    miThread = new HiloReproduccion(sonido.rutaSonido);
                    miThread.start();
                }
                angulo -= incrementoAngulo;
            } else {
                if (angulo<=0 && angulo+incrementoAngulo>=0){
                    miThread = new HiloReproduccion(sonido.rutaSonido);
                    miThread.start();
                }
                angulo += incrementoAngulo;
            }
        }
    }
    
    
    private class MovimientoQuieto implements IMovimientoListener {

        private Bola bolasInicio[];
        private int numBolasTotal;
        private int i;
        private float largoHilo=2.8125f;
        private float ro=0.017453292519943295f;

        public MovimientoQuieto(Bola inicio[]) {
            bolasInicio = inicio;
            numBolasTotal=inicio.length;
        }

        public void manejarEventoMovimiento() {

            for (i=0;i<numBolasTotal;i++){
                bolasInicio[i].traslacion[0]=-numBolasTotal/2.0f-bolasInicio[i].diametro/2.0f+(i+1)*bolasInicio[i].diametro;
                bolasInicio[i].traslacion[1]=-largoHilo;
                bolasInicio[i].giro[2]=0.0f;


                bolasInicio[i].hilo[0]= Double.valueOf(Math.sin(angulo*ro)*largoHilo).floatValue();
                bolasInicio[i].hilo[1]= Double.valueOf(Math.cos(angulo*ro)*largoHilo).floatValue();
            }


        }
    }

    private class MovimientoCuadratico implements IMovimientoListener {

        private Bola bolasInicio[];
        private float ro=0.017453292519943295f;
        private float largoHilo=2.8125f;
        private int numBolasTotal;
        private float velocidad=3.0f;
        private int numBolasMovimiento;
        private int i;

        //public MovimientoVerticalListener(Bola inicio[], Bola demas[]) {
        public MovimientoCuadratico(Bola inicio[],int movimiento) {
            bolasInicio = inicio;
            numBolasMovimiento = movimiento;
            numBolasTotal=inicio.length;
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

                bolasInicio[i].hilo[0]= Double.valueOf(Math.sin(angulo*ro)*largoHilo).floatValue();
                bolasInicio[i].hilo[1]= Double.valueOf(Math.cos(angulo*ro)*largoHilo).floatValue();
            }

        }
        /**
         * Calculamos el angulo actual teniendo en cuenta el sentido de la bola,y el angulo que tiene
         * con respecto al angulo m�ximo.
         */


        private void calcularAnguloActual() {
            //TODO:introducir un coeficiente de fricci�n para que vaya parando la bola.
            if (angulomax<=0){
                miThread.destroy();
                angulo=0.0f;
                return;
            }
            if (sentidoHorario && angulo <= -angulomax) {
                sentidoHorario = false;
            } else if (!sentidoHorario && angulo >= angulomax) {
                sentidoHorario = true;
            }
            if (angulo>=0){
                if (Math.abs(angulomax-angulo)>0.2){
                    velocidad=((angulomax-angulo) * incrementoAngulo)/15+0.2f;
                }else{
                    velocidad=0.2f;
                }
            }else{
                if (Math.abs(-angulomax-angulo)>0.2){
                    velocidad=((angulomax+angulo) * incrementoAngulo)/15+0.2f;
                }else{
                    velocidad=0.2f;
                }
            }
            if (sentidoHorario) {
                if (angulo>=0 && angulo-velocidad<=0){
                    miThread = new HiloReproduccion(sonido.rutaSonido);
                    miThread.start();
                    angulomax-=rozamiento;
                }
                angulo -= velocidad;
            } else {
                if (angulo<=0 && angulo+velocidad>=0){
                    miThread = new HiloReproduccion(sonido.rutaSonido);
                    miThread.start();
                    angulomax-=rozamiento;
                }
                angulo += velocidad;
            }
        }
    }

    private class MovimientoArrastre implements IMovimientoListener {

        private Bola bolasInicio[];
        private float ro=0.017453292519943295f;
        private float largoHilo=2.8125f;
        private int numBolasTotal;
        private float velocidad=3.0f;
        private int numBolasMovimiento;
        private int i;

        //public MovimientoVerticalListener(Bola inicio[], Bola demas[]) {
        public MovimientoArrastre(Bola inicio[],int movimiento) {
            bolasInicio = inicio;
            numBolasMovimiento = movimiento;
            numBolasTotal=inicio.length;
            angulo=angulomax;
        }

        public void manejarEventoMovimiento() {

            angulo=angulomax;

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

                bolasInicio[i].hilo[0]= Double.valueOf(Math.sin(angulo*ro)*largoHilo).floatValue();
                bolasInicio[i].hilo[1]= Double.valueOf(Math.cos(angulo*ro)*largoHilo).floatValue();
            }

        }
        /**
         * Calculamos el angulo actual teniendo en cuenta el sentido de la bola,y el angulo que tiene
         * con respecto al angulo m�ximo.
         */



    }

}


