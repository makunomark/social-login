package com.github.social_login_library.classes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by M on 17.9.15.
 */
public class FacebookSignIn  {
    private CallbackManager callbackManager;
    private Activity activity;

    public FacebookSignIn(Activity activity){
        this.activity = activity;
    }

    private void signIn() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("Facebook Login Successful!");
                        System.out.println("Logged in user Details : ");
                        System.out.println("--------------------------");
                        System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                        System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("Facebook Login failed!!");
                    }

                    @Override
                    public void onError(FacebookException e) {
                        System.out.println("Facebook Login failed!!");
                    }
                });
    }

    public void userData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                String name = null, uniqueId = null, pictureUri = null, profileLink = null;
                try {
                    JSONObject json = response.getJSONObject();
                    json.getString("name");
                    Log.d("JSON DATA", json.toString());
                    name = json.getString("name");
                    uniqueId = json.getString("id");
                    profileLink = json.getString("link");
                    //pictureUri = ((JSONObject) ((JSONObject) json.get("picture")).get("data")).getString("url");
                    pictureUri = "http://graph.facebook.com/"+uniqueId+"/picture?type=large";
//                    createUser(name, uniqueId, pictureUri, profileLink);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
