package Smart.Contracts.Romain.api.services.gestionContrats;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Interface utilisée pour la gestion des contrats
 * deploy() permet de deployer un contrat
 * load() d'en charger un ou de le deployer si ce n'était pas déjà fait.
 * exectue() de récupérer le coup en gas pour un rang donné
 * generateCloud() pour générer une liste comportant le coût en gas pour des rangs allant de 1 à n
 */
public interface GestionContrats {

    void deploy(Web3j web3j, Credentials credentials) throws Exception;
    void load(Web3j web3j, Credentials credentials) throws Exception;
    BigInteger execute(int rang) throws Exception;
    ArrayList<Integer> generateCloud(int rang) throws Exception;
}
