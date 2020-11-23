package Smart.Contracts.Romain.api.services;


import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import static org.joor.Reflect.*;

import Smart.Contracts.Romain.api.controllers.ContractController;
import Smart.Contracts.Romain.api.services.gestionContrats.*;
import Smart.Contracts.Romain.generaters.IntegerInputGenerator;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hyperledger.besu.ethereum.vm.OperationTracer;
import org.web3j.abi.datatypes.Address;

import org.web3j.bubblesort.Bubblesort;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.evm.Configuration;
import org.web3j.evm.PassthroughTracer;
import org.web3j.evm.EmbeddedWeb3jService;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;



public class Web3JService {

    private static final String password = "Password123";
    private static final String src = "src/main/resources/demo-wallet.json";
    private static final long ethFund = Long.MAX_VALUE;
    private static final String PACKAGE = "org.web3j";

    private Credentials credentials;
    private Configuration configuration;
    private OperationTracer operationTracer;
    private Web3j web3j;
    private Class classContract;

    public Web3JService(String script) {
        try {
            credentials = WalletUtils.loadCredentials(password, src);
            configuration = new Configuration(new Address(credentials.getAddress()),ethFund);
            operationTracer = new PassthroughTracer();
            web3j = Web3j.build(new EmbeddedWeb3jService(configuration, operationTracer));
            classContract = getScriptClass(script);
        } catch (Exception e) {
            System.out.println("Erreur lors de la génération du service Web3J.");
        }
    }

    public Class getScriptClass(String scriptName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = PACKAGE.replace(".","/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> files = new ArrayList<>();
        while(resources.hasMoreElements()){
            URL resource = resources.nextElement();
            files.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for(File file : files){
            classes.addAll(findClasses(file, PACKAGE));
        }
        Class[] allClass =  classes.toArray(new Class[classes.size()]);
        Object[] filteredClass = Arrays.stream(allClass)
                .filter(
                        actualClass -> actualClass.getSimpleName().equalsIgnoreCase(scriptName)).toArray();
        Class ret = null;
        if(filteredClass.length == 1){
            ret = (Class) filteredClass[0];
        }
        else {
            System.out.println("No class found for this script name");
        }
        return ret;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }



    public String executeSmartContract(String scriptName, int rang) throws Exception { //WIP
        switch (scriptName.toLowerCase()){
            case "quicksort":

                GestionQuickSort gestion = new GestionQuickSort();
                gestion.deploy(web3j,credentials);
                return "Cout en gas pour un trableau de taille "+ rang + " : " + gestion.execute(rang);

            case "bubblesort":

                GestionBubbleSort gestionBubbleSort = new GestionBubbleSort();
                gestionBubbleSort.deploy(web3j,credentials);
                return "Cout en gas pour un trableau de taille "+ rang + " : " + gestionBubbleSort.execute(rang);

            case "mergesort":

                GestionMergeSort gestionMergeSort = new GestionMergeSort();
                gestionMergeSort.deploy(web3j,credentials);
                return "Cout en gas pour un trableau de taille "+ rang + " : " + gestionMergeSort.execute(rang);

            case "fibonacci":

                GestionFibonacci gestionFibonacci = new GestionFibonacci();
                gestionFibonacci.deploy(web3j,credentials);
                return "Cout en gas pour un entier de rang "+ rang + " : " + gestionFibonacci.execute(rang);

            case "regreeter":

                GestionRegreeter gestionRegreeter = new GestionRegreeter();
                gestionRegreeter.deploy(web3j,credentials);
                return "Cout en gas pour un String de taille "+ rang + " : " + gestionRegreeter.execute(rang);

            default:
                return "Pas de contrat avec ce nom";
        }
    }

    public String scriptIsAvaible(String name){ //WIP
        String avaible = new String();
        avaible = "hello world";
        return (name + " "+ avaible);
    }

}
