# social-login

##Implementing GOOGLE login :-

1: get config file from https://developers.google.com/identity/sign-in/android/start-integrating

2: add following line to project level build.gradle - classpath 'com.google.gms:google-services:1.3.0-beta1'

3: add following line to app level build.gradle - apply plugin: 'com.google.gms.google-services'

4: add this to app level build.gradle too in the dependencies block - compile project(':social-login-library')


5: now to the class that you need to implement the login, 
	  
	  1. create a GoogleSignIn object
	   ```java
	  	  private GoogleSignIn mGoogleSignIn;
	  ```
	  ```javascript
    var s = "JavaScript syntax highlighting";
    alert(s);
    ```
	  2. initialize the GoogleSignIn object
		
		  mGoogleSignIn = new GoogleSignIn(this, this);
	  
	  3. override onActivityResult method from Activity Class and put this code in it
		
		  if (requestCode == GoogleSignIn.RC_SIGN_IN) {
        mGoogleSignIn.onActivityResult(requestCode, resultCode, data);
      }
	  
	  - override onStart() and OnStop() methods from your class and put this lines respectively;
		
		    mGoogleSignIn.onStart(); and, mGoogleSignIn.onStop(); 
	  
	  - let your class implement GoogleSignCallbacks interface	
	  
	  - that will need you to overide the method onGoogleSignInSuccess()
		
		@Override
    		public void onGoogleSignInSuccess(Person person) {
        		String personName = person.getDisplayName();
        		String personPhoto = person.getImage().getUrl();
        		String personGooglePlusProfile = person.getUrl();

		        Toast.makeText(getApplicationContext(), "Hi, " + personName + " :)", Toast.LENGTH_SHORT).show();

		        Log.d("Profile info : ", personName + personPhoto + personGooglePlusProfile);
    		}

	- It receives a person object (com.google.android.gms.plus.model.people.Person), you can retrieve information from 		this as shown above.

6: That's it for now for Google sign in.
