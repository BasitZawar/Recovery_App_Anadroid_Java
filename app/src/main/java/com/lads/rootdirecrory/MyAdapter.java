package com.lads.rootdirecrory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //    ArrayList<Spacecraft> spacecrafts;
    public static ArrayList<String> imageList;
    public OnItemClickListener itemClickListener;
    Context c;

    public MyAdapter(Context c,
                     ArrayList<String> imageList, OnItemClickListener itemClickListener) {
        this.c = c;
        this.imageList = imageList;
        this.itemClickListener = itemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        viewHolder.nameText.setText(s.getName());
        Glide.with(viewHolder.itemView).load(imageList.get(position)).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(imageList.get(position));
//            Toast.makeText(view.getContext(), "" + position, Toast.LENGTH_SHORT).show();
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String details);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //        TextView nameText;
        ImageView img;

        public ViewHolder(View view) {
            super(view);
//            nameText = (TextView) itemView.findViewById(R.id.txtName);
            img = (ImageView) itemView.findViewById(R.id.imageView);
//            img.setOnClickListener(view1 -> {
//                Toast.makeText(img.getContext(), "Item selected" +img, Toast.LENGTH_SHORT).show();
//            });
        }
    }


}
