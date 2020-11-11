package Smart.Contracts.Romain.managers;

import org.hyperledger.besu.ethereum.vm.OperationTracer;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.ConsoleDebugTracer;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.evm.PassthroughTracer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.quicksort.QuickSort;
import org.web3j.regreeter.Regreeter;
import org.web3j.tx.Contract;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Web3JManager {

    private static final Web3JManager instance = new Web3JManager();

    private static final String password = "Password123";
    private static final String src = "src/main/resources/demo-wallet.json";
    private static final long ethFund = Long.MAX_VALUE;
    private static Credentials credentials;
    private Web3j web3j;

    public Web3JManager(){
        System.out.println("Génération du Web3J Manager");
    }

    public void setup()throws Exception {
        credentials = WalletUtils.loadCredentials(password, src);
        Configuration configuration = new Configuration(new Address(credentials.getAddress()),ethFund);
        OperationTracer operationTracer = new PassthroughTracer();
        web3j = Web3j.build(new EmbeddedWeb3jService(configuration, operationTracer));
    }

    public static final Web3JManager getInstance(){
        return instance;
    }

    public Web3j getWeb3J(){
        return web3j;
    }

    public Credentials getCredentials(){
        return this.credentials;
    }

}
