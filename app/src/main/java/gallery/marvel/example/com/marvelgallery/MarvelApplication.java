package gallery.marvel.example.com.marvelgallery;

import android.app.Application;

import gallery.marvel.example.com.marvelgallery.dagger.component.DaggerNetComponent;
import gallery.marvel.example.com.marvelgallery.dagger.component.NetComponent;
import gallery.marvel.example.com.marvelgallery.dagger.module.AppModule;
import gallery.marvel.example.com.marvelgallery.dagger.module.NetModule;


public class MarvelApplication extends Application {

    private NetComponent mNetComponent;
    public static final String TAG = MarvelApplication.class.getSimpleName();


    private static MarvelApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.SERVER_URL,this))
                .build();
    }



    public static synchronized MarvelApplication getInstance() {
        return mInstance;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}