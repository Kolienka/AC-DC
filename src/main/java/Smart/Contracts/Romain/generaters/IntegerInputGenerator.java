package Smart.Contracts.Romain.generaters;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IntegerInputGenerator extends NumericalInputGenerator {

    /**
     * Générateur d'entiers aléatoires
     */

    public IntegerInputGenerator(){
        super();
    }
    public IntegerInputGenerator(int limMin, int limMax){
        super(limMin,limMax);
    }
    public BigInteger generate(){
        return BigDecimal.valueOf((getLimMin() + (int)(Math.random() * ( getLimMax() - getLimMin() + 1)))).toBigInteger();
    }


}
