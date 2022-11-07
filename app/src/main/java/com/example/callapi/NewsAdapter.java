package com.example.callapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final ArrayList<News> values;
    private final LayoutInflater inflater;

    public NewsAdapter(Context context, ArrayList<News> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.txtPublishedAt.setText(values.get(position).getPublishedAt().substring(0,10));
        holder.txtTitle.setText(values.get(position).getTitle());
        holder.txtSource.setText(values.get(position).getSource().getName());
        if (values.get(position).getUrlToImage() != null) {
            Picasso.get().load(values.get(position).getUrlToImage()).into(holder.imgNews);
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPublishedAt;
        TextView txtTitle;
        TextView txtSource;
        ImageView imgNews;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPublishedAt = itemView.findViewById(R.id.txt_publishedAt);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtSource = itemView.findViewById(R.id.txt_source);
            imgNews = itemView.findViewById(R.id.img_news);
        }
    }
}
