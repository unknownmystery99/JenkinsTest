package com.tci.crawling;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

    public class Crawler {

        /**
         * variable for pages
         */
        private static int pages;
        /**
         * variable for depth
         */
        private static int depth;
        /**
         * variable for start time
         */
        private static long startTime;
        /**
         * variable for end time
         */
        private static long endTime;
        /**
         * variable for json Array
         */
        private static JSONArray jsonArray;
        private String URL;
        /**
         * Variable json Object
         */
        private JSONObject jsonObj;
        /**
         * Varibale json object 2
         */
        private JSONObject jsonObject2;
        /**
         * Docuemnt for connection
         */
        private Document doc;
        /**
         * Variable used to for converting the length of the url depending on each browser
         */
        private int sLength;

        public boolean connection = false;

        /**
          *Constructor @param URL
         */
        Crawler(String URL) {
            this.URL=URL;
            connectToJsoup();
        }

        /**
         * Method get URL @return URl
         */
    public String getURL() {
        return URL;
    }

    /** Method used to connect */
    public boolean connectToJsoup(){
        startTime = System.currentTimeMillis();
        pages = 0;
        depth = 0;

        jsonObj = new JSONObject();
        jsonObject2 = new JSONObject();
        try {
            doc = Jsoup.connect(URL).get();
            connection = true;
            return connection;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Method used to check for the time elapsed */
    private static long timeElasped() {
        return  endTime - startTime;
    }

    /** WCA Crawl Algorithm used for search with a specific Key Word */
    public JSONObject searchAlgorithmWithWordDFS(String Searchword) throws IOException {

        sLength = URL.length();
        pages++;
        Elements linksToVisit = doc.getElementsByAttributeValue("class", "nav");
        Elements ok = linksToVisit.get(0).getElementsByTag("li");
//        if (ok.isEmpty()){
//            throw new IllegalArgumentException("Oups Sorry!!");
//        }
       // System.out.println(ok);
       // System.out.println();
        ok.remove(ok.last());
       // System.out.println(ok);
        for (Element link : ok) {
            Elements bookdetail = link.getElementsByTag("a");
            doc = Jsoup.connect(URL + bookdetail.attr("href")).get();
            pages++;
            if (depth == 0) {
                depth++;
            }
            Elements detailpage = doc.getElementsByAttributeValue("class", "items");
            Elements results = detailpage.get(0).getElementsByTag("li");
            //System.out.println("HERE");
            //System.out.println(results);
            //System.out.println();
            for (Element result : results) {
                Elements details = result.getElementsByTag("a");
                doc = Jsoup.connect(URL + details.attr("href")).get();
                pages++;
                String name = doc.getElementsByTag("img").attr("alt");
                if (depth == 1) {
                    depth++;
                }
                if (Searchword.equals(name)){
                    endTime = System.currentTimeMillis();
                    Elements detailspage = doc.getElementsByAttributeValue("class", "media-details");
                    Element table = detailspage.select("table").first();
                    Elements th = table.getElementsByTag("th");
                    Elements td = table.getElementsByTag("td");
                    getJSON(jsonObject2,jsonArray,th,td);
                    jsonObject2.put("name", Searchword);
                    jsonObj.put("result", jsonObject2);
                    //System.out.println(jsonObj);
                    return jsonObj;
                } else if (results.last().equals(result)) {
                    depth = 0;
                    endTime = System.currentTimeMillis();
                }
            }
        }
        throw new IllegalArgumentException("Sorry! search word does not exist");

    }

    /** WCA Crawl Algorithm used for search with a specific ID */
    public JSONObject searchAlgorithmWithIdDFS(int id) throws IOException {
        sLength = URL.length();
        pages++;
        Elements linksToVisit = doc.getElementsByAttributeValue("class", "nav");
        Elements ok = linksToVisit.get(0).getElementsByTag("li");
        ok.remove(ok.last());
        for (Element link : ok) {
            Elements bookdetail = link.getElementsByTag("a");
            doc = Jsoup.connect(URL + bookdetail.attr("href")).get();
            pages++;
            if (depth == 0) {
                depth++;
            }
            Elements detailpage = doc.getElementsByAttributeValue("class", "items");
            Elements results = detailpage.get(0).getElementsByTag("li");
            for (Element result : results) {
                Elements details = result.getElementsByTag("a");
                doc = Jsoup.connect(URL + details.attr("href")).get();
                pages++;
                int theID = Integer.parseInt(doc.baseUri().substring(sLength + 15));
                //System.out.println("HERE");
                //System.out.println(doc.baseUri());
                //System.out.println(URL);
                //System.out.println();
                if (depth == 1) {
                    depth++;
                }
                if (id == theID) {
                    endTime = System.currentTimeMillis();
                    Elements detailspage = doc.getElementsByAttributeValue("class", "media-details");
                    Element table = detailspage.select("table").first();
                    Elements th = table.getElementsByTag("th");
                    Elements td = table.getElementsByTag("td");

                    getJSON(jsonObject2,jsonArray,th,td);
                    jsonObject2.put("id", id);
                    jsonObj.put("result", jsonObject2);

                   // System.out.println(jsonObj);
                    return jsonObj;
                } else if (results.last().equals(result)) {
                    depth = 0;
                    endTime = System.currentTimeMillis();
                }
            }

        }
        throw new IllegalArgumentException("Sorry! Input ID does not exit");
    }

    /** Method returning Json Objects */
    public void getJSON(JSONObject jsonObject2,JSONArray jsonArray,Elements th,Elements td){

        for (int i = 0, l = th.size(); i < l; i++) {
            String k = th.get(i).text();
            String v = td.get(i).text();
            if (v.contains(",")) {
                jsonArray = new JSONArray();
                String[] temp = v.split(",");
                for (int j = 0, length = temp.length; j < length; j++) {
                    jsonArray.add(temp[j]);
                }
                jsonObject2.put(k, jsonArray);
            } else
                jsonObject2.put(k, v);
        }
        jsonObj.put("totalTime", endTime - startTime);
        jsonObj.put("pages", pages);
        jsonObj.put("depth", depth);
    }

    public JSONObject searchBookAlgorithmWithWordDFS(Book book, String Search){
        return jsonObj;
    }
}
