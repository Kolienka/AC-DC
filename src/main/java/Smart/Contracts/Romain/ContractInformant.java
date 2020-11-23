package Smart.Contracts.Romain;


import org.web3j.tx.Contract;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ContractInformant {

    private Contract contrat;

    public ContractInformant(Contract contrat){
        this.contrat = contrat;
    }

    public Method[] getMethods(){
        return contrat.getClass().getMethods();
    }

    public Object[] getObjects() {
        return Arrays.stream(getMethods()).filter(x-> x.getName() == "setGreeting" || x.getName() == "sort" || x.getName() == "fibonacci").toArray();
    }

    public Type[] getParameters() {
        return ((Method) getObjects()[0]).getGenericParameterTypes();
    }
}
