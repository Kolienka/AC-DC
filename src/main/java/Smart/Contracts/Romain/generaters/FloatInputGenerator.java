package Smart.Contracts.Romain.generaters;

public class FloatInputGenerator extends NumericalInputGenerator {

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
