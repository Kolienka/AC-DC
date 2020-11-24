package Smart.Contracts.Romain.api.controllers;

import Smart.Contracts.Romain.api.services.Web3JService;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Web3JController{

    /**
     *
     * @param scriptReq Nom du script demandé
     * @param rang Rang d'arret de la fonction (ou taille dans le cadre d'un type string en entrée par exemple)
     * @return execute contrat du service Web3J
     * @throws Exception
     */

    public static ArrayList<Integer> executeSmartcontract(String scriptReq, int rang) throws Exception {
        System.out.println("Script requested:" + scriptReq);
        Web3JService web3JService = new Web3JService(scriptReq);
        return web3JService.executeSmartContract(scriptReq,rang);
    }

    /**
     *
     * @param name Nom du script demandé
     * @return True -> Le script est dispnible, False -> Le script n'est pas disponible
     * @throws URISyntaxException
     */

    public static boolean scriptIsAvaible(String name) throws URISyntaxException {
        Web3JService web3JService = new Web3JService(name);
        return web3JService.scriptIsAvaible(name);
    }

}