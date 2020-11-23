package Smart.Contracts.Romain.api.services.gestionContrats;

import Smart.Contracts.Romain.api.services.GestionContrats;
import Smart.Contracts.Romain.generaters.IntegerInputGenerator;
import org.web3j.bubblesort.Bubblesort;
import org.web3j.crypto.Credentials;
import org.web3j.fibonacci.Fibonacci;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;

public class GestionFibonacci extends GestionnaireContrat{

    Fibonacci fibonacci;
    String adress;

    @Override
    public void deploy(Web3j web3j, Credentials credentials) throws Exception {
        fibonacci = Fibonacci.deploy(web3j, credentials, new DefaultGasProvider()).send();
        adress = fibonacci.getContractAddress();
    }

    @Override
    public void load(Web3j web3j, Credentials credentials) throws Exception {
        if(adress != null) {
           fibonacci = Fibonacci.load(adress, web3j, credentials, new DefaultGasProvider());
        }
        else{
            deploy(web3j,credentials);
        }
    }

    @Override
    public BigInteger execute(int rang) throws Exception { //WIP
        BigInteger val = new BigInteger(String.valueOf(rang));
        //Fibonacci fibo = fibonacci.fibonacci(val).send().getGasUsed();
        return null;
    }
}
