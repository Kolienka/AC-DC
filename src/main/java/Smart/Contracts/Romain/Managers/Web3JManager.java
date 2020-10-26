package Smart.Contracts.Romain.managers;

import org.hyperledger.besu.ethereum.vm.OperationTracer;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.ConsoleDebugTracer;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.protocol.Web3j;
import org.web3j.regreeter.Regreeter;
import org.web3j.tx.gas.DefaultGasProvider;


public class Web3JManager {

    private static final String password = "Password123";
    private static final String src = "src/main/resources/demo-wallet.json";
    private static final long ethFund = 1000000;

    public Web3JManager(){

    }

    public void setup()throws Exception {

        Credentials credentials = WalletUtils.loadCredentials(password, src);
        Configuration configuration = new Configuration(new Address(credentials.getAddress()),ethFund);
        OperationTracer operationTracer = new ConsoleDebugTracer();
        Web3j web3j = Web3j.build(new EmbeddedWeb3jService(configuration, operationTracer));

        //Test methode .send()
        Regreeter regreeter =
                Regreeter.deploy(web3j, credentials, new DefaultGasProvider(), "Hello!").send();

    }
}
