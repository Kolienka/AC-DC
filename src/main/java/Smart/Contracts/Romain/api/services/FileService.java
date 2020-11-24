package Smart.Contracts.Romain.api.services;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class FileService {
    /**
     * Permet de récuperer les scripts disponibles
     * @return Tableau des fichiers disponibles
     * @throws URISyntaxException
     */
    public File[] getAvailableScripts() throws URISyntaxException {
        FilenameFilter filter = (dir, name) -> name.endsWith(".bin");
        URL resource = FileService.class.getClassLoader().getResource("solidity");
        if (resource == null)
            throw new IllegalArgumentException("file not found!");
        else {
            File folder = new File(resource.toURI());
            return Objects.requireNonNull(folder.listFiles(filter));
        }
    }

}