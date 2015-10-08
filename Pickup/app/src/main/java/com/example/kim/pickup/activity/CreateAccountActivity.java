package com.example.kim.pickup.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kim.pickup.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText _passwordEditText;
    private EditText _username;
    private EditText _passwordAgain;
    private EditText _phoneNumber;
    private RadioGroup _gender;
    private String _errorString = "";

    private Context _context;
    private Dialog _dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        _context = getBaseContext();

        setContentView(R.layout.activity_create_account);

        _dialog = new Dialog(CreateAccountActivity.this);
        _dialog.setContentView(R.layout.custom_spinner_dialog);
        _dialog.setTitle("Signing Up...");

        _passwordEditText = (EditText) findViewById(R.id.create_password);
        _passwordEditText.addTextChangedListener(new TextWatcher() {

            TextView errorText = (TextView) findViewById(R.id.tv_password_length_error);

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() < 4)
                    errorText.setVisibility(View.VISIBLE);
                else if (s.length() >= 4 && s.length() <= 15)
                    errorText.setVisibility(View.INVISIBLE);
                else if (s.length() == 16) {
                    errorText.setVisibility(View.VISIBLE);
                    errorText.setText("Password Max Length Reached");
                }
            }
        });

        //make sure password verification is correct.
        _passwordAgain = (EditText) findViewById(R.id.create_passwordAgain);
        _passwordAgain.addTextChangedListener(new TextWatcher() {

            TextView errorText = (TextView) findViewById(R.id.tv_password_mismatch);

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void afterTextChanged(Editable s) {

                if (s.toString().equals(_passwordEditText.getText().toString()))
                {
                    errorText.setTextColor(Color.GREEN);
                    errorText.setText("Password Match SUCCESS");
                    errorText.setVisibility(View.VISIBLE);
                }
                else
                    errorText.setVisibility(View.INVISIBLE);
            }
        });
        _passwordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                    TextView errorText = (TextView) findViewById(R.id.tv_password_mismatch);

                    if (_passwordAgain.getText().toString().equals(_passwordEditText.getText().toString())) {
                        errorText.setTextColor(Color.GREEN);
                        errorText.setText("Password Match SUCCESS");
                        errorText.setVisibility(View.VISIBLE);
                    } else {
                        errorText.setTextColor(Color.RED);
                        errorText.setText("Password Match FAIL");
                        errorText.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.gender_male:
                if (checked)
                    break;
            case R.id.gender_female:
                if (checked)
                    break;
        }
    }

    //true = valid, so proceed. false = invalid, don't further proceed
    private boolean validCreation() {
        _passwordEditText = (EditText) findViewById(R.id.create_password);
        _passwordAgain = (EditText) findViewById(R.id.create_passwordAgain);
        _username = (EditText) findViewById(R.id.create_username);
        _phoneNumber = (EditText) findViewById(R.id.create_phoneNo);
        _gender = (RadioGroup) findViewById(R.id.gender);

        boolean validFlag = true;

        //username invalid form validation
        if ( (!_username.getText().toString().matches("^[a-z0-9]*$"))) {
            _errorString = "Invalid username format";
            validFlag = false;
        }
        //not empty validation
        if(_phoneNumber.getText().toString().matches("") || _passwordEditText.getText().toString().matches("")
                || _username.getText().toString().matches("") || (_gender.getCheckedRadioButtonId() == -1)){
            _errorString = "Creation Form NOT FULLY FILLED";
            validFlag = false;
        }

        return validFlag;
    }

    public void btnCreate(View view) {
        if(validCreation())
        {
            //TODO for Jimmy
            //create new user
            //show pending sign with a custom dialog
            _dialog.show();

            ParseUser newUser = new ParseUser();

            newUser.setUsername(_username.getText().toString());
            newUser.setPassword(_passwordEditText.getText().toString());
            newUser.put("phoneNumber", _phoneNumber.getText().toString());
            newUser.put("rate", 0.0);
            newUser.put("sport", "none");
            if(_gender.getCheckedRadioButtonId() == R.id.gender_male) {
                newUser.put("isMale", true);
            } else {
                newUser.put("isMale", false);
            }

            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    _dialog.dismiss();

                    if(e == null) {
                        /*
                        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        */
                        CreateAccountActivity.this.finish();
                        Toast.makeText(CreateAccountActivity.this, "Account Create was Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
                        builder.setMessage(e.getMessage())
                                .setTitle("Sign up Error")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            });

        }else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccountActivity.this);
            builder.setMessage(_errorString)
                    .setTitle("Sign up Error")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void returnToMain(View view){
        finish();
    }
}
