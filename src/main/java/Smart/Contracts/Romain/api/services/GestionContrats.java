package Smart.Contracts.Romain.api.services;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;

import java.math.BigInteger;

public interface GestionContrats {

    void deploy(Web3j web3j, Credentials credentials) throws Exception;
    void load(Web3j web3j, Credentials credentials) throws Exception;
    BigInteger execute(int rang) throws Exception;
}
