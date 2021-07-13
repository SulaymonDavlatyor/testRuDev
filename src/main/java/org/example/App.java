package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class App {

    private Document doc;

    public static void main(String[] args) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long ts = timestamp.getTime();
        String[] HEADERS = {"productId", "sellerId", "oriMinPrice", "oriMaxPrice", "productTitle", "promotionId", "URL"};
        ArrayList<Product> products = new ArrayList<>();

        //adding products to array
        for (int offset = 0; offset < 100; offset += 20) {

            String url = "https://gpsfront.aliexpress.com/getRecommendingResults.do?widget_id=5547572&platform=pc&limit=20&offset=" + offset + "&phase=1&productIds2Top=&postback=acc339d0-31ad-46b6-958e-3a27c13742e1&_=" + ts;

            Document doc = getPage(url);

            JsonObject mainObject = JsonParser.parseString(doc.body().text()).getAsJsonObject();
            JsonArray pItem = mainObject.getAsJsonArray("results");

            pItem.forEach(v -> {

                products.add(new Product(
                        v.getAsJsonObject().get("productId").getAsLong(),
                        v.getAsJsonObject().get("sellerId").getAsInt(),
                        v.getAsJsonObject().get("minPrice").getAsString(),
                        v.getAsJsonObject().get("maxPrice").getAsString(),
                        v.getAsJsonObject().get("promotionId").getAsInt(),
                        v.getAsJsonObject().get("productTitle").getAsString(),
                        v.getAsJsonObject().get("productDetailUrl").getAsString()
                ));
            });

        }
        //csv writing
        FileWriter out = new FileWriter("productsList" + ts + ".csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADERS))) {

            products.forEach(product -> {

                try {
                    printer.printRecord(product.getProductId(), product.getSellerId(), product.getOriMinPrice(), product.getOriMaxPrice(), product.getProductTitle(), product.getPromotionId(), product.getUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }


    }

    private static Document getPage(String url) {

        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}



