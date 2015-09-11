package com.github.social_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.social_login_library.classes.GoogleSignIn;
import com.github.social_login_library.interfaces.GoogleSignCallbacks;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class MainActivity extends AppCompatActivity implements GoogleSignCallbacks, View.OnClickListener {
    private GoogleSignIn mGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleSignIn = new GoogleSignIn(this, this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                mGoogleSignIn.signIn();
                break;
        }
    }

    @Override
    public void onGoogleSignInSuccess(Person person) {
        String personName = person.getDisplayName();
        String personPhoto = person.getImage().getUrl();
        String personGooglePlusProfile = person.getUrl();

        Toast.makeText(getApplicationContext(), "Hi, " + personName + " :)", Toast.LENGTH_SHORT).show();

        Log.d("Profile info : ", personName + personPhoto + personGooglePlusProfile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleSignIn.RC_SIGN_IN) {
            mGoogleSignIn.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleSignIn.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleSignIn.onStop();
    }
}
