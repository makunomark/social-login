package com.github.social_login_library.interfaces;

import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

/**
 * Created by M on 17.9.15.
 */
public interface FacebookSignInCallbacks {

    void onFacebookSuccess(LoginResult result);

    void onFacebookCancel();

    void onFacebookError();

    void onFacebookUser(JSONObject object, GraphResponse response);
}
