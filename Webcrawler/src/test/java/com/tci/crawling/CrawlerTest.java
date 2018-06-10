package com.tci.crawling;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertNotNull;

public class CrawlerTest {

    private Crawler crawls = new Crawler("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/");
    private JSONObject jsonObject;

    @Test
    public void givenBean_whenHasValue_thenCorrect() {
        Crawler crawler = new Crawler("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/");
        assertThat(crawler, hasProperty("URL", equalTo("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/")));
    }

    /** Testing for the SearchAlgorithmWithWordDFS with a key searchWord*/
    @Test
    public void shouldAllowToRetrieve_aSearchForAParticularSearchWord() {
        try {
            assertNotNull(crawls.searchAlgorithmWithWordDFS("Clean Code: A Handbook of Agile Software Craftsmanship"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Testing for the SearchAlgorithmWithIdDFS with a key id number*/
    @Test
    public void shouldAllowToRetrieve_aSearchForAParticularID() {

        try {
            assertNotNull( crawls.searchAlgorithmWithIdDFS(102));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}