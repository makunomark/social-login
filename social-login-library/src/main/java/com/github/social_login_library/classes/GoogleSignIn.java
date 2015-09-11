package com.github.social_login_library.classes;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by M on 11.9.15.
 */
public class GoogleSignIn implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;

    public GoogleSignIn(){

    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void onStart() {
        mGoogleApiClient.connect();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
    }
}