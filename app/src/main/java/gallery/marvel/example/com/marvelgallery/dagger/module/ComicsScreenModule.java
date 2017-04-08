package gallery.marvel.example.com.marvelgallery.dagger.module;


import dagger.Module;
import dagger.Provides;
import gallery.marvel.example.com.marvelgallery.dagger.contracts.ComicsListContract;
import gallery.marvel.example.com.marvelgallery.utils.CustomScope;

@Module
public class ComicsScreenModule {

    private ComicsListContract.View mView = null;

    public ComicsScreenModule(ComicsListContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    ComicsListContract.View providesMainScreenContractView() {
        return mView;
    }

}
