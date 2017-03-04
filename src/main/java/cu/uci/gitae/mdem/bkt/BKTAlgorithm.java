package cu.uci.gitae.mdem.bkt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.*;

/**
 * Created by angel on 16/05/16.
 */
public class BKTAlgorithm {

    /**
     * Probabilidad inicial de dominar la habilidad
     */
    private Double pL0;
    /**
     * Probabilidad de adivinar la respuesta del item.
     */
    private Double pGuess;
    /**
     * Probabilidad de que se equivoque.
     */
    private Double pSlip;
    /**
     *  Probabilidad de aprender la habilidad al responder un item
     */
    private Double pT;
    /**
     *  Listado de items de respuestas de la habilidad
     */
    private List<Item> items;

    public BKTAlgorithm() {
        this.items = new ArrayList<>();
    }

    public BKTAlgorithm(Double pL0, Double pGuess, Double pSlip, Double pT) {
        this.pL0 = pL0;
        this.pGuess = pGuess;
        this.pSlip = pSlip;
        this.pT = pT;
        this.items = new ArrayList<>();
    }

    public BKTAlgorithm(Double pL0, Double pGuess, Double pSlip, Double pT, List<Item> items) {
        this.pL0 = pL0;
        this.pGuess = pGuess;
        this.pSlip = pSlip;
        this.pT = pT;
        this.items = items;
    }

    /**
     * Metodo que se encarga de ejecutar el algoritmo BKT dado una lista de Item.
     * @return
     */
    public Double execute(){
        Double pL[] = new Double[this.items.size()+1];
        pL[0] = this.pL0;
        Double pLParcial;
        for (int i = 1; i <= items.size(); i++) {
            Item actual = this.items.get(i-1);
            if(actual.isCorrecto()){
                pLParcial = (pL[i-1]*(1-pSlip))/(pL[i-1]*(1-pSlip)+(1-pL[i-1])*pGuess);
            }else{
                pLParcial = (pL[i-1]*pSlip)/(pL[i-1]*pSlip+(1-pL[i-1])*(1-pGuess));
            }
            pL[i] = pLParcial + ((1-pLParcial)*pT);
        }
        return pL[items.size()];
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static void main(String[] args) {
        /*BKTAlgorithm bkt = new BKTAlgorithm(0.4,0.2,0.3,0.1);
        Item item1 = new Item(false,"hab1");
        Item item2 = new Item(true,"hab1");
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        bkt.setItems(items);
        System.out.println(bkt.execute());*/

    }
}
