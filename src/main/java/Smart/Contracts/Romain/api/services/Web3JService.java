package Smart.Contracts.Romain.api.services;


import java.net.URISyntaxException;
import java.util.ArrayList;

import Smart.Contracts.Romain.api.controllers.FileController;
import Smart.Contracts.Romain.api.services.gestionContrats.*;
import org.hyperledger.besu.ethereum.vm.OperationTracer;
import org.web3j.abi.datatypes.Address;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.PassthroughTracer;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.protocol.Web3j;


public class Web3JService {

    private static final String password = "Password123";
    private static final String src = "src/main/resources/demo-wallet.json";
    private static final long ethFund = Long.MAX_VALUE;

    private Credentials credentials;
    private Configuration configuration;
    private OperationTracer operationTracer;
    private Web3j web3j;

    public Web3JService(String script) {
        try {
            credentials = WalletUtils.loadCredentials(password, src);
            configuration = new Configuration(new Address(credentials.getAddress()),ethFund);
            operationTracer = new PassthroughTracer();
            web3j = Web3j.build(new EmbeddedWeb3jService(configuration, operationTracer));
        } catch (Exception e) {
            System.out.println("Erreur lors de la génération du service Web3J.");
        }
    }

    /**
     *
     * @param scriptName nom du script
     * @param rang rang d'arret pour l'execution du smart contrat
     * @return un nuage de points comportant tous les couts en gas du rang 1 à n
     * @throws Exception
     */
    public ArrayList<Integer> executeSmartContract(String scriptName, int rang) throws Exception { //WIP
        switch (scriptName.toLowerCase()){
            case "quicksort":
                GestionQuickSort gestionQuickSort = new GestionQuickSort();
                gestionQuickSort.deploy(web3j,credentials);
                return gestionQuickSort.generateCloud(rang);

            case "bubblesort":

                GestionBubbleSort gestionBubbleSort = new GestionBubbleSort();
                gestionBubbleSort.deploy(web3j,credentials);
                return gestionBubbleSort.generateCloud(rang);

            case "mergesort":

                GestionMergeSort gestionMergeSort = new GestionMergeSort();
                gestionMergeSort.deploy(web3j,credentials);
                return gestionMergeSort.generateCloud(rang);

            case "fibonacci":

                GestionFibonacci gestionFibonacci = new GestionFibonacci();
                gestionFibonacci.deploy(web3j,credentials);
                return gestionFibonacci.generateCloud(rang);

            case "regreeter":

                GestionRegreeter gestionRegreeter = new GestionRegreeter();
                gestionRegreeter.deploy(web3j,credentials);
                return gestionRegreeter.generateCloud(rang);

            default:
                return new ArrayList<>();
        }
    }

    /**
     *
     * @param name Nom du script
     * @return True -> Le script est dipnobile, False -> Le script n'est pas disponible
     * @throws URISyntaxException
     */

    public Boolean scriptIsAvaible(String name) throws URISyntaxException {
        return new FileController().getAvailableScripts().toString().toLowerCase().contains(name.toLowerCase());
    }
}
