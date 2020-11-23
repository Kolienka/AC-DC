package Smart.Contracts.Romain;

import java.math.BigInteger;

public class CurveDeterminer { //WIP

    public CurveDeterminer(){}

    public void determine(int errorRate, BigInteger[] gasArray){ //errorRate en pourcentages
        if(gasArray.length <= 10 ){
            System.out.println("Pas assez d'élements dans le tableau");
            return;
        }

        int ecartDebut = gasArray[2].intValue() - gasArray[1].intValue();
        int ecartFin = gasArray[gasArray.length-1].intValue() - gasArray[gasArray.length-2].intValue();
        if(ecartFin >= ecartDebut * 1-errorRate || ecartFin <= ecartDebut * 1+errorRate){
            System.out.println("Modèle linéaire");
        }
        else if (ecartFin <= ecartDebut * 1-errorRate){
            System.out.println("Modèle logarithmique");
        }else{
            System.out.println("Modèle exponentielle");
        }
    }

}
