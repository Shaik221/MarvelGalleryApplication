package gallery.marvel.example.com.marvelgallery;

import dagger.Component;
import gallery.marvel.example.com.marvelgallery.utils.CustomScope;

@CustomScope
@Component(modules = {ComicsScreenTestModule.class})
public interface ComicsScreenTestComponent {
    void inject(ComicsListActivityTest test);
}