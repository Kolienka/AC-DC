package Smart.Contracts.Romain.api.services.gestionContrats;

import Smart.Contracts.Romain.api.services.GestionContrats;
import Smart.Contracts.Romain.generaters.IntegerInputGenerator;
import org.web3j.bubblesort.Bubblesort;
import org.web3j.crypto.Credentials;
import org.web3j.mergesort.MergeSort;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;

public class GestionMergeSort extends GestionnaireContrat{

    MergeSort mergeSort;
    String adress;

    @Override
    public void deploy(Web3j web3j, Credentials credentials) throws Exception {
        mergeSort = MergeSort.deploy(web3j, credentials, new DefaultGasProvider()).send();
        adress = mergeSort.getContractAddress();
    }

    @Override
    public void load(Web3j web3j, Credentials credentials) throws Exception {
        if(adress != null) {
            mergeSort = MergeSort.load(adress, web3j, credentials, new DefaultGasProvider());
        }
        else{
            deploy(web3j,credentials);
        }
    }

    @Override
    public BigInteger execute(int rang) throws Exception { //WIP
        ArrayList<BigInteger> list = new IntegerInputGenerator().generateArray(rang);
        //return mergeSort.sort(list).send().getGasUsed();
        return null;
    }
}