package gallery.marvel.example.com.marvelgallery.dagger.component;



import javax.inject.Singleton;

import dagger.Component;
import gallery.marvel.example.com.marvelgallery.dagger.module.AppModule;
import gallery.marvel.example.com.marvelgallery.dagger.module.NetModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    Retrofit retrofit();
}