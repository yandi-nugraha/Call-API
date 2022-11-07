package com.example.callapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<News> list = new ArrayList<>();
    private NewsAdapter newsAdapter;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        recyclerView = findViewById(R.id.rv_news);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNews();
                btn.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getNews() {
        Call<NewsApiResponse> caller = RetrofitClient.getInstance().getMyApi().getNews("id", "897f442283fd4bde9a5c29e8a4a249af");
        caller.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                NewsApiResponse newsApiResponse = response.body();
                list.addAll(newsApiResponse.getArticles());

                newsAdapter = new NewsAdapter(MainActivity.this, list);
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

            }
        });
    }
}