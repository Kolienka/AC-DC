package Smart.Contracts.Romain.api.services.gestionContrats;

import Smart.Contracts.Romain.generaters.StringInputGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.regreeter.Regreeter;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;

public class GestionRegreeter extends GestionnaireContrat{

    Regreeter regreeter;
    String adress;

    @Override
    public void deploy(Web3j web3j, Credentials credentials) throws Exception {
        regreeter = Regreeter.deploy(web3j, credentials, new DefaultGasProvider(),"hello!").send();
        adress = regreeter.getContractAddress();
    }

    @Override
    public void load(Web3j web3j, Credentials credentials) throws Exception {
        if(adress != null) {
            regreeter = Regreeter.load(adress, web3j, credentials, new DefaultGasProvider());
        }
        else{
            deploy(web3j,credentials);
        }
    }

    @Override
    public BigInteger execute(int rang) throws Exception {
        StringInputGenerator generator = new StringInputGenerator(rang);
        return regreeter.setGreeting(generator.generate()).send().getGasUsed();
    }

    public ArrayList<Integer> generateCloud(int rang) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=2; i<rang; i++){
            list.add(execute(i).intValue());
        }
        return list;
    }
}