package com.lsp.library.classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.lsp.library.interfaces.FacebookSignInCallbacks;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by M on 17.9.15.
 */
public class FacebookSignIn {
    private CallbackManager callbackManager;
    private Activity activity;
    FacebookSignInCallbacks facebookSignInCallbacks1;

    public FacebookSignIn(AppCompatActivity activity) {
        this.activity = activity;
    }


    public void signIn(FacebookSignInCallbacks facebookSignInCallbacks) {
        this.facebookSignInCallbacks1 = facebookSignInCallbacks;
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        facebookSignInCallbacks1.onFacebookSuccess(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        facebookSignInCallbacks1.onFacebookCancel();
                    }

                    @Override
                    public void onError(FacebookException e) {
                        facebookSignInCallbacks1.onFacebookError();
                    }
                });
    }

    public void signOut(){
        LoginManager.getInstance().logOut();
    }

    public void userData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                facebookSignInCallbacks1.onFacebookUser(object, response);
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();

    }

    public void onPause() {
        AppEventsLogger.deactivateApp(activity);
    }

    public void onResume() {
        AppEventsLogger.deactivateApp(activity);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
