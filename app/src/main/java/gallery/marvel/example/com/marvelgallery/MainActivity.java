package gallery.marvel.example.com.marvelgallery;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import gallery.marvel.example.com.marvelgallery.dagger.component.DaggerComicsScreenComponent;
import gallery.marvel.example.com.marvelgallery.dagger.contracts.ComicsListContract;
import gallery.marvel.example.com.marvelgallery.dagger.module.ComicsScreenModule;
import gallery.marvel.example.com.marvelgallery.model.adapter.ComicsAdapter;
import gallery.marvel.example.com.marvelgallery.model.beans.Result;
import gallery.marvel.example.com.marvelgallery.presenter.ComicsListPresenter;
import gallery.marvel.example.com.marvelgallery.utils.CommonValues;
import gallery.marvel.example.com.marvelgallery.utils.ConnectivityReceiver;
import gallery.marvel.example.com.marvelgallery.utils.TransparentProgressDialog;
import gallery.marvel.example.com.marvelgallery.utils.UserAlerts;

public class MainActivity extends Activity implements ComicsListContract.View{

    RecyclerView recyclerView;
    TransparentProgressDialog pDialog;

    @Inject
    ComicsListPresenter comicsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //progress dialog instance created
        pDialog = new TransparentProgressDialog(this);

        // building dagger component with dependent modules
        DaggerComicsScreenComponent.builder()
                .netComponent(((MarvelApplication)getApplication().getApplicationContext()).getNetComponent())
                .comicsScreenModule(new ComicsScreenModule(this))
                .build().inject(this);

        recyclerView = (RecyclerView)findViewById(R.id.comicsList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        String url = BuildConfig.SERVER_URL+"comics?";
        getComics(url, CommonValues.PUBLIC_KEY, CommonValues.TS, CommonValues.HASH_VALUE);

    }

    public void getComics(String url, String publicKey, String timeStamp, String hashKey){
        //check for network availability and call list of cards
        if(ConnectivityReceiver.isConnected()) {
            pDialog.show();
            comicsListPresenter.loadComicsList(url,publicKey,timeStamp,hashKey);
        } else {
            UserAlerts.showAlertDialog(this,getString(R.string.alert_nonetwork));
        }
    }

    @Override
    public void showError(String message) {
        pDialog.cancel();
    }

    @Override
    public void showComplete() {
        pDialog.cancel();
    }

    @Override
    public void showComicsList(List<Result> comicsDetailsList)
    {
        List<gallery.marvel.example.com.marvelgallery.model.beans.Result> createLists = comicsDetailsList ;
        ComicsAdapter adapter = new ComicsAdapter(this, createLists);
        recyclerView.setAdapter(adapter);
    }
}
