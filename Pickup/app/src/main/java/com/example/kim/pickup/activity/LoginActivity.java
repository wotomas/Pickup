package com.example.kim.pickup.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kim.pickup.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {
    /**
    private Button _loginButton;
    private TextView _forgotButton;
    private TextView _createAccountButton;
    **/
    private EditText _userNameEditText;
    private EditText _passwordEditText;
    private String _errorString = "";
    private Dialog _dialog;

    //private String _databaseUrl = "http://192.168.0.109:3000/users";

    static final int CREATE_ACCOUNT_REQUEST = 1; //The request code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        _userNameEditText = (EditText) findViewById(R.id.username_edittext);
        _passwordEditText = (EditText) findViewById(R.id.password_edittext);

        //*******************FOR DEBUGGING ONLY**********************************//
        //**********************************************************************//
        //This code is for testing only. it erases all the local json formatted database files
        //FileManager.getInstance().deleteUserFile(getBaseContext());
        //**********************************************************************//
        //**********************************************************************//

        //UserController.getInstance().initializeUserController(new UserStorage(), getBaseContext());

        setContentView(R.layout.activity_login);

        _dialog = new Dialog(LoginActivity.this);
        _dialog.setContentView(R.layout.custom_spinner_dialog);
        _dialog.setTitle("Signing Up...");
        //attach TextWatch


    }

    public void onLoginButton(View view) {
        Log.d("Login", "Login Button Pressed");
        _dialog.show();
        //login button pressed

        if(isNetworkAvailable()) {
            //check parameters and see if it is validate
            //ex) create a method that validates username and password. check for null, stringify, empty password, etc.
            //    Return true or false. if true, set _errorString to according error string
            boolean validLoginFlag = validLogin();
            if(validLoginFlag==true) {
                //continue and send query to the server to login
                ParseUser.logInInBackground(_userNameEditText.getText().toString(), _passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        _dialog.dismiss();
                        if(e == null){
                            //login ok
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            //login fail
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage())
                                    .setTitle("Login Failed")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });

            }else
            {
                //toast the error message
                Toast.makeText(this, _errorString, Toast.LENGTH_SHORT).show();
            }
        } else {
            _errorString = "Please Try again when your network is available.";
            Toast.makeText(this, _errorString, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        } else {
            isAvailable = false;
        }

        return isAvailable;
    }

    public void onForgotClick(View view) {
        Log.d("Forgot", "Forgot Button Pressed");
        //temp function. Need implementation after phase2 when time we have time
    }

    public void onCreateAccountClick(View view) {
        Log.d("Create Account", "Create Account Button Pressed");
        //create button is pressed
        //create a new intent(a sign in page)
        if(isNetworkAvailable()) {
            Intent create_account_intent = new Intent(this, CreateAccountActivity.class);
            startActivity(create_account_intent);
            //startActivityForResult(create_account_intent, CREATE_ACCOUNT_REQUEST);
        } else {
            _errorString = "Please Try again when your network is available.";
            Toast.makeText(this, _errorString, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean validLogin(){
        //check parameters and see if it is validate
        //ex) create a method that validates username and password. check for null, stringify, empty password, etc.
        //    Return true or false. if true, set _errorString to according error string

        _userNameEditText = (EditText) findViewById(R.id.username_edittext);
        _passwordEditText = (EditText) findViewById(R.id.password_edittext);
        String username = _userNameEditText.getText().toString();
        String password = _passwordEditText.getText().toString();
        _errorString = ""; //reset error string

        //check null
        if (username.matches("")) {
            _errorString = "Username is required";
        }
        if (password.matches("")) {
            _errorString = "Password is required";
        }
        if(!username.matches("^[a-z0-9]*$")) {
            _errorString = "Invalid username format";
        }
        if((password.length()<4 || password.length()>16)){
            Log.d("length check", "length: "+password.length());
            _errorString = "Invalid password length";
        }

        if(_errorString=="")
            return true;   //valid login details
        else
        {
            return false; //invalie login details
        }

    }
}
