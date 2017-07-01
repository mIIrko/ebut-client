package de.htwg.ebut;

public class Application {

    public static void main(String[] args) {

        RestImport ri = new RestImport();
        ri.processImports();

        RestExport re = new RestExport();
        re.processExport();

    }

}

