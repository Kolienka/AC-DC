package Smart.Contracts.Romain.api.services.gestionContrats;

import java.math.BigInteger;

public abstract class GestionnaireContrat implements GestionContrats {

    public abstract BigInteger execute(int rang) throws Exception;

}
