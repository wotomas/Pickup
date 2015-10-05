package com.example.kim.pickup;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText _passwordEditText;
    private EditText _username;
    private EditText _passwordAgain;
    private EditText _phoneNo;
    private RadioGroup _gender;
    private String _errorString = "";

    TextWatcher mTextEditWatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account);

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
        _phoneNo = (EditText) findViewById(R.id.create_phoneNo);
        _gender = (RadioGroup) findViewById(R.id.gender);

        boolean validFlag = true;

        //username invalid form validation
        if ( (!_username.getText().toString().matches("^[a-z0-9]*$"))) {
            _errorString = "Invalid username format";
            validFlag = false;
        }
        //not empty validation
        if(_phoneNo.getText().toString().matches("") || _passwordEditText.getText().toString().matches("")
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
            //Add new user data to database, at this stage

            Toast.makeText(this, "Account was successfully created!", Toast.LENGTH_SHORT).show();
            finish();
        }else
        {
            Toast.makeText(this, _errorString, Toast.LENGTH_SHORT).show();
        }
    }

    public void returnToMain(View view){
        finish();
    }
}
