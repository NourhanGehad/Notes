package com.example.notes.destinations;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_view);
        navBar.setVisibility(View.VISIBLE);

        final SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);
        String retrievedUsername = mPrefs.getString("username", "NO NAME");

        final EditText etUsername = view.findViewById(R.id.username);
        etUsername.setText(retrievedUsername);
        Button btnSave = view.findViewById(R.id.save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typedUsername = etUsername.getText().toString();
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("username", typedUsername);
                editor.commit();
                Toast.makeText(getActivity(),"username saved successfully", Toast.LENGTH_SHORT).show();

            }
        });




    }

//    prefsEditor.putString("MyObject", json);
//prefsEditor.commit();
}
