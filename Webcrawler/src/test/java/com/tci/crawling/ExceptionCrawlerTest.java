package com.tci.crawling;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;


public class ExceptionCrawlerTest {

    private Crawler crawls = new Crawler("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/");

    /** Testing for IllegalArgumentException Exceptions
     * Since we have Tags in the web and if a Tab is empty then
     * Resulting into an IllegalArgumentException exception case
     * if Tag = empty exception and to view remove the @Test expected*/

    @Test(expected = IllegalArgumentException.class)
//    @SuppressWarnings("deprecation") // Because we using depreciated
    public void searchAlgorithmWithWordDFS_shouldThrowIAEForIncorrectSearchWord() throws IOException {
        try {
            crawls.searchAlgorithmWithWordDFS("No"); /**  Testing purpose if search word = No wrong not found exception is fired correctly */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Testing for IllegalArgumentException Exceptions
     * Since we have Tags in the web and if a Tab is empty then
     * Resulting into an IllegalArgumentException exception case
     * if Tag = empty exception and to view remove the @Test expected*/

    @Test(expected = IllegalArgumentException.class)
//    @SuppressWarnings("deprecation") // Because we using depreciated
    public void SearchAlgorithmWithIdDFS_shouldThrowIAEForIncorrectSearchWord() throws IOException {
        try {
            crawls.searchAlgorithmWithIdDFS(23); /**  Testing purpose if ID = 2 not found exception is fired correctly */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Verifying The Exception case */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /** This is used to test for the exception message validation of the expected exception from the class Crawler*/
    @Test
    public void shouldValidateThrowMessageIAEFor_searchAlgorithmWithIdDFS() throws IllegalArgumentException {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Sorry! Input ID does not exit"); // if change the message then exception occurs
        try {
            crawls.searchAlgorithmWithIdDFS(3); /** To verify change id = 203 then you will see the expected exception*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
