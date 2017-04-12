package gallery.marvel.example.com.marvelgallery.dagger.services;

import java.util.Map;

import gallery.marvel.example.com.marvelgallery.model.beans.DataResults;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface GetComicsList {
    @GET
    Observable<DataResults> getAllCommicsDetails(@Url String url, @QueryMap Map<String, String> params);
}
