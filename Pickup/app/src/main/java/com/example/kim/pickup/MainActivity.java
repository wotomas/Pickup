package com.example.kim.pickup;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    /**
    private Button _loginButton;
    private TextView _forgotButton;
    private TextView _createAccountButton;
    **/
    private EditText _userNameEditText;
    private EditText _passwordEditText;
    private String _errorString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        _userNameEditText = (EditText) findViewById(R.id.username_edittext);
        _passwordEditText = (EditText) findViewById(R.id.password_edittext);

        setContentView(R.layout.activity_main);


    }

    public void onLoginButton(View view) {
        Log.d("Login", "Login Button Pressed");
        //login button pressed

        //check parameters and see if it is validate
        //ex) create a method that validates username and password. check for null, stringify, empty password, etc.
        //    Return true or false. if true, set _errorString to according error string

        /* TODO */


        //if method returned false(No error), continue and send query to server to login.
    }

    public void onForgotClick(View view) {
        Log.d("Forgot", "Forgot Button Pressed");
        //temp function. Need implementation after phase2 when time we have time
    }

    public void onCreateAccountClick(View view) {
        Log.d("Create Account", "Create Account Button Pressed");
        //create button is pressed
        //create a new intent(a sign in page)
        /* TODO */

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
}
