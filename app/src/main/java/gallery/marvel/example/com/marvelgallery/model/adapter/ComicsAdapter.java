package gallery.marvel.example.com.marvelgallery.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import gallery.marvel.example.com.marvelgallery.ComicDetailsActivity;
import gallery.marvel.example.com.marvelgallery.R;
import gallery.marvel.example.com.marvelgallery.model.beans.Result;
import gallery.marvel.example.com.marvelgallery.utils.ImageUtils;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {
    private List<Result> galleryList;
    private Context context;

    public ComicsAdapter(Context context, List<Result> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @Override
    public ComicsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicsAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(galleryList.get(i).getTitle());

        //Loading image from below url into imageView
        Picasso.with(context)
                .load(galleryList.get(i).getThumbnail().getPath()+"."+galleryList.get(i).getThumbnail().getExtension())
                .into(viewHolder.img);

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComicDetailsActivity.class);
                intent.putExtra("ComicDetails", galleryList.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tile);
            img = (ImageView) view.findViewById(R.id.icon);
        }

    }
}