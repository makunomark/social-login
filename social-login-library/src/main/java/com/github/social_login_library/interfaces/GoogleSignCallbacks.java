package com.github.social_login_library.interfaces;

import com.google.android.gms.plus.model.people.Person;

/**
 * Created by M on 11.9.15.
 */
public interface GoogleSignCallbacks {
    void onGoogleSignInSuccess(Person person);
}
