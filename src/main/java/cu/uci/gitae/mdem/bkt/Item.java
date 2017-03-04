package cu.uci.gitae.mdem.bkt;

/**
 * Created by lisset on 29/10/2016.
 */
public class Item {
    private boolean correcto;
    private String habilidad;

    public Item(boolean correcto, String habilidad) {
        this.correcto = correcto;
        this.habilidad = habilidad;
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }
}
