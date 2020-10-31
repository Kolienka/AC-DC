package Smart.Contracts.Romain.managers;

import org.hyperledger.besu.ethereum.vm.OperationTracer;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.ConsoleDebugTracer;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.quicksort.QuickSort;
import org.web3j.regreeter.Regreeter;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Web3JManager {

    private static final String password = "Password123";
    private static final String src = "src/main/resources/demo-wallet.json";
    private static final long ethFund = 1000000;
    private static final String ADDRESS = "0x2cf178c0fcf153dd0f40db1af064824a8c6751a5";

    public Web3JManager(){

    }

    public void setup()throws Exception {

        Credentials credentials = WalletUtils.loadCredentials(password, src);
        Configuration configuration = new Configuration(new Address(credentials.getAddress()),ethFund);
        OperationTracer operationTracer = new ConsoleDebugTracer();
        Web3j web3j = Web3j.build(new EmbeddedWeb3jService(configuration, operationTracer));
        //Test methode .send()

        System.out.println();

        // Deploy Quicksort contract

        System.out.println("Starting to deploy Quicksort contract");

        QuickSort quickSort = QuickSort.load(ADDRESS,web3j,credentials,new DefaultGasProvider());


        ArrayList<BigInteger> tab = new ArrayList<>();
        tab.add(new BigInteger("1"));
        tab.add(new BigInteger("5"));
        tab.add(new BigInteger("3"));
        tab.add(new BigInteger("2"));
        tab.add(new BigInteger("10"));
        quickSort.sort(tab);

        //System.out.println(web3j.ethEstimateGas(quickSort.sort(tab).send()));
        //web3j.ethEstimateGas(quicksort.sort(tab));
        // Deploy Greeter contract..
        System.out.println("Starting Greeter deploy..");

    }
}
