/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.gitae.mdem.irt;


import cu.uci.gitae.mdem.irt.clases.IRTClase;
import cu.uci.gitae.mdem.irt.clases.TipoModeloLogistico;

/**
 *
 * @author administrador
 */
public class IRT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IRTClase irt=new IRTClase(TipoModeloLogistico.Modelo2Parametros);
//        irt.CargarItems();
        double [] ejecucion1=irt.algoritmoIRT2(0.001, 1);
        System.out.println("Estimación de conocimiento: "+ejecucion1[0]+"\nError estandar: "+ejecucion1[1]);
        System.out.println("----------------------------------");
        double [] ejecucion2=irt.algoritmoIRT(1.24, 1);
        System.out.println("Estimación de conocimiento: "+ejecucion2[0]+"\nError estandar: "+ejecucion2[1]);
        
        System.out.println(""+irt.mostrarTabla());
        
    }
    
    
    
}
