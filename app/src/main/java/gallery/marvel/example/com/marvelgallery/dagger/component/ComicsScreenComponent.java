package gallery.marvel.example.com.marvelgallery.dagger.component;



import dagger.Component;
import gallery.marvel.example.com.marvelgallery.ComicsListActivity;
import gallery.marvel.example.com.marvelgallery.dagger.module.ComicsScreenModule;
import gallery.marvel.example.com.marvelgallery.utils.CustomScope;

@CustomScope
@Component(dependencies = NetComponent.class, modules = {ComicsScreenModule.class})
public interface ComicsScreenComponent {
    void inject(ComicsListActivity customersListFragment);
}
