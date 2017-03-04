/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.gitae.mdem.irt.clases;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author administrador
 */
public class IRTClase {

    private List<Item> listaItems;
    private TipoModeloLogistico tipo;
    private List<String[][]> datos;
    private DataLoadEasy dataLoad;
    private int cantIteraciones;

    public IRTClase(TipoModeloLogistico tipo) {
        listaItems = new LinkedList<>();
        this.tipo = tipo;
        datos = new LinkedList<>();
        //DataLoad para el dataSet
       dataLoad=new DataLoadEasy("/media/mdem/MULTIBOOT/DataSets/irt-item-poolmathworkforsce.txt");
}

    /**
     * Modelos logísticos para IRT, calculan probabilidad de responder
     * correctamente el item, con un nivel de habilidad determinado.
     */

    /**
     * Valores para el parametro disciminador (a):
     * none 0
     * very low .01 - .34
     * Low .35 - .64
     * moderate .65 - 1.34
     * High 1.35 - 1.69
     * Very high > 1.70
     * Perfect + infinity
     */



    /**
     * Modelo de Rasch
     * Ecuación logística de 1 parámetros b(parámetro de dificultad) - 4 < = b
     * <= + 4. valores típicos -3 < = b < = +3. thetha grande , nivel de habilidad
     */
    public double logisticaEcuacion1Para(double b, double thetha) {
        return 1 / (1 + Math.pow(Math.E,(-1 * (thetha - b))));
    }

    /**
     * Ecuación logística de 2 parámetros b(parámetro de dificultad) - 4 < = b
     * <= + 4. valores típicos -3 < = b < = +3 a(discriminador)- 4< = a < =+ 4,
     * en la práctica -2.80 <= a <= +2.80. thetha grande , nivel de habilidad
     */
    public double logisticaEcuacion2Para(double a, double b, double thetha) {
        return 1 / (1 + Math.pow(Math.E,(-1 * a * (thetha - b))));
    }

    /**
     * Modelo de Birnbaum
     * Ecuación lógistica de 3 parámetros b(parámetro de dificultad) - 4 < = b <
     * = + 4. valores típicos -3 < = b < = +3 a(discriminador)- 4< = a < =+ 4,
     * en la práctica -2.80 <= a <= +2.80. thetha grande , nivel de habilidad
     * c(probabilidad de adivinación del ítem),0 < = c < = 1.0, en la práctica
     * un valor superior a 0.35 no es aceptable
     */
    public double logisticaEcuacion3Para(double a, double b, double c, double thetha) {
        return c + (1 - c) * (1 / (1 + Math.pow(Math.E, (-1 * a * (thetha - b)))));
    }

    /**
     * Ecuación de estimación de conocimiento latente para la próxima iteración
     * a(discriminador)- 4< = a < =+ 4, en la práctica -2.80 <= a <= +2.80.
     * thetha grande , nivel de habilidad u respuesta al examen 1 correcto y 0
     * incorrecto @return
     */
    public double[] ecuacionDeEstimacion(double thetha) {
        double suma1 = 0;
        double suma2 = 0;
        /*String[][] tabla = new String[listaItems.size()][3];
        int i = 0;*/
        for (Item item : listaItems) {
            double probabilidad;
            switch (tipo){
                case Modelo3Parametros: {
                    probabilidad = logisticaEcuacion3Para(item.getDiscriminador(), item.getDificultad(), item.getProbabilidadAdivinar(), thetha);
                    break;
                }
                case Modelo2Parametros:{
                    probabilidad = logisticaEcuacion2Para(item.getDiscriminador(), item.getDificultad(), thetha);
                    break;
                }
                default:{
                    probabilidad=logisticaEcuacion1Para(item.getDificultad(),thetha);
                    break;
                }
            }
            suma1 += item.getDiscriminador() * (item.getRespuesta() - probabilidad);
            suma2 += Math.pow(item.getDiscriminador(), 2) * probabilidad * (1 - probabilidad);
            /*tabla[i][0] = "[a = " + item.getDiscriminador() + ", b = " + item.getDificultad() +", c = "+item.getProbabilidadAdivinar() + ", u = " + item.getRespuesta() +" ]";
            tabla[i][1] = "P(thetha) " + probabilidad;
            tabla[i][2] = "Q(thetha) " + (1 - probabilidad);
            i++;*/
        }
        //datos.add(tabla);
        double errorEstandar = 1 / (Math.sqrt(suma2));
        return new double[]{thetha + suma1 / suma2, errorEstandar, suma1 / suma2};
    }

    /**
     * Recibe como parámetros un error deseado y el nivel de habilidad,
     * realiza iteraciones cuando el error estándar es inferior a ese umbral
     *
     * @param error
     * @param thetha
     * @return
     */

    public double[] algoritmoIRT(double error, double thetha) {
        double[] result = ecuacionDeEstimacion(thetha);
        while (result[1] > error) {
            result = ecuacionDeEstimacion(result[0]);
        }
        return result;
    }

    /**
     * Recibe como parámetros un umbral y el nivel de habilidad,
     * realiza iteraciones cuando el suma1/suma2 es inferior a ese umbral
     *
     * @param umbral
     * @param thetha
     * @return
     */

    public double[] algoritmoIRT2(double umbral, double thetha) {
        double[] result = ecuacionDeEstimacion(thetha);
        cantIteraciones++;
        while (Math.abs(result[2]) > umbral) {
            cantIteraciones++;
            result = ecuacionDeEstimacion(result[0]);
        }
        return result;
    }

    public void CargarItems() throws IOException {
       dataLoad.loadItems2("/media/Datos/PROYECTO/Datasets/kddcup_challenge/bridge_to_algebra_2008_2009_train.txt",1000000000);

        listaItems=dataLoad.getItems();
    /*List<DataSetRow> litem= utils.getDataSetRows();
        for(DataSetRow dsr:litem){
            Random r=new Random();
            double probabilidadAdivinar=r.nextDouble();
            double disciminador=r.nextDouble();
            double dificultad=r.nextDouble();
            int respuesta=r.nextInt(2);
            listaItems.add(new Item(disciminador,dificultad,probabilidadAdivinar,respuesta));
        }*/
    }

    public String mostrarTabla() {
        String salida = "";
        int iteracion=0;
        for (String[][] tabla : datos) {
            salida+="Iteración: "+iteracion+"\n";
            for (int i = 0; i < tabla.length; i++) {
                for (int j = 0; j < tabla[0].length; j++) {
                    salida += tabla[i][j] + "   ";
                }
                salida += "\n";
            }
            salida += "\n\n";
            iteracion++;
        }
        return salida;
    }

    public List<Item> getListaItems() {
        return listaItems;
    }

    public TipoModeloLogistico getTipo() {
        return tipo;
    }

    public DataLoadEasy getDataLoad() {
        return dataLoad;
    }

    public List<String[][]> getDatos() {
        return datos;
    }

    public int getCantIteraciones() {
        return cantIteraciones;
    }

    /**
     *     very easy, easy, medium, hard, very hard
     * @return
     */

    public double dificultadXHints(){
        return 0;
    }

}
