package Smart.Contracts.Romain.api.routes;

import Smart.Contracts.Romain.api.controllers.FileController;
import Smart.Contracts.Romain.api.controllers.Web3JController;

import static spark.Spark.*;

public class Routes {

    public static void load(){

        redirect.get("/scripts","/scripts/");

        path("/scripts", () -> {
            get("/", (request, response) -> FileController.getAvailableScripts());
            get("/:name", (request, response) -> Web3JController.scriptIsAvaible(request.params(":name")));
            get("/:name/result/:rang", (request, response) -> Web3JController.executeSmartcontract(request.params(":name"), Integer.parseInt(request.params(":rang"))));
        });
    }
}