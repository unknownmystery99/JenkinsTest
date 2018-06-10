package com.tci.crawling;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;


@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("deprecation") // Because we using depreciated
public class CrawlerParameterizedTest {

    private static Object[] getURL() {
       return $(
               $("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/")
       );
    }

    /** Testing for Parameterized constructors*/
    @Test
    @Parameters(method = "getURL")
    public void constructorShouldSetURL(String URL) {
        Crawler crawler = new Crawler("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/");
        assertEquals("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/", crawler.getURL());
         }
}
