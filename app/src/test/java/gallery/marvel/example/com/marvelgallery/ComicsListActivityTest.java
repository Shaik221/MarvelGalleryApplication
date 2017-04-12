package gallery.marvel.example.com.marvelgallery;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import gallery.marvel.example.com.marvelgallery.dagger.services.GetComicsList;
import gallery.marvel.example.com.marvelgallery.model.beans.DataResults;
import gallery.marvel.example.com.marvelgallery.model.beans.Result;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class ComicsListActivityTest {

    private Map<String, String> params;
    private String url;

    @Inject
    GetComicsList getComicsListService;

    private ComicsScreenTestComponent testComponent;

    @Before
    public void setUp() throws Exception {

        testComponent = DaggerComicsScreenTestComponent.builder()
                .comicsScreenTestModule(new ComicsScreenTestModule())
                .build();

         params = new HashMap<String, String>();

         //dummy params for test cases
         params.put("ts", "1");
         params.put("apikey", "sample");
         params.put("hash", "hashKey");
         params.put("limit", "100");

         url = BuildConfig.SERVER_URL+"comics?";

        testComponent.inject(this);
    }

    @After
    public void tearDown() {}

    @Test
    public void testWithDataResults() {
        Observable<DataResults> dataResultsObservable = getComicsListService.getAllCommicsDetails(url,params).subscribeOn(Schedulers.io());
        dataResultsObservable.observeOn(AndroidSchedulers.mainThread());

        DataResults dataResults = dataResultsObservable.toBlocking().first();
        assertNotNull(dataResults);
        assertEquals(new Integer(200), dataResults.getCode());
    }

    @Test
    public void testWithComicsData() {
        Observable<DataResults> dataResultsObservable = getComicsListService.getAllCommicsDetails(url,params).subscribeOn(Schedulers.io());
        dataResultsObservable.observeOn(AndroidSchedulers.mainThread());

        DataResults dataResults = dataResultsObservable.toBlocking().first();
        assertNotNull(dataResults);
        assertNotNull(dataResults.getData());
        assertEquals(new Integer(2), dataResults.getData().getLimit());
        assertEquals(new Integer(39273),dataResults.getData().getTotal());
        assertEquals(new Integer(2), dataResults.getData().getCount());
    }

    @Test
    public void testWithComicsDataDetails() {
        Observable<DataResults> dataResultsObservable = getComicsListService.getAllCommicsDetails(url,params).subscribeOn(Schedulers.io());
        dataResultsObservable.observeOn(AndroidSchedulers.mainThread());

        DataResults dataResults = dataResultsObservable.toBlocking().first();
        assertNotNull(dataResults);
        assertNotNull(dataResults.getData());
        assertEquals(new Integer(2), dataResults.getData().getLimit());
        assertEquals(new Integer(39273),dataResults.getData().getTotal());
        assertEquals(new Integer(2), dataResults.getData().getCount());
        assertNotNull(dataResults.getData().getResults());
        ArrayList<Result> expected = new ArrayList<Result>();
        Assert.assertEquals(expected, dataResults.getData().getResults());
    }

    @Test
    public void testWithComicsDataDetails1() {
        Observable<DataResults> dataResultsObservable = getComicsListService.getAllCommicsDetails(url,params).subscribeOn(Schedulers.io());
        dataResultsObservable.observeOn(AndroidSchedulers.mainThread());

        DataResults dataResults = dataResultsObservable.toBlocking().first();
        assertNotNull(dataResults);
        assertNotNull(dataResults.getData());
        assertEquals(new Integer(2), dataResults.getData().getLimit());
        assertEquals(new Integer(39273),dataResults.getData().getTotal());
        assertEquals(new Integer(2), dataResults.getData().getCount());
        assertNotNull(dataResults.getData().getResults());
        assertEquals(new Integer(3627),dataResults.getData().getResults().get(0).getId());
        assertEquals(new Integer(0),dataResults.getData().getResults().get(0).getDigitalId());
        assertEquals("Storm (2006)",dataResults.getData().getResults().get(0).getTitle());
        assertEquals(new Integer(0),dataResults.getData().getResults().get(0).getPageCount());
        assertEquals("http://gateway.marvel.com/v1/public/comics/3627",
                dataResults.getData().getResults().get(0).getResourceURI());
        assertNotNull(dataResults.getData().getResults().get(0).getCreators().getItems());
        assertEquals("Eric Jerome Dickey",dataResults.getData().getResults().get(0).getCreators().getItems().get(0).getName());
        assertEquals("writer",dataResults.getData().getResults().get(0).getCreators().getItems().get(0).getRole());

    }
}