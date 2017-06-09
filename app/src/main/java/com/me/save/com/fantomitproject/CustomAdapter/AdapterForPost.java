package com.me.save.com.fantomitproject.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.save.com.fantomitproject.ModelClasses.UserPostModelClass;
import com.me.save.com.fantomitproject.R;

import java.util.ArrayList;

/**
 * Created by acer on 6/8/2017.
 */

public class AdapterForPost extends RecyclerView.Adapter<AdapterForPost.MyViewHolder>{

    private ArrayList<UserPostModelClass> arrayList = new ArrayList<>();

    Context context;

    public AdapterForPost(Context context, ArrayList<UserPostModelClass> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public AdapterForPost.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postrow_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,context,arrayList);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterForPost.MyViewHolder holder, int position) {
        UserPostModelClass developer =  arrayList.get(position);
        holder.mCreateTime.setText(developer.getCreatedTime());
        holder.mPostId.setText(developer.getId());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mCreateTime;
        private TextView mPostId;

        ArrayList<UserPostModelClass> developers;
        Context context;
        public MyViewHolder(View itemView , Context context, ArrayList<UserPostModelClass> developers) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.developers = developers;
            this.context = context;
            mCreateTime = (TextView) itemView.findViewById(R.id.dateAsTitleTv);
            mPostId = (TextView) itemView.findViewById(R.id.idforPostTv);

        }

        @Override
        public void onClick(View v) {

          /*  int position = getAdapterPosition();
            UserPostModelClass posts = developers.get(position);
            Intent intent = new Intent(this.context, PostDetailActivity.class);
            intent.putExtra("createTime",posts.getCreatedTime());
            intent.putExtra("message" , posts.getMessage());
            intent.putExtra("story" , posts.getStory());
            this.context.startActivity(intent);*/

        }
    }
}
