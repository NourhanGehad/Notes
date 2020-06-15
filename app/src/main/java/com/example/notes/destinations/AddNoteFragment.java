package com.example.notes.destinations;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.AppActivity;
import com.example.notes.DBHandler.SQLiteHelper;
import com.example.notes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {

    public AddNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_view);
        navBar.setVisibility(View.VISIBLE);

        final EditText etTitle = view.findViewById(R.id.title);
        final EditText etDescription = view.findViewById(R.id.description);
        Button btnSave = view.findViewById(R.id.save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();

              AppActivity.sqLiteHelper.insertData(title, description);
//          new AsyncTaskExample(AppActivity.sqLiteHelper, title, description).execute();
                Navigation.findNavController(view).navigateUp();
                Toast.makeText(getActivity(),"Note added successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private class AsyncTaskExample extends AsyncTask<Void,Void, Boolean> {
        SQLiteHelper sqLiteHelper;
        String title;
        String description;

        public AsyncTaskExample(SQLiteHelper sqLiteHelper, String title, String description) {
            this.sqLiteHelper = sqLiteHelper;
            this.title = title;
            this.description = description;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            sqLiteHelper.insertData(title, description);
            return true;
        }
    }
}
