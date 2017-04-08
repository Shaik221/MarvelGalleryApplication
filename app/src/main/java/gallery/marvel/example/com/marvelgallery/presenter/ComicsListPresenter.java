package gallery.marvel.example.com.marvelgallery.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import gallery.marvel.example.com.marvelgallery.dagger.contracts.ComicsListContract;
import gallery.marvel.example.com.marvelgallery.dagger.services.GetComicsList;
import gallery.marvel.example.com.marvelgallery.model.beans.DataResults;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ComicsListPresenter implements ComicsListContract.Presenter {

    Retrofit retrofit;
    ComicsListContract.View mView;

    @Inject
    public ComicsListPresenter(Retrofit retrofit, ComicsListContract.View mView) {
            this.retrofit = retrofit;
            this.mView = mView;
    }

    @Override
    public void loadComicsList(String url, String publicKey, String timeStamp, String hashKey) {
        Map<String, String> params = new HashMap<String, String>();


        if (timeStamp != null)
            params.put("ts", timeStamp);

        if (publicKey!=null)
             params.put("apikey", publicKey);

        if(hashKey != null)
            params.put("hash", hashKey);


        retrofit.create(GetComicsList.class).getAllCommicsDetails(url, params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<DataResults>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            mView.showError(e.getMessage());
                        } catch (Throwable et) {
                            et.printStackTrace();

                        }
                    }

                    @Override
                    public void onNext(DataResults json) {
                        mView.showComicsList(json.getData().getResults());
                    }
                });
    }
}

