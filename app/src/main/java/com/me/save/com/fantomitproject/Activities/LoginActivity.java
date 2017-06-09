package com.me.save.com.fantomitproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.me.save.com.fantomitproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;

    private LoginButton mLognButton;
    private TextView mUserNameTv;
    private String firstName, lastName, email, birthday, gender,location,hometown,about;
    private URL profilePicture;
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mLognButton = (LoginButton) findViewById(R.id.login_button);
        mUserNameTv = (TextView) findViewById(R.id.nameTv);

        callbackManager = CallbackManager.Factory.create();

        mLognButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            userId = object.getString("id");
                            profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
                            if (object.has("first_name"))
                                firstName = object.getString("first_name");
                            if (object.has("last_name"))
                                lastName = object.getString("last_name");
                            if (object.has("email"))
                                email = object.getString("email");
                            if (object.has("birthday"))
                                birthday = object.getString("birthday");
                            if (object.has("gender"))
                                gender = object.getString("gender");

                            if (object.has("hometown"))
                                hometown = object.getString("hometown");

                            if (object.has("location"))
                                location = object.getString("location");

                            if (object.has("about"))
                                about = object.getString("about");

                            Intent main = new Intent(LoginActivity.this, ProfileActivity.class);
                            main.putExtra("name", firstName);
                            main.putExtra("surname", lastName);
                            main.putExtra("birthday", birthday);
                            main.putExtra("gender", gender);
                            main.putExtra("hometown", hometown);
                            main.putExtra("location", location);
                            main.putExtra("about", about);
                            main.putExtra("imageUrl", profilePicture.toString());

                            finish();

                            startActivity(main);


                            Toast.makeText(LoginActivity.this, ""+birthday, Toast.LENGTH_SHORT).show();


                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //Here we put the requested fields to be returned from the JSONObject
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email, birthday, gender");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

                mUserNameTv.setText("cancel");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mLognButton.setReadPermissions("email", "user_birthday", "user_posts","user_photos","user_friends","user_location","user_hometown"
        ,"user_about_me");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}