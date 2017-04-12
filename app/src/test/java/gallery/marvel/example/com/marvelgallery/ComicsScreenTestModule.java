package gallery.marvel.example.com.marvelgallery;

import dagger.Module;
import dagger.Provides;
import gallery.marvel.example.com.marvelgallery.comics.MockComicsListService;
import gallery.marvel.example.com.marvelgallery.dagger.services.GetComicsList;
import gallery.marvel.example.com.marvelgallery.utils.CustomScope;

@Module
public class ComicsScreenTestModule {

    @Provides
    @CustomScope
    public GetComicsList provideComicsData() {
        return new MockComicsListService();
    }
}
