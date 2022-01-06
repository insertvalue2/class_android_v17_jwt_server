package com.nomadlab.myjwt_server;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nomadlab.myjwt_server.adapter.BlogListAdapter;
import com.nomadlab.myjwt_server.repository.JwtService;
import com.nomadlab.myjwt_server.repository.models.response.ResPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogListFragment extends Fragment {


    private JwtService jwtService;
    private String token;
    private RecyclerView recyclerView;
    public BlogListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("jwt", "");
        jwtService = JwtService.retrofit.create(JwtService.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_blog_list, container, false);
        recyclerView = itemView.findViewById(R.id.blogListRv);
        requestPostData();
        return itemView;
    }

    private void requestPostData() {
        jwtService.getPostList(token).enqueue(new Callback<ResPost>() {
            @Override
            public void onResponse(Call<ResPost> call, Response<ResPost> response) {
                ResPost resPost = response.body();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                BlogListAdapter blogListAdapter = new BlogListAdapter(getContext());
                blogListAdapter.setItemData(resPost.getData());

                recyclerView.hasFixedSize();
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(blogListAdapter);
            }

            @Override
            public void onFailure(Call<ResPost> call, Throwable t) {

            }
        });
    }
}