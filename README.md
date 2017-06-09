# FantomItProject
Log in with facebook 
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
 
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                
                
                
                within the code take user public porfile data 
                
                //////////////////
                
                
 and   facebook graph api use to find user firends list and  user public post  and also more
                
 and facebook logout with the code 
                
 LoginManager.getInstance().logOut();
            Intent login = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(login);
            finish();        
                
                
                
