/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uva.ig;

public class Movimiento {

     
    private float angulomax = 70;       //Angulo maximo
    private float rozamiento=0f;
    private float incrementoAngulo = 3.0f;  //Incremento del angulo en cada pasada.Marca velocidad.
    private float velocidad=1.0f;
    private float angulo;  //Angulo de la bola
    private boolean sentidoHorario = false;     //para controlar el sentido en que se mueven las bolas
    public static final int QUIETO =0;
    public static final int MOVIMIENTO_LINEAL = 1;
    public static final int MOVIMIENTO_CUADRATICO = 2;
    public static final int ARRASTRE = 3;
    private float velocidadDef = velocidad;
    private Bola bolasInicio[];
    private int numMovimiento;
    private int modo;
    private static GLRenderer renderer;

    private Sonido sonido;

    HiloReproduccion miThread;

    //public Movimiento(Bola inicio[], Bola demas[]) {
    public Movimiento(Bola inicio[], int movimiento,GLRenderer r) {
        bolasInicio = inicio;
        numMovimiento=movimiento;
        //bolasDemas = demas;
        sonido = Sonido.getInstancia();
        renderer=r;
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

    public void cambiarVelocidad (float m){

        velocidad-=m;

        velocidad=(float)Math.rint(velocidad*100)/100;

        if (velocidad>4.0f){
            velocidad=4.0f;
        }

        if (velocidad<0.2f){
            velocidad=0.2f;
        }
    }

    public float getVelocidadDef(){
        return velocidadDef;
    }

    public float getVelocidad (){
        return velocidad;
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

            Iluminacion.rotacion-=0.3*velocidad;
            if (Iluminacion.rotacion<0)
                Iluminacion.rotacion=360f;

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
        float angAnt=0.0f;

        private void calcularAnguloActual() {
            if (angulomax<=0){
                angulo=0.0f;
                renderer.pararMovimiento();
                //miThread.destroy();
                return;
            }
            if (sentidoHorario && angulo <= -angulomax) {
                sentidoHorario = false;
                angulomax-=rozamiento;
            } else if (!sentidoHorario && angulo >= angulomax) {
                sentidoHorario = true;
                angulomax-=rozamiento;                
            }
            angAnt=angulo;
            if (sentidoHorario) {
                angulo -= incrementoAngulo*velocidad;
                if (angAnt>=0 && angulo<=0){
                    if (!sonido.getRutaSonido().equals("")){
                        miThread = new HiloReproduccion(sonido.rutaSonido);
                        miThread.start();
                    }
                }
                
            } else {
                angulo += incrementoAngulo*velocidad;
                if (angAnt<=0 && angulo>=0){
                    if (!sonido.getRutaSonido().equals("")){
                        miThread = new HiloReproduccion(sonido.rutaSonido);
                        miThread.start();
                    }
                }
                
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

            Iluminacion.rotacion-=0.3;
            if (Iluminacion.rotacion<0)
                Iluminacion.rotacion=360f;

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
        private float ang=3.0f;
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
            Iluminacion.rotacion-=0.3*velocidad;
            if (Iluminacion.rotacion<0)
                Iluminacion.rotacion=360f;
        }
        /**
         * Calculamos el angulo actual teniendo en cuenta el sentido de la bola,y el angulo que tiene
         * con respecto al angulo m�ximo.
         */
        float angAnt=0.0f;

        private void calcularAnguloActual() {
            //TODO:introducir un coeficiente de fricci�n para que vaya parando la bola.
            if (angulomax<=0){
                angulo=0.0f;
                renderer.pararMovimiento();
                //miThread.destroy();
                return;
            }
            if (sentidoHorario && angulo <= -angulomax) {
                sentidoHorario = false;
            } else if (!sentidoHorario && angulo >= angulomax) {
                sentidoHorario = true;
            }
            if (angulo>=0){
                if (Math.abs(angulomax-angulo)>0.2){
                    ang=(angulomax-angulo)/12+0.2f;
                }else{
                    ang=0.2f;
                }
            }else{
                if (Math.abs(-angulomax-angulo)>0.2){
                    ang=(angulomax+angulo)/12+0.2f;
                }else{
                    ang=0.2f;
                }
            }
            angAnt=angulo;
            if (sentidoHorario) {
                angulo -= ang*velocidad;
                if (angAnt>=0 && angulo<=0){
                    if (!sonido.getRutaSonido().equals("")){
                        miThread = new HiloReproduccion(sonido.rutaSonido);
                        miThread.start();
                    }
                    angulomax-=rozamiento;
                }
                
            } else {
                angulo += ang*velocidad;
                if (angAnt<=0 && angulo>=0){
                    if (!sonido.getRutaSonido().equals("")){
                        miThread = new HiloReproduccion(sonido.rutaSonido);
                        miThread.start();
                    }
                    angulomax-=rozamiento;
                }
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

            Iluminacion.rotacion-=0.3;
            if (Iluminacion.rotacion<0)
                Iluminacion.rotacion=360f;

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


