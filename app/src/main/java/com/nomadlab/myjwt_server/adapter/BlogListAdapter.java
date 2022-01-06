package com.nomadlab.myjwt_server.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nomadlab.myjwt_server.R;
import com.nomadlab.myjwt_server.repository.models.response.ResPost;

import java.util.ArrayList;
import java.util.List;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.ViewHolder> {

    private Context context;
    private List<ResPost.Data> list = new ArrayList<>();

    public BlogListAdapter(Context context) {
        this.context = context;
    }

    public void setItemData(List<ResPost.Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_blog_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResPost.Data data = list.get(position);
        //holder.imageView
        holder.title.setText(data.getTitle());
        holder.userName.setText(data.getUser().getUsername());
        holder.content.setText(data.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView userName;
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.blogPosterIv);
            title = itemView.findViewById(R.id.blogTitleTv);
            userName = itemView.findViewById(R.id.blogUserNameTv);
            content = itemView.findViewById(R.id.blogContentTv);

        }

    }
}
