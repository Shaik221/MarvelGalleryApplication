package gallery.marvel.example.com.marvelgallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

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

public class ComicsListActivity extends Activity implements ComicsListContract.View{

    private RecyclerView recyclerView;
    private TransparentProgressDialog pDialog;
    private EditText budget;
    public static ArrayList<Result> comicsList = new ArrayList<>();
    public ArrayList<Result> budgetComicsList = new ArrayList<>();
    private ComicsAdapter adapter;
    private static int totalPagesToRead;

    @Inject
    ComicsListPresenter comicsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //progress dialog instance created
        pDialog = new TransparentProgressDialog(this);

        ImageView searchIcon = (ImageView)findViewById(R.id.searchBtn);
        searchIcon.setOnClickListener(clickListener);

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


        budget = (EditText) findViewById(R.id.user_budget);
        budget.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String userEntry = budget.getText().toString();
                    if(userEntry!=null && !userEntry.isEmpty()) {
                        budgetComicsList.clear();
                        comicsWithinBudget(Double.parseDouble(userEntry));
                    }else{
                        UserAlerts.showAlertDialog(ComicsListActivity.this,getString(R.string.no_values));
                        recyclerView.setAdapter(new ComicsAdapter(ComicsListActivity.this,comicsList));
                        recyclerView.invalidate();
                        totalPagesToRead = 0;
                    }

                    return true;
                }
                return false;
            }
        });

    }

    //method to get list of comics
    public void getComics(String url, String publicKey, String timeStamp, String hashKey){
        //check for network availability and call list of cards
        if(ConnectivityReceiver.isConnected()) {
            pDialog.show();
            comicsListPresenter.loadComicsList(url,publicKey,timeStamp,hashKey);
        } else {
            UserAlerts.showAlertDialog(this,getString(R.string.alert_nonetwork));
        }
    }

    //search onclick
    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {

            String userBudget = budget.getText().toString();
            if(userBudget!=null && !userBudget.isEmpty()) {
                budgetComicsList.clear();
                comicsWithinBudget(Double.parseDouble(userBudget));
            }else{
                UserAlerts.showAlertDialog(ComicsListActivity.this,getString(R.string.no_values));
                recyclerView.setAdapter(new ComicsAdapter(ComicsListActivity.this,comicsList));
                recyclerView.invalidate();
                totalPagesToRead = 0;
            }

        }
    };

    @Override
    public void showError(String message) {
        pDialog.cancel();
    }

    @Override
    public void showComplete() {
        pDialog.cancel();
    }

    @Override
    public void showComicsList(ArrayList<Result> comicsDetailsList)
    {
        ArrayList<gallery.marvel.example.com.marvelgallery.model.beans.Result> createLists = comicsDetailsList ;
        //store comics list into local list
        comicsList = comicsDetailsList;
        adapter = new ComicsAdapter(this, createLists);

        //sorting the list based on prices
        Collections.sort(comicsList, new PriceSort());

        recyclerView.setAdapter(adapter);
    }

    //method to retrieve comics with the given budget
    public void comicsWithinBudget(double givenBudget)
    {
        if(comicsList != null && comicsList.size() > 0)
        {
            //sorting the list based on prices
            Collections.sort(comicsList, new PriceSort());
            totalPagesToRead = 0;

            for (int i =0; i<comicsList.size(); i++)
            {
                double checkPrice = comicsList.get(i).getPrices().get(0).getPrice();
                if(checkPrice == 0 && givenBudget == 0)
                {
                    budgetComicsList.add(comicsList.get(i));
                    totalPagesToRead += comicsList.get(i).getPageCount();
                } else if(checkPrice < givenBudget && givenBudget > 0)
                {
                    totalPagesToRead += comicsList.get(i).getPageCount();
                    budgetComicsList.add(comicsList.get(i));
                    givenBudget = givenBudget-checkPrice;
                }
            }

            recyclerView.setAdapter(new ComicsAdapter(this,budgetComicsList));
            recyclerView.invalidate();
            //alert to show the max number of pages to read
            UserAlerts.showAlertDialog(ComicsListActivity.this,"Total Pages you need to read is:"+String.valueOf(totalPagesToRead));
        }
    }
}
