package com.example.notes.destinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.AppActivity;
import com.example.notes.NoteModel;
import com.example.notes.NotesAdapter;
import com.example.notes.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_nav_view);
        navBar.setVisibility(View.VISIBLE);

        RecyclerView rv_notes = view.findViewById(R.id.rv_notes);

        ArrayList<NoteModel> notes = AppActivity.sqLiteHelper.getNotes();

        NotesAdapter adapter = new NotesAdapter(notes, new NotesAdapter.SelectionPropagator() {
            @Override
            public void propagateSelection(NoteModel note) {
                NavDirections action = HomeFragmentDirections.actionFromHomeToEdit().setNoteToEdit(note);
                Navigation.findNavController(view).navigate(action);
            }
        });
        rv_notes.setAdapter(adapter);
        rv_notes.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
    }
}
