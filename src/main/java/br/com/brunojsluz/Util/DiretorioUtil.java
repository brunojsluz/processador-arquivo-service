package br.com.brunojsluz.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DiretorioUtil {

    private static final String URL_HOME = System.getProperty("user.home");

    private static final String EXTENSAO_ARQUIVO = ".dat";
    private static final String URL_IN = "/data/in";
    private static final String URL_OUT = "/data/out/";

    public static Stream<Path> obterListaArquivos() throws IOException {
        String urlIn = URL_HOME.concat(URL_IN);
        return Files.list(Paths.get(urlIn))
                .filter(path -> path
                        .toString()
                        .endsWith(EXTENSAO_ARQUIVO));
    }

    public static String obterDiretorioSaida() {
        return URL_HOME.concat(URL_OUT);
    }
}
