/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uva.ig;

public class Movimiento {

    private static Double PI = Math.atan(1.0) * 4;
    //private GLUquadricObj quadratic;   //Necesario para dibujar cilindros con glu
    private int milisegundos = 20;      //Tiempo entre cada actualización de pantalla
    private float angulomax = 50;       //Valor m�ximo para el �ngulo
    private float incrementomax = 6.5f;  //Valor m�ximo para los incrementos de �ngulo
    private float angulo = -angulomax;  //La esfera izquierda iniciar� suspendida con el mayor �ngulo v�lido
    private boolean clockwise = false;     //y se trasladar� en sentido counter-clockwise
    public static final int MOVIMIENTO_VERTICAL = 1;
    public static final int MOVIMIENTO_DIAGONAL = 2;
    private Bola bolasInicio[];
    private Bola bolasDemas[];

    public Movimiento(Bola inicio[], Bola demas[]) {
        bolasInicio = inicio;
        bolasDemas = demas;
    }
    private IMovimientoListener movimiento;

    public void setMovimiento(int mov) {
        if (mov == MOVIMIENTO_VERTICAL) {
            movimiento = new MovimientoVerticalListener(bolasInicio, bolasDemas);
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
        private int numBolasTotal;
        private int numBolasMovimiento;
        private int numBolasDemas;
        private int i;
        private int direccion;

        public MovimientoVerticalListener(Bola inicio[], Bola demas[]) {
            bolasInicio = inicio;
            bolasDemas = demas;
            numBolasMovimiento = inicio.length;
            numBolasDemas = demas.length;
            numBolasTotal = numBolasMovimiento + numBolasDemas;
            direccion = 1;
        }

        public void manejarEventoMovimiento() {
            float incremento = Double.valueOf(incrementomax - Math.abs(angulo) / angulomax * incrementomax * 0.85).floatValue();
            if (clockwise && angulo <= -angulomax) {
                clockwise = false;
            } else if (!clockwise && angulo >= angulomax) {
                clockwise = true;
            }

            if (clockwise) {
                angulo -= incremento;
            } else {
                angulo += incremento;
            }

//            if(i<=semueven && angulo<0)
//                esferaAmarrada(angulo);
//            else if(i>esferas-semueven && angulo>0)
//                esferaAmarrada(angulo);
//            else
//                esferaAmarrada(0);

            for (i=0;i<bolasInicio.length;i++){
                bolasInicio[i].traslacion[0]=-numBolasTotal/2.0f-bolasInicio[i].diametro/2.0f+i*bolasInicio[i].diametro;
                bolasInicio[i].traslacion[1]=-2.8125f;
                bolasInicio[i].giro[2]=(angulo<0)?angulo:0.0f;
            }

            for (i=0;i<bolasDemas.length;i++){
                bolasDemas[i].traslacion[0]=-numBolasTotal/2.0f-bolasDemas[i].diametro/2.0f+(i+numBolasMovimiento)*bolasDemas[i].diametro;
                bolasDemas[i].traslacion[1]=-2.8125f;
                //System.out.println(i+numBolasMovimiento+ " " + ((i+numBolasMovimiento)>numBolasDemas && angulo>0));
                bolasDemas[i].giro[2]=((i+1+numBolasMovimiento)>numBolasDemas && angulo>0)?angulo:0.0f;
            }

        }
    }
}
