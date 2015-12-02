package com.github.social_login_library.classes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.social_login_library.interfaces.GoogleSignCallbacks;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;

/**
 * Created by M on 11.9.15.
 */
public class GoogleSignIn implements GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 9001;
    private final String TAG = "GOOGLE SIGN IN";
    private GoogleApiClient mGoogleApiClient;
    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;
    private AppCompatActivity activity;
    private GoogleSignCallbacks googleSignCallbacks;

    public GoogleSignIn(AppCompatActivity activity) {
        this.activity = activity;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        if(mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .enableAutoManage(activity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }

    public void signIn(GoogleSignCallbacks googleSignCallbacks) {
        this.googleSignCallbacks = googleSignCallbacks;
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallbacks<Status>() {
                    @Override
                    public void onFailure(Status status) {
                        Log.e("Google sign out,", "Failure");
                    }

                    @Override
                    public void onSuccess(Status status) {
                        Log.e("Google sign out,", "Success ");
                    }
                });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != Activity.RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                googleSignCallbacks.onGoogleSignInSuccess(result.getSignInAccount());
            } else {
                // Signed out, show unauthenticated UI.
                googleSignCallbacks.onGoogleSignInFailure();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        googleSignCallbacks.onGoogleConnectionFailure(connectionResult);
    }
}