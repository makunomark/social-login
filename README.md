# Lumpy Space

**Lumpy Space** was made with android developers in mind. It reduces the work done to include logging in with *Google* and *FaceBook* into your application.

![alt tag](Screenshot_2016-02-20-16-39-47.png?raw=true "Home page")
![alt tag](Screenshot_2016-02-20-16-41-08.png?raw=true "Facebook login activity")
![alt tag](Screenshot_2016-02-20-16-40-49.png?raw=true "Google account selection")


Lets get started!

###Step 1: Include the library into you your project by including these blocks of code to your **build.gradle**
```
repositories {
    maven {
        url 'https://dl.bintray.com/makunomark/maven'
    }
}

dependencies {
    compile 'com.lsp.library:library:1.0'
}
```

###Step 2: Start integrating
#### 1. Google
Add config file from [Add Google Services](https://developers.google.com/mobile/add?platform=android&cntapi=signin&cnturl=https:%2F%2Fdevelopers.google.com%2Fidentity%2Fsign-in%2Fandroid%2Fsign-in%3Fconfigured%3Dtrue&cntlbl=Continue%20Adding%20Sign-In)

Add the following permissions to manifest
```
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.USE_CREDENTIALS" />
```
sample use
```
GoogleSignIn mGoogleSignIn = null;
mGoogleSignIn = new GoogleSignIn(this);
mGoogleSignIn.signIn(new GoogleSignInCallbacks(){
	public void onGoogleSuccess(){}
	public void onGoogleCancelled(){}
	public void onGoogle....
});
```
#### 2. Facebook
Get your key from [facebook.com - Facebook Developers](https://developers.facebook.com/quickstarts/?platform=android)

Skip add **Add Facebook SDK to Your Project** section

Follow instructions on **Add Facebook App ID** section

Fill data in **Tell us about your Android project** section

Finally, add your development and release key hashes

Press next and scroll till end of page
	
Sample use
```
FacebookSdk.sdkInitialize(this); before super.onCreate();
FacebookSignIn mFacebookSignIn = null;
mFacebookSignIn = new FacebookSignIn(this);
mFacebookSignIn.signIn(new FacebookSignInCallbacks(){
	public void onFacebookUser(){}
	public void onFacebookFailed(){}
	public void onFacebookSuccess(){}
	public void onFacebook....
});
```

Override the `onActivityResult()` method:

```
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GoogleSignIn.RC_SIGN_IN) {
            mGoogleSignIn.onActivityResult(requestCode, resultCode, data);
        } else {
            mFacebookSignIn.onActivityResult(requestCode, resultCode, data);
        }
    }
```

## Layouts
You can use button layouts included in the library:
```
<include layout="@layout/login_buttons"
	android:layout_height="wrap_content"
	android:layout_width="wrap_content"
	android:layout_centerVertical="true"
	android:layout_centerHorizontal="true" />
```

The button ids are:

- google : loginButtonGoogle
- facebook : loginButtonFacebook
- twitter : loginButtonTwitter

## Current progress
- working on loggin in with **Twitter**
