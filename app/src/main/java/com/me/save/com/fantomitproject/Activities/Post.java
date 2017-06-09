package com.me.save.com.fantomitproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.me.save.com.fantomitproject.CustomAdapter.AdapterForPost;
import com.me.save.com.fantomitproject.ModelClasses.UserPostModelClass;
import com.me.save.com.fantomitproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Post extends AppCompatActivity {
    private  String postStory = null;
    private String  postCreate_time = null;
    private String  postId = null;
    private String postMessage = null;
    private  String postPhoto = null;
    private RecyclerView recyclerView;
    private AdapterForPost adapter;



    private ArrayList<UserPostModelClass> arrayList;
    UserPostModelClass  userPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = (RecyclerView) findViewById(R.id.postsRecyclerView);

        arrayList = new ArrayList<>();

        final GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/posts",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {

                        try {
                            for(int i=0;i<response.getJSONObject().getJSONArray("data").length();i++){
                                JSONObject post = response.getJSONObject().getJSONArray("data").getJSONObject(i);

                                if(post.has("message")) {
                                    postMessage = post.getString("message");

                                    Toast.makeText(Post.this, ""+postMessage, Toast.LENGTH_SHORT).show();
                                }

                                if(post.has("story")) {
                                    postStory = post.getString("story");

                                    Toast.makeText(Post.this, ""+postStory, Toast.LENGTH_SHORT).show();
                                }
                                if(post.has("id")) {
                                    postId = post.getString("id");

                                    Toast.makeText(Post.this, ""+postId, Toast.LENGTH_SHORT).show();

                                }
                                if(post.has("created_time")) {
                                    postCreate_time = post.getString("created_time");

                                    Toast.makeText(Post.this, ""+postCreate_time, Toast.LENGTH_SHORT).show();
                                }

                                userPost = new UserPostModelClass(post.getString("story"),post.getString("id"),
                                        post.getString("created_time"),post.getString("message"));
                                arrayList.add(userPost);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

       /* userPost = new UserPostModelClass(postStory,postId,postCreate_time,postMessage);
        arrayList.add(userPost);*/

        adapter = new AdapterForPost(Post.this, arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        request.executeAsync();

    }
}
