package Smart.Contracts.Romain.api.services.gestionContrats;

import Smart.Contracts.Romain.generaters.IntegerInputGenerator;
import org.web3j.bubblesort.Bubblesort;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;

public class GestionBubbleSort extends GestionnaireContrat{

    Bubblesort bubblesort;
    String adress;

    @Override
    public void deploy(Web3j web3j, Credentials credentials) throws Exception {
        bubblesort = Bubblesort.deploy(web3j, credentials, new DefaultGasProvider()).send();
        adress = bubblesort.getContractAddress();
    }

    @Override
    public void load(Web3j web3j, Credentials credentials) throws Exception {
        if(adress != null) {
            bubblesort = Bubblesort.load(adress, web3j, credentials, new DefaultGasProvider());
        }
        else{
            deploy(web3j,credentials);
        }
    }

    @Override
    public BigInteger execute(int rang) throws Exception {
        ArrayList<BigInteger> list = new IntegerInputGenerator().generateArray(rang);
        return bubblesort.sort(list).send().getGasUsed();
    }

    public ArrayList<Integer> generateCloud(int rang) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=2; i<rang; i++){
            list.add(execute(i).intValue());
        }
        return list;
    }

}
