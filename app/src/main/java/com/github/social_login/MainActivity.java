package com.github.social_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.github.social_login_library.classes.FacebookSignIn;
import com.github.social_login_library.classes.GoogleSignIn;
import com.github.social_login_library.interfaces.FacebookSignInCallbacks;
import com.github.social_login_library.interfaces.GoogleSignCallbacks;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private GoogleSignIn mGoogleSignIn;
    private FacebookSignIn mFacebookSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.loginButtonGoogle).setOnClickListener(this);
        findViewById(R.id.loginButtonFacebook).setOnClickListener(this);
        findViewById(R.id.loginButtonTwitter).setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButtonGoogle:
                mGoogleSignIn = new GoogleSignIn(this);
                gSignIn();
                break;
            case R.id.loginButtonFacebook:
                mFacebookSignIn = new FacebookSignIn(this);
                fSignIn();
                break;
        }
    }

    private void gSignIn() {
        mGoogleSignIn.signIn(new GoogleSignCallbacks() {
            @Override
            public void onGoogleSignInSuccess(GoogleSignInAccount googleSignInAccount) {
                Toast.makeText(getApplicationContext(), "Hi, " + googleSignInAccount.getDisplayName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onGoogleSignInFailure() {
                Toast.makeText(getApplicationContext(), "Hi sign in failure", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onGoogleConnectionFailure(ConnectionResult connectionResult) {
                Toast.makeText(getApplicationContext(), "Hi connection failure", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void fSignIn() {
        mFacebookSignIn.signIn(new FacebookSignInCallbacks() {
            @Override
            public void onFacebookSuccess(LoginResult result) {
                System.out.println("Facebook Login Successful!");
                System.out.println("Logged in user Details : ");
                System.out.println("--------------------------");
                System.out.println("User ID  : " + result.getAccessToken().getUserId());
                System.out.println("Authentication Token : " + result.getAccessToken().getToken());

                mFacebookSignIn.userData();
            }

            @Override
            public void onFacebookCancel() {
                System.out.println("Facebook Login Cancelled!!");
            }

            @Override
            public void onFacebookError() {
                System.out.println("Facebook Login failed!!");
            }

            @Override
            public void onFacebookUser(JSONObject object, GraphResponse response) {
                String name = null, uniqueId = null, pictureUri = null, profileLink = null;
                try {
                    JSONObject json = response.getJSONObject();
                    json.getString("name");
                    Log.d("JSON DATA", json.toString());
                    name = json.getString("name");
                    uniqueId = json.getString("id");
                    profileLink = json.getString("link");
                    //pictureUri = ((JSONObject) ((JSONObject) json.get("picture")).get("data")).getString("url");
                    pictureUri = "http://graph.facebook.com/" + uniqueId + "/picture?type=large";
//                    createUser(name, uniqueId, pictureUri, profileLink);

                    Toast.makeText(getApplicationContext(), "hi, " + name, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleSignIn.RC_SIGN_IN) {
            mGoogleSignIn.onActivityResult(requestCode, resultCode, data);
        } else {
            mFacebookSignIn.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        if (mFacebookSignIn != null)
            mFacebookSignIn.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        if (mFacebookSignIn != null)
            mFacebookSignIn.onPause();
    }
}
