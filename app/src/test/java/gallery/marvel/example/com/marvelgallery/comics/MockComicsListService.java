package gallery.marvel.example.com.marvelgallery.comics;

import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import gallery.marvel.example.com.marvelgallery.dagger.services.GetComicsList;
import gallery.marvel.example.com.marvelgallery.model.beans.DataResults;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


public class MockComicsListService implements GetComicsList {

    @Override public Observable<DataResults> getAllCommicsDetails(@Url String url, @QueryMap Map<String, String> params) {
        DataResults dataResults = new Gson().fromJson(ComicsListTestData.MOCK_COMICS_DATA_JSON, DataResults.class);
        return Observable.just(dataResults).delay(1, TimeUnit.SECONDS);
    }
}
