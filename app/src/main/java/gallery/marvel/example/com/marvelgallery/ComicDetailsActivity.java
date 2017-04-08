package gallery.marvel.example.com.marvelgallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import gallery.marvel.example.com.marvelgallery.model.beans.Result;

public class ComicDetailsActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_details_activity);

        Button backButton = (Button) findViewById(R.id.backBtn);
        backButton.setOnClickListener(this);
        TextView title = (TextView) findViewById(R.id.comic_title);
        TextView descrption = (TextView) findViewById(R.id.comic_description);
        TextView pageCount = (TextView) findViewById(R.id.page_count);
        TextView price = (TextView) findViewById(R.id.price);
        TextView authors = (TextView) findViewById(R.id.authors);


        // To retrieve object in second Activity
        Result comicDetails = (Result)getIntent().getSerializableExtra("ComicDetails");
        title.setText(comicDetails.getTitle());
        descrption.setText(comicDetails.getDescription());
        pageCount.setText(comicDetails.getPageCount().toString());
        price.setText(comicDetails.getPrices().get(0).getPrice().toString());
        //authors.setText(comicDetails.getA);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.backBtn:
                this.finish();
                break;
        }
    }
}
