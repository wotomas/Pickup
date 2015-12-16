package com.example.kim.pickup.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kim.pickup.R;
import com.example.kim.pickup.activity.MainActivity;
import com.example.kim.pickup.controller.MatchController;
import com.parse.ParseUser;

public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String ARG_POSITION = "ProfileFragment";

    private Spinner spinner;
    private TextView userName;
    private Button okButton;
    private String selectedSports = MainActivity.CURRENT_USER_SPORTS;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        userName = (TextView) rootView.findViewById(R.id.userNameTextView);
        userName.setText(MainActivity.CURRENT_USER);


        okButton = (Button) rootView.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                if(currentUser != null) {
                    currentUser.put("sport", selectedSports);
                    currentUser.saveInBackground();
                    MainActivity.CURRENT_USER_SPORTS = selectedSports;
                    MatchController.getInstance().getList(MainActivity.CURRENT_USER_SPORTS, container.getContext());
                }
                Log.d("CloseProfile", "Close Fragment");
                //close
                getActivity().getFragmentManager().popBackStack();
            }
        });
        spinner = (Spinner) rootView.findViewById(R.id.preferedSportsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.preferedSports, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                String item = (String) parent.getItemAtPosition(position);
                selectedSports = item;
                Log.d("testing Spinner", "Spinner item selected is: " + selectedSports + " " + item.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("testing Spinner", "Nothing selected");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
