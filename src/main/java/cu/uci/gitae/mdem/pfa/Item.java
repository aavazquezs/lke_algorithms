package cu.uci.gitae.mdem.pfa;

/**
 * Created by home on 10/28/2016.
 */
public class Item {
    private double beta;
    private double ganma;
    private double ro;

    public Item(double beta, double ganma, double ro){
        this.beta = beta;
        this.ganma = ganma;
        this.ro = ro;
    }

    public double getBeta() {
        return beta;
    }

    public double getGanma() {
        return ganma;
    }

    public double getRo() {
        return ro;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public void setGanma(double ganma) {
        this.ganma = ganma;
    }

    public void setRo(double ro) {
        this.ro = ro;
    }
}
