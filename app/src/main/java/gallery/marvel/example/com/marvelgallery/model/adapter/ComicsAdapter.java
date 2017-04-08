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
    public void onBindViewHolder(ComicsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(galleryList.get(i).getTitle());

        //Loading image from below url into imageView

        Picasso.with(context)
                .load(galleryList.get(i).getThumbnail().getPath()+"."+galleryList.get(i).getThumbnail().getExtension())
                .into(viewHolder.img);
      /*  Bitmap tempBitmap = ImageUtils.decodeResource(context.getResources(),galleryList.get(i).getImage_ID());
        int height = ImageUtils.dpToPx(context,200);
        int width = viewHolder.img.getMaxWidth();
        Bitmap scaleBitmap = ImageUtils.getResizedBitmap(tempBitmap,width,height);*/
        //viewHolder.img.setImageBitmap(scaleBitmap);

        //viewHolder.img.setScaleType(ImageView.ScaleType.FIT_XY);
     /*   viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListItemsActivity.class);
                intent.putExtra("GalleryName", galleryCatName );
                context.startActivity(intent);
            }
        });

        viewHolder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, DialogFragmentWindow.class);
                intent.putExtra("GalleryName", galleryCatName );
                context.startActivity(intent);
                return false;
            }
        });*/
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