package com.tci.crawling;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** This test class will test for Test Double cases i.e the Mockito Tests */
@RunWith(MockitoJUnitRunner.class) /** In order for these annotations to be enabled, we’ll need to annotate the JUnit test with a runner – MockitoJUnitRunner*/
public class MockitoTest {

    private List<String> authors = new ArrayList<>();

    @Mock
    private Crawler crawls = new Crawler("http://localhost/WebCrawler/Webcrawler/src/main/webapp/sample_site_to_crawl/");
    @Mock
    private Music spyMusic;
    @Mock
    private Music music;

    /** Variable declaration for Music class to be used */
    private static final String name = "Beethoven: Complete Symphonies";
    private static final String genre = "Classical";
    private static final String format = "CD";
    private static final int year = 2012;
    private static final String artistName = "Ludwig van Beethoven";

    /** Testing for Dummy Object */
    @Test
    public void shouldAllowToRetrieve_aSearchForAParticularBook() {
        String name = "Clean Code: A Handbook of Agile Software Craftsmanship";
        String genre = "Tech"; String format = "Ebook";
        int year = 2008;
        authors.add("Robert C. Martin");
        String publisher = "Prentice Hall"; String ISBN = "978-0132350884";
        Book book = new Book(name,genre,format,year,authors,publisher,ISBN);

        JSONObject jsonObj1 = crawls.searchBookAlgorithmWithWordDFS(book, "Clean Code: A Handbook of Agile Software Craftsmanship");
        JSONObject jsonObj2 = crawls.searchBookAlgorithmWithWordDFS(book, "Clean Code: A Handbook of Agile Software Craftsmanship");
        assertSame("objects are same", jsonObj1, jsonObj2);
    }

    /** Testing for Stubs */
    @Test
    public void shouldConnectToJsoup() {
        /*Arrange*/
         /*Act*/
        boolean result = crawls.connectToJsoup();
        /*Assert*/
        assertTrue("Connection Successful ", result);
    }

    /** Testing for Spy */
    @Before
    public void buildSpy(){
        music = new Music(name,genre,format,year,artistName);
    }

    /** Testing to verify if the Spy annotation can be used to call the real objects
     * As seen below the spyMusic is used to call the getArtist in the music class to get the
     * Artist name but method returns false cause initially the artist name is set to final Beethoven but
     * changed to Mozart results into passed test since names of artist are different
     * The methods getArtist and so on are the Retrieval Interface used to
     * access the actual arguments of the call to the Music Class.*/
    @Test
    public void verifySpyEffectOfRealInstanceOfGetMusicArtistName(){


        /** A spy on the other hand will behave differently – it will actually call the real implementation
         * of the add method and add the element to the underlying list:*/

        Music spyBook = Mockito.spy(new Music(name, genre,format,year,artistName)); // this is the spying

        spyBook.getArtist();

        // verify
        Mockito.verify(spyBook).getArtist();
        // Asserting
        assertEquals("Ludwig van Beethoven", spyBook.getArtist());


        /**
         The role of the test stub is to return controlled values to the object being tested.
         These are described as indirect inputs to the test
         */

        // verify
        Mockito.doReturn("Beethoven: Complete Symphonies").when(spyBook).getName(); // this is the Stubbing definition above
        assertEquals("Beethoven: Complete Symphonies", spyBook.getName());

        // Another way for stubbing
        // Mockito.when(spyBook.getName()).thenReturn("Beethoven: Complete Symphonies"); // this is the Stubbing definition above
        // assertEquals("Beethoven: Complete Symphonies", spyBook.getName());

    }

    /** Testing for Mocks */
    /** Testing to verify if the mock returns a music object.*/
    @Test
    public void verifyMockitoForGetMusicArtistName() {

        Music mockedMusic = Mockito.mock(Music.class);

        mockedMusic.getName();
        Mockito.verify(mockedMusic).getName();

        assertEquals(null, mockedMusic.getName());
    }


}
