package com.github.social_login_library.interfaces;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;

/**
 * Created by M on 11.9.15.
 */
public interface GoogleSignCallbacks {
    void onGoogleSignInSuccess(GoogleSignInAccount googleSignInAccount);

    void onGoogleSignInFailure();

    void onGoogleConnectionFailure(ConnectionResult connectionResult);
}
