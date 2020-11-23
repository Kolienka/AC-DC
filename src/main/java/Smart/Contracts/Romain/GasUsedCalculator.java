package Smart.Contracts.Romain;

import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.ArrayList;

public class GasUsedCalculator {

    private Contract contract;

    public GasUsedCalculator(Contract contract){
        this.contract = contract;
    }

    public ArrayList<BigInteger> calculateGas(int taille){
        ArrayList<BigInteger> gasUsed = new ArrayList<>();
        

        return gasUsed;
    }

}
