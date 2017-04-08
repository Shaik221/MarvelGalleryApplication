package gallery.marvel.example.com.marvelgallery.dagger.contracts;


import java.util.ArrayList;
import java.util.List;

import gallery.marvel.example.com.marvelgallery.model.beans.Result;

public interface ComicsListContract {
    //get list of all comics
    interface View {
        void showComicsList(List<Result> comicsDetailsList);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadComicsList(String url, String publicKey, String ts, String hashKey);
    }

    interface Repository {

      /*  CustomerDetails getCustomerById(long id);
        CustomerDetails getCustomerByFirstName(String name);
        CustomerDetails getCustomerByLastName(String lastName);*/

    }
}
