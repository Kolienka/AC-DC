package Smart.Contracts.Romain.managers;

import java.util.ArrayList;

public abstract class InputGenerator<T> {

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
