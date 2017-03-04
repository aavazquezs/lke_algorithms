package cu.uci.gitae.mdem.pfa;

import java.util.Scanner;

/**
 * Created by home on 10/24/2016.
 */
public class PFAAlgorithm {

    public double AprendizajeAcumuladoM(double beta, double ganma, double exitos, double ro, double fracasos) {
        double m = beta + (ganma * exitos) + (ro * fracasos);
        System.out.println("valor de m" + m);
        do {
            if (ProbabilidadItemCorrectoP(m) < 0.43) {
                fracasos++;
                m = beta + (ganma * exitos) + (ro * fracasos);
                System.out.println("valor de m" + m);
            } else {
                exitos++;
                m = beta + (ganma * exitos) + (ro * fracasos);
                System.out.println("valor de m" + m);
            }
        }
        while (exitos < 1);
        return m;
    }

    public double ProbabilidadItemCorrectoP(double m) {
        double p = 0;
        p = 1 / (1 + Math.pow(Math.E, -1 * m));
        return p;
    }

    public static void main(String[] args) {

    }
}
