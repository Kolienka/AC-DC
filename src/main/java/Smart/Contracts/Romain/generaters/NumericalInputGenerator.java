package Smart.Contracts.Romain.generaters;

public abstract class NumericalInputGenerator<T> extends InputGenerator {
    /**
     * Générateur de types numériques, les limites sont modifiables.
     */
    protected int limMin = 0;
    protected int limMax = 1000000000;

    public NumericalInputGenerator(){
        super();
    }

    public NumericalInputGenerator(int limMin, int limMax){
        super();
        this.limMin = limMin;
        this.limMax = limMax;
    }

    public abstract T generate();

    public int getLimMin(){return limMin;}
    public int getLimMax(){return limMax;}
    public void setLimMin(int limMin){this.limMin = limMin;}
    public void setLimMax(int limMax){this.limMax = limMax;}


}
