package de.htwg.ebut;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.*;

/**
 * Created by mirko on 01.07.17.
 */
public class RestExport {

    public void processExport() {

        try {
            // EXPORT_BAD_REQUEST
            HttpResponse<String> response_1 = Unirest.get("http://localhost:8080/rest/service")
                    .asString();
            System.out.println(response_1.getBody());
            writeFileToRessources("response_bad-request.xml", response_1.getBody());

            // EXPORT_XML
            HttpResponse<String> response_2 = Unirest.get("http://localhost:8080/rest/service")
                    .header("accept", "application/xml")
                    .asString();
            System.out.println(response_2.getBody());
            writeFileToRessources("response_export-xml-all.xml", response_2.getBody());

            // EXPORT_XML_SELECTIVE
            HttpResponse<String> response_3 = Unirest.get("http://localhost:8080/rest/service?term=phone")
                    .header("accept", "application/xml")
                    .asString();
            System.out.println(response_3.getBody());
            writeFileToRessources("export-xml-selective.xml", response_3.getBody());

            // EXPORT_XHTML
            HttpResponse<String> response_4 = Unirest.get("http://localhost:8080/rest/service?type=xhtml")
                    .header("accept", "application/xhtml+xml")
                    .asString();
            System.out.println(response_4.getBody());
            writeFileToRessources("response_export-xhtml-all.xml", response_4.getBody());

            // EXPORT_XHTML_SELECTIVE
            HttpResponse<String> response_5 = Unirest.get("http://localhost:8080/rest/service?term=htwg&type=xhtml")
                    .header("accept", "application/xhtml+xml")
                    .asString();
            System.out.println(response_5.getBody());
            writeFileToRessources("response_export-xhtml-selective", response_5.getBody());

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void writeFileToRessources(String filename, String content) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream( filename ), "utf-8"))) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
