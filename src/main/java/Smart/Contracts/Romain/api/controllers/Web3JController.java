package Smart.Contracts.Romain.api.controllers;

import Smart.Contracts.Romain.api.services.Web3JService;

import java.math.BigInteger;

public class Web3JController{

    public static String executeSmartcontract(String scriptReq, int rang) throws Exception {
        System.out.println("Script requested:" + scriptReq);
        Web3JService web3JService = new Web3JService(scriptReq);
        return web3JService.executeSmartContract(scriptReq,rang);
    }

    public static String scriptIsAvaible(String name){
        Web3JService web3JService = new Web3JService(name);
        return web3JService.scriptIsAvaible(name);
    }

}