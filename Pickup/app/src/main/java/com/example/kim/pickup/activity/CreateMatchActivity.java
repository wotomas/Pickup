package com.example.kim.pickup.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.kim.pickup.R;
import com.example.kim.pickup.controller.MatchController;
import com.example.kim.pickup.unit.Match;

import java.util.Calendar;

public class CreateMatchActivity extends AppCompatActivity {
    public static final String TAG = "CreateMatchActivity";
    Match matchObject;
    Button createButton;
    Button pickDateButton;
    Button pickTimeButton;

    Spinner locationSpinner;
    EditText sportsTypeEditText;
    EditText matchTitleEditText;
    EditText playerCapacityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create New Match");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DE5460")));

        setContentView(R.layout.activity_create_match);
        matchObject = new Match();
        matchTitleEditText = (EditText) findViewById(R.id.matchTitleEditText);
        pickDateButton = (Button) findViewById(R.id.pickDateButton);
        pickTimeButton = (Button) findViewById(R.id.pickTimeButton);
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        sportsTypeEditText = (EditText) findViewById(R.id.sportsTypeEditText);
        sportsTypeEditText.setText(MainActivity.CURRENT_USER_SPORTS);
        playerCapacityEditText = (EditText) findViewById(R.id.playerCapacityEditText);

        createButton = (Button) findViewById(R.id.createRoomButton);
        createButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *     private String _matchName;
                 *     private Calendar _startTime;
                 *     private double _distance;
                 *     private int sportKey;
                 *     private int totalCapacity;
                 *     private int popularity;
                 *     private int ownerID;
                 **/


                if(matchTitleEditText.getText().toString() == "") {
                    Log.d(TAG, "Match Title is null");
                    matchObject.set_matchName("");
                } else {
                    matchObject.set_matchName(matchTitleEditText.getText().toString());
                }

                String[] dates = new String[3];
                dates = pickDateButton.getText().toString().split("\\.");
                //Log.d(TAG, "Date: " + pickDateButton.getText().toString());
                String[] times = new String[3];
                times = pickTimeButton.getText().toString().split("\\:");

                Log.d(TAG, "Match Name: " + matchTitleEditText.getText().toString());
                Log.d(TAG, "Year is : " + dates[0] + " " + dates[1] + " " + dates[2] + " Time: " + times[0] + " " + times[1]);
                Calendar saveDate = Calendar.getInstance();
                saveDate.set(Calendar.YEAR, Integer.parseInt(dates[0]));
                saveDate.set(Calendar.MONTH, Integer.parseInt(dates[1]));
                saveDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates[2]));
                saveDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
                saveDate.set(Calendar.MINUTE, Integer.parseInt(times[1]));
                matchObject.set_startTime(saveDate);


                matchObject.set_location(0);

                matchObject.setSportKey(MainActivity.CURRENT_USER_SPORTS);

                if(playerCapacityEditText.getText().toString() == "") {
                    //if capacity is not specified
                    matchObject.setTotalCapacity(2);
                } else {
                    matchObject.setTotalCapacity(Integer.parseInt(playerCapacityEditText.getText().toString()));
                }

                matchObject.setPopularity(0);
                matchObject.setOwnerID(MainActivity.CURRENT_USER);

                Intent intent = new Intent();
                MatchController.getInstance().addMatch(matchObject, getBaseContext());
                //intent.putExtra("MatchObject", matchObject);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.locationSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                Object item = parent.getItemAtPosition(position);
                Log.d("testing Spinner", "Spinner item selected is: " + item.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("testing Spinner", "Nothing selected");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_match, menu);

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

    ///////////////////////Time & Date Picker////////////////////////////////
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        Button pickTimeButton;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            Log.d("Testing SetTime", "Time set to: hour=" + hourOfDay +" minute= "+minute);
            pickTimeButton = (Button)getActivity().findViewById(R.id.pickTimeButton);
            pickTimeButton.setText(hourOfDay + ":" + minute);
        }
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        Button pickDateButton;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            Log.d("Testing SetDate", "Time set to: year= " + year + " month=" + month +" date= "+day);
            pickDateButton = (Button)getActivity().findViewById(R.id.pickDateButton);
            pickDateButton.setText(year + "." + month + "." + day);
        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}

