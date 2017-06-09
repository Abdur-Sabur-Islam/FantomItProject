package com.me.save.com.fantomitproject.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.me.save.com.fantomitproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class ProfileActivity extends AppCompatActivity {


    private ImageView mProfileImage;
    private TextView mProfileNameTv;
    private TextView mFirstNameTv;
    private TextView mLastNameTv;
    private TextView mLoactionTv;
    private TextView mBitrhDayTv;
    private TextView mGenderTv;
    private TextView mHomeTownTv;
    private TextView mAboutTv;
    private Button aboutmeBtn;
    private Button mPostBtn;
    private Button mFriendsBtn;

    String  name  ;
    String surname ;
    String imageUrl;

    String location;
    String birthday;
    String gender;
    String hometown;
    String about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Profile");

        mProfileImage = (ImageView) findViewById(R.id.profileImage);
        mProfileNameTv = (TextView) findViewById(R.id.profileNameTv);
        mFirstNameTv = (TextView) findViewById(R.id.firstNameTv);
        mLastNameTv = (TextView) findViewById(R.id.lastNameTv);
        mLoactionTv = (TextView) findViewById(R.id.locationTv);
        mBitrhDayTv = (TextView) findViewById(R.id.birthdayTv);
        mGenderTv = (TextView) findViewById(R.id.genderTv);
        mHomeTownTv = (TextView) findViewById(R.id.hometownTv);


        aboutmeBtn = (Button) findViewById(R.id.aboutMeBtn);
        mPostBtn = (Button) findViewById(R.id.postBtn);
        mFriendsBtn = (Button) findViewById(R.id.friendBtn);


        Bundle inBundle = getIntent().getExtras();
        location = inBundle.getString("location");
        birthday = inBundle.getString("birthday");
        gender = inBundle.getString("gender");
        hometown = inBundle.getString("hometown");
        about = inBundle.getString("about");
        name = inBundle.getString("name");
        surname = inBundle.getString("surname");
        imageUrl = inBundle.getString("imageUrl");

        mProfileNameTv.setText("" + name + " " + surname);
        mFirstNameTv.setText(name);
        mLastNameTv.setText(surname);
        mLoactionTv.setText(location);
        mBitrhDayTv.setText(birthday);
        mGenderTv.setText(gender);
        mHomeTownTv.setText(hometown);

        Picasso.with(ProfileActivity.this).load(imageUrl).into(mProfileImage);

        aboutmeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("About");
                builder.setMessage(about+"\n"+"name"+"\n"+surname);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        });

        mPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,Post.class);
                startActivity(intent);
            }
        });

        mFriendsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this,FriendList.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            LoginManager.getInstance().logOut();
            Intent login = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(login);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
