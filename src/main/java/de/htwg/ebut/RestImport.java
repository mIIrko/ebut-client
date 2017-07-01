package de.htwg.ebut;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mirko on 01.07.17.
 */
public class RestImport {

    public void processImports() {

        try {
            // no file in request
            HttpResponse<String> response_1 = Unirest.post("http://localhost:8080/rest/service")
                    .header("content-type", "application/xml")
                    .header("accept", "application/xml")
                    .asString();
            System.out.println("------------------------------");
            System.out.println("RESPONSE 1");
            System.out.println("------------------------------");
            System.out.println("STATUS > " + response_1.getStatus());
            System.out.println("BODY   > " + response_1.getBody());

            // not well formed
            HttpResponse<String> response_2 = Unirest.post("http://localhost:8080/rest/service")
                    .header("content-type", "application/xml")
                    .header("accept", "application/xml")
                    //.body("<bastian>\n    <mirko>\n</bastian>\n    </mirko>s\n")
                    .body(readFileFromClasspath("not-wellformed.xml"))
                    .asString();
            System.out.println("------------------------------");
            System.out.println("RESPONSE 2");
            System.out.println("------------------------------");
            System.out.println("STATUS > " + response_2.getStatus());
            System.out.println("BODY   > " + response_2.getBody());

            // not valid
            HttpResponse<String> response_3 = Unirest.post("http://localhost:8080/rest/service")
                    .header("content-type", "application/xml")
                    .header("accept", "application/xml")
                    .body(readFileFromClasspath("not-valid.xml"))
                    .asString();
            System.out.println("------------------------------");
            System.out.println("RESPONSE 3");
            System.out.println("------------------------------");
            System.out.println("STATUS > " + response_3.getStatus());
            System.out.println("BODY   > " + response_3.getBody());

            // kn media store
            HttpResponse<String> response_4 = Unirest.post("http://localhost:8080/rest/service")
                    .header("content-type", "application/xml")
                    .header("accept", "application/xml")
                    //.body("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n<BMECAT version=\"1.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n\t<HEADER>\n\t\t<CATALOG>\n\t\t\t<LANGUAGE>deu</LANGUAGE>\n\t\t\t<CATALOG_ID>HTWG-EBUS-12</CATALOG_ID>\n\t\t\t<CATALOG_VERSION>1.0</CATALOG_VERSION>\n\t\t\t<CATALOG_NAME>Beispielproduktkatalog für E-Business Laborpraktika</CATALOG_NAME>\n\t\t</CATALOG>\n\t\t<SUPPLIER>\n\t\t\t<SUPPLIER_NAME>KN MEDIA STORE</SUPPLIER_NAME>\n\t\t</SUPPLIER>\n\t</HEADER>\n\t<T_NEW_CATALOG>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A1111</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Tablet PC</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>10 inch tablet pc</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999991</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>399.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.19</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t\t<TERRITORY>FR</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>499.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.1</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A2222</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Dell Vostro 1310</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>13 inch Dell notebook with intel Core 2 Duo 2.1 GHz</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999992</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>845.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>923.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A3333</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Apple iPhone 4</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>iPhone 4 with 32 GB.</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999993</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>850.49</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>888.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A4444</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Playstation 3 Slim Edition</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>PS3 Slim Edition with 320 GB incl 1 wireless controller</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999994</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>319</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>349</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t</T_NEW_CATALOG>\n</BMECAT>\n")
                    .body(readFileFromClasspath("kn-media-store.xml"))
                    .asString();
            System.out.println("------------------------------");
            System.out.println("RESPONSE 4");
            System.out.println("------------------------------");
            System.out.println("STATUS > " + response_4.getStatus());
            System.out.println("BODY   > " + response_4.getBody());

            // same article, other supplier (htwg)
            HttpResponse<String> response_5 = Unirest.post("http://localhost:8080/rest/service")
                    .header("content-type", "application/xml")
                    .header("accept", "application/xml")
                    //.body("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n<BMECAT version=\"1.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n\t<HEADER>\n\t\t<CATALOG>\n\t\t\t<LANGUAGE>deu</LANGUAGE>\n\t\t\t<CATALOG_ID>HTWG-EBUS-12</CATALOG_ID>\n\t\t\t<CATALOG_VERSION>1.0</CATALOG_VERSION>\n\t\t\t<CATALOG_NAME>Beispielproduktkatalog für E-Business Laborpraktika</CATALOG_NAME>\n\t\t</CATALOG>\n\t\t<SUPPLIER>\n\t\t\t<SUPPLIER_NAME>HTWG Konstanz</SUPPLIER_NAME>\n\t\t</SUPPLIER>\n\t</HEADER>\n\t<T_NEW_CATALOG>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A1111</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Tablet PC</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>10 inch tablet pc</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999991</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>399.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.19</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t\t<TERRITORY>FR</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>499.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.1</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A2222</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Dell Vostro 1310</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>13 inch Dell notebook with intel Core 2 Duo 2.1 GHz</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999992</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>845.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>923.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A3333</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Apple iPhone 4</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>iPhone 4 with 32 GB.</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999993</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>850.49</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>888.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A4444</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Playstation 3 Slim Edition</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>PS3 Slim Edition with 320 GB incl 1 wireless controller</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999994</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>319</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>349</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t</T_NEW_CATALOG>\n</BMECAT>\n")
                    .body(readFileFromClasspath("same-articles-other-supplier.xml"))
                    .asString();
            System.out.println("------------------------------");
            System.out.println("RESPONSE 5");
            System.out.println("------------------------------");
            System.out.println("STATUS > " + response_5.getStatus());
            System.out.println("BODY   > " + response_5.getBody());

            // unknown supplier
            HttpResponse<String> response_6 = Unirest.post("http://localhost:8080/rest/service")
                    .header("content-type", "application/xml")
                    .header("accept", "application/xml")
                    //.body("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n<BMECAT version=\"1.2\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n\t<HEADER>\n\t\t<CATALOG>\n\t\t\t<LANGUAGE>deu</LANGUAGE>\n\t\t\t<CATALOG_ID>HTWG-EBUS-12</CATALOG_ID>\n\t\t\t<CATALOG_VERSION>1.0</CATALOG_VERSION>\n\t\t\t<CATALOG_NAME>Beispielproduktkatalog für E-Business Laborpraktika</CATALOG_NAME>\n\t\t</CATALOG>\n\t\t<SUPPLIER>\n\t\t\t<SUPPLIER_NAME>WAESCH GLOBAL INDUSTRIES</SUPPLIER_NAME>\n\t\t</SUPPLIER>\n\t</HEADER>\n\t<T_NEW_CATALOG>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A1111</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Tablet PC</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>10 inch tablet pc</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999991</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>399.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.19</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t\t<TERRITORY>FR</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>499.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.1</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A2222</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Dell Vostro 1310</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>13 inch Dell notebook with intel Core 2 Duo 2.1 GHz</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999992</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>845.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"net_list\">\n\t\t\t\t\t<PRICE_AMOUNT>923.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A3333</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Apple iPhone 4</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>iPhone 4 with 32 GB.</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999993</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>850.49</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>888.99</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t\t<ARTICLE>\n\t\t\t<SUPPLIER_AID>A4444</SUPPLIER_AID>\n\t\t\t<ARTICLE_DETAILS>\n\t\t\t\t<DESCRIPTION_SHORT>Playstation 3 Slim Edition</DESCRIPTION_SHORT>\n\t\t\t\t<DESCRIPTION_LONG>PS3 Slim Edition with 320 GB incl 1 wireless controller</DESCRIPTION_LONG>\n\t\t\t\t<EAN>99999994</EAN>\n\t\t\t</ARTICLE_DETAILS>\n\t\t\t<ARTICLE_ORDER_DETAILS>\n\t\t\t\t<ORDER_UNIT>PK</ORDER_UNIT>\n\t\t\t\t<CONTENT_UNIT>C62</CONTENT_UNIT>\n\t\t\t\t<NO_CU_PER_OU>10</NO_CU_PER_OU>\n\t\t\t</ARTICLE_ORDER_DETAILS>\n\t\t\t<ARTICLE_PRICE_DETAILS>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>319</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>EUR</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.16</TAX>\n\t\t\t\t\t<TERRITORY>DE</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t\t<ARTICLE_PRICE price_type=\"gros_list\">\n\t\t\t\t\t<PRICE_AMOUNT>349</PRICE_AMOUNT>\n\t\t\t\t\t<PRICE_CURRENCY>USD</PRICE_CURRENCY>\n\t\t\t\t\t<TAX>0.076</TAX>\n\t\t\t\t\t<TERRITORY>US</TERRITORY>\n\t\t\t\t</ARTICLE_PRICE>\n\t\t\t</ARTICLE_PRICE_DETAILS>\n\t\t</ARTICLE>\n\t</T_NEW_CATALOG>\n</BMECAT>\n")
                    .body(readFileFromClasspath("not-existing-supplier.xml"))
                    .asString();
            System.out.println("------------------------------");
            System.out.println("RESPONSE 6");
            System.out.println("------------------------------");
            System.out.println("STATUS > " + response_6.getStatus());
            System.out.println("BODY   > " + response_6.getBody());

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException | URISyntaxException ie) {
            ie.printStackTrace();
        }
    }

    //https://stackoverflow.com/a/14693024
    public String readFileFromClasspath(final String fileName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(
                Paths.get(getClass().getClassLoader()
                        .getResource(fileName)
                        .toURI())));
    }
}
