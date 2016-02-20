# Lumpy Space

**Lumpy Space** was made with android developers in mind. It reduces the work done to include logging in with *Google* and *FaceBook* into your application.

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
## Current progress
- working on loggin in with **Twitter**
