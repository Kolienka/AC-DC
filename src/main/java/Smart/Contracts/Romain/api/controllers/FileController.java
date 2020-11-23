package Smart.Contracts.Romain.api.controllers;

import Smart.Contracts.Romain.api.services.FileService;

import java.io.FilenameFilter;
import java.net.URL;
import java.util.Map;
import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

public class FileController extends Controller{

    public static String getAvailableScripts(){
        FileService service = new FileService();
        File[] allFileNames;
        final Map<String, String[]> response = new HashMap<>();
        try {
            allFileNames = service.getAvailableScripts();
            String[] fileNames = new String[allFileNames.length];
            for (int i = 0; i < allFileNames.length; i++) {
                fileNames[i] = allFileNames[i].getName().split("\\.")[0];
            }
            response.put("scripts",fileNames);
        } catch (URISyntaxException e) {
            System.out.println("Erreur gestion fichiers");
            e.printStackTrace();
        }
        return gson.toJson(response);
    }
}
