package com.example.notes.destinations;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.NoteModel;
import com.example.notes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.notes.AppActivity.sqLiteHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends Fragment {

    public EditNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_view);
        navBar.setVisibility(View.GONE);

        final EditText etTitle = view.findViewById(R.id.title);
        final EditText etDescription = view.findViewById(R.id.description);
        Button btnSave = view.findViewById(R.id.save);

        final NoteModel note = EditNoteFragmentArgs.fromBundle(getArguments()).getNoteToEdit();

        if(note == null){
            Navigation.findNavController(view).navigateUp();
            Toast.makeText(getActivity(),"Note Not Found", Toast.LENGTH_SHORT).show();
        } else {
            etTitle.setText(note.getTitle());
            etDescription.setText(note.getDescription());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                sqLiteHelper.updateData(title, description, note.getId());
                Navigation.findNavController(view).navigateUp();
                Toast.makeText(getActivity(),"Note edited successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
