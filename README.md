# social-login
#Implementing GOOGLE login :-
- Download [config file](https://developers.google.com/identity/sign-in/android/start-integrating)

- Add following line to project level build.gradle 
  ```gradle
  classpath 'com.google.gms:google-services:1.3.0-beta1'
  ```
- Add following line to app level build.gradle 
  ```gradle
  apply plugin: 'com.google.gms.google-services'
  ```
- Add this to app level build.gradle too in the dependencies block

  ```gradle
  compile project(':social-login-library')
  ```
- Now to the class that you need to implement the login, 
	- Create a GoogleSignIn object
        ```java	
	private GoogleSignIn mGoogleSignIn;
	```
		
	- Initialize the GoogleSignIn object
	```java
	mGoogleSignIn = new GoogleSignIn(this, this);
	```
	  
	- Override onActivityResult method from Activity Class and put this code in it
	```java
	if (requestCode == GoogleSignIn.RC_SIGN_IN) {
		mGoogleSignIn.onActivityResult(requestCode, resultCode, data);
	}
	```
    	  
	- Override `onStart()` and `OnStop()` methods from your class and put this lines respectively;
	```java
	mGoogleSignIn.onStart();, mGoogleSignIn.onStop(); 
	```
	
	- Let your class implement `GoogleSignCallbacks` interface	
	
	- That will need you to overide the method `onGoogleSignInSuccess()`
	```java
	@Override
	public void onGoogleSignInSuccess(Person person) {
		String personName = person.getDisplayName();
		String personPhoto = person.getImage().getUrl();
		String personGooglePlusProfile = person.getUrl();		         		
		Toast.makeText(getApplicationContext(), "Hi, " + personName + " :)", Toast.LENGTH_SHORT).show();
		Log.d("Profile info : ", personName + personPhoto + personGooglePlusProfile);
	}
	```
	
	- It receives a `Person` object (`com.google.android.gms.plus.model.people.Person`), you can retrieve 				information from this as shown above.


##Implementing FACEBOOK login :-

- Get your application id after filling [this form](https://developers.facebook.com/quickstarts/?platform=android). You will have to register for a facebook developer first.

- Copy the application id and paste it to your `strings.xml` 
```xml
<string name="facebook_app_id">your_app_id_here</string>
```

- Add the following inside your application tags in your `manifest` 
```xml
<meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/facebook_app_id"/>
```

- Add the following in your `onCreate()` method in your activity before the `setContentView()` of your activity.
```java
FacebookSdk.sdkInitialize(this);
```
- Declare your `FacebookSignIn` object in your class
```java
private FacebookSignIn mFacebookSignIn
```

- On your onClick(), or wherever you need to sign in with facebook, initialize yoir `FacebookSignIn` object. then call `signIn()` method in the object you just initialized.
```java
mFacebookSignIn = new FacebookSignIn(this, this);
mFacebookSignIn.signIn();
```

- In order for you to initialize the object you need to implement `FacebookSignInCallbacks`
```java
public class MainActivity extends AppCompatActivity implements FacebookSignInCallbacks{...}
```
- Override the following methods, `onFacebookSuccess`(To notify you of a successful login), `onFacebookCancel`(to notify of 	a cancel), `onFacebookError`(to notify of a login error) and `onFacebookUser`(to recieve user data). The implementation is 	as follows:
```java
//facebook callbacks
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
           		pictureUri = "http://graph.facebook.com/"+uniqueId+"/picture?type=large";
           		Toast.makeText(getApplicationContext(), "hi, "+name, Toast.LENGTH_SHORT).show();
       	} catch (JSONException e) {
       		e.printStackTrace();
       	}
}
```
