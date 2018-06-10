package com.tci.crawling;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Crawler crawler = new Crawler("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/");
        Crawler crawler = new Crawler("http://localhost/WebCrawler/");

        System.out.print("################################## Web Crawling with ID ##########################################\n");

        System.out.print(crawler.searchAlgorithmWithIdDFS(102) + " \n");

        System.out.print("################################## Web Crawling with search word ##################################\n");

        System.out.print(crawler.searchAlgorithmWithWordDFS("No Fences"));

    }
}
