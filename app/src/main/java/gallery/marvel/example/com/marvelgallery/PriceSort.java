package gallery.marvel.example.com.marvelgallery;

import java.util.Comparator;

import gallery.marvel.example.com.marvelgallery.model.beans.Result;

public class PriceSort implements Comparator<Result> {
public int compare(Result a, Result b) {
    return a.getPrices().get(0).getPrice().compareTo(b.getPrices().get(0).getPrice());
}

}