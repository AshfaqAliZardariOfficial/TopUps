package com.ashfaqalizardaristore.topupsapp;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.LoginStatusCallback;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;
//import com.firebase.ui.auth.AuthUI;
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
//import com.firebase.ui.auth.IdpResponse;
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.ActionCodeSettings;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
// ...

    private static final List<String> permissions = Arrays.asList("public_profile", "email");
//    private CallbackManager mCallbackManager;
//    private LoginButton loginButton;
    Button btnFBLogin;
    // See: https://developer.android.com/training/basics/intents/result
//    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
//            new FirebaseAuthUIActivityResultContract(),
//            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
//                @Override
//                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
//                    onSignInResult(result);
//                }
//            }
//    );
//    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
//        IdpResponse response = result.getIdpResponse();
//        if (result.getResultCode() == RESULT_OK) {
//            // Successfully signed in
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            // ...
//        } else {
//            // Sign in failed. If response is null the user canceled the
//            // sign-in flow using the back button. Otherwise check
//            // response.getError().getErrorCode() and handle the error.
//            // ...
//        }
//    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "User: "+currentUser.getEmail(), Toast.LENGTH_SHORT).show();
       // updateUI(currentUser);
    }
//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithCredential:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        Toast.makeText(LoginActivity.this, "User: "+user.getEmail(), Toast.LENGTH_SHORT).show();
//                        //updateUI(user);
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.getException());
//                        Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show();
//                        //updateUI(null);
//                    }
//                });
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
//        callbackManager = CallbackManager.Factory.create();
//        loginButton = findViewById(R.id.login_button);
       // btnFBLogin = findViewById(R.id.btnFbLogin);
// Initialize Facebook Login button
//        mCallbackManager = CallbackManager.Factory.create();
//        LoginButton loginButton = findViewById(R.id.login_button);
//        loginButton.setPermissions(permissions);
//        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                handleFacebookAccessToken(loginResult.getAccessToken());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "facebook:onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "facebook:onError", error);
//            }
//        });
//        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
//                .setAndroidPackageName(
//                        /* yourPackageName= */ "...",
//                        /* installIfNotAvailable= */ true,
//                        /* minimumVersion= */ null)
//                .setHandleCodeInApp(true) // This must be set to true
//                .setUrl("https://google.com") // This URL needs to be whitelisted
//                .build();



//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder()
//                        .enableEmailLinkSignIn()
//                        .setActionCodeSettings(actionCodeSettings)
//                        .build()
//        );

        // Choose authentication providers
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
////                new AuthUI.IdpConfig.EmailBuilder().build(),
////                new AuthUI.IdpConfig.PhoneBuilder().build(),
////                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                new AuthUI.IdpConfig.EmailBuilder()
//                        .enableEmailLinkSignIn()
//                        .setActionCodeSettings(actionCodeSettings).build()
////                new AuthUI.IdpConfig.TwitterBuilder().build())
//        );

// Create and launch sign-in intent
//        Intent signInIntent = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .build();
//        btnFBLogin.setOnClickListener(view -> {
//            signInLauncher.launch(signInIntent);
//        });
//        if (AuthUI.canHandleIntent(getIntent())) {
//            if (getIntent().getExtras() == null) {
//                return;
//            }
//            String link = getIntent().getExtras().getString("email_link_sign_in");
//            if (link != null) {
//                signInLauncher.launch(AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setEmailLink(link)
//                        .setAvailableProviders(providers)
//                        .build());
//            }
//        }
//        loginButton.setPermissions(Arrays.asList("public_profile", "email", "user_birthday"));
//        loginButton.setPermissions(permissions);


//        LoginManager.getInstance().retrieveLoginStatus(this, new LoginStatusCallback() {
//            @Override
//            public void onCompleted(AccessToken accessToken) {
//                // User was previously logged in, can log them in directly here.
//                // If this callback is called, a popup notification appears that says
//                // "Logged in as <User Name>"
//                Toast.makeText(LoginActivity.this, "Already logged in!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure() {
//                // No access token could be retrieved for the user
//                Toast.makeText(LoginActivity.this, "User not logged in yet.", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Exception exception) {
//                // An error occurred
//                Toast.makeText(LoginActivity.this, "Error, while checking user logged in.", Toast.LENGTH_SHORT).show();
//            }
//        });


        // If you are using in a fragment, call loginButton.setFragment(this);
        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(LoginActivity.this, "Canceled by user.", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                Toast.makeText(LoginActivity.this, "Error, while login.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        Toast.makeText(this, "Is logged in: " + isLoggedIn, Toast.LENGTH_SHORT).show();
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    // ...
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result back to the Facebook SDK
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//    }
}