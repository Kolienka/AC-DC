package Smart.Contracts.Romain.generaters;

import java.util.ArrayList;

public abstract class InputGenerator<T> {
    /**
     * Classe mère des générateurs aléatoires, permet de générer soit une fois un type T, soit un tableau de T de taille length
     */

    public InputGenerator(){}
    public abstract T generate();
    public ArrayList<T> generateArray(int length){
        ArrayList<T> tab = new ArrayList<>();
        for (int i = 0; i < length; i++){
            tab.add(generate());
        }
        return tab;
    }
}
