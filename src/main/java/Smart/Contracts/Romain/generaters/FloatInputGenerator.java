package Smart.Contracts.Romain.generaters;

public class FloatInputGenerator extends NumericalInputGenerator {

    /**
     * Générateur de floats aléatoires, génère soit un float, soit un tableau de float. les limites minimales et maximales sont fixables à la construction.
     */
    public FloatInputGenerator(){
        super();
    }
    public FloatInputGenerator(int limMin, int limMax){
        super(limMin,limMax);
    }
    public Float generate(){
        return (getLimMin() + (float)Math.random() * ( getLimMax()-getLimMin() ));
    }



}
