# social-login
######Implementing GOOGLE login :-
1. Download [config file](https://developers.google.com/identity/sign-in/android/start-integrating)

2. Add following line to project level build.gradle 
  ```gradle
  classpath 'com.google.gms:google-services:1.3.0-beta1'
  ```
3. Add following line to app level build.gradle 

  ```gradle
  apply plugin: 'com.google.gms.google-services'
  ```
4. Add this to app level build.gradle too in the dependencies block

  ```gradle
  compile project(':social-login-library')
  ```
5. Now to the class that you need to implement the login, 
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
	- Override ```onStart()``` and ```OnStop()``` methods from your class and put this lines respectively;
	  
	  ```java
		mGoogleSignIn.onStart(); and, mGoogleSignIn.onStop(); 
	  ```
	
	- Let your class implement ```GoogleSignCallbacks``` interface	
	
	- That will need you to overide the method ```onGoogleSignInSuccess()```
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
	
	- It receives a ```Person``` object (```com.google.android.gms.plus.model.people.Person```), you can retrieve information from this as shown above.

6. That's it for now for Google sign in.
