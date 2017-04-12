package gallery.marvel.example.com.marvelgallery;

import junit.framework.Assert;

import org.junit.Test;

/**
 * DataResults local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DataResultsUnitTest {
    @Test
    public void list_comics_checks() throws Exception {
        Assert.assertNotNull(new ComicsListActivity());
        Assert.assertEquals(100, ComicsListActivity.comicsList.size());
    }
}