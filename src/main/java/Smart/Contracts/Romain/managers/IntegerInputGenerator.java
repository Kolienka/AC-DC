package Smart.Contracts.Romain.managers;

import jnr.ffi.annotations.In;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class IntegerInputGenerator extends NumericalInputGenerator{

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
