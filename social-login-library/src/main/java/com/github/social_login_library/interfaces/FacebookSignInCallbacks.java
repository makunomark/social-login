package com.github.social_login_library.interfaces;

import com.facebook.login.LoginResult;

/**
 * Created by M on 17.9.15.
 */
public interface FacebookSignInCallbacks {

    void onFacebookSuccess(LoginResult result);
    void onFacebookCancel();
    void onFacebookError();
}
