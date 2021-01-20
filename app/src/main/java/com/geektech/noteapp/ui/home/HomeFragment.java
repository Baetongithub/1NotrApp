package com.geektech.noteapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.noteapp.NoteAdapter;
import com.geektech.noteapp.R;
import com.geektech.noteapp.models.Note;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter(this.getContext());
        String date = DateFormat.getDateTimeInstance().format(new Date());
        List<Note> list = new ArrayList<>();
        Note hp = new Note("HP", date);
        list.add(hp);
        Note asus = new Note("Asus", date);
        list.add(asus);
        Note acer = new Note("Acer", date);
        list.add(acer);
        Note lenovo = new Note("Lenovo", date);
        list.add(lenovo);
        Note xiaomi = new Note("Xiaomi", date);
        list.add(xiaomi);
        Note macbook = new Note("MacBook", date);
        list.add(macbook);
        Note dell = new Note("Dell", date);
        list.add(dell);
        Note zenbook = new Note("ZenBook", date);
        list.add(zenbook);
        Note rog = new Note("ROG", date);
        list.add(rog);
        Note vivobook = new Note("VivoBook", date);
        list.add(vivobook);
        Note laptop = new Note("TUFGaming", date);
        list.add(laptop);
        adapter.addList(list);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        view.findViewById(R.id.fab).setOnClickListener(v ->
                openForm());

        setFragmentListener();
        initList();
    }

    private void initList() {
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("rk_form",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Note note = (Note) result.getSerializable("note");
                        adapter.addItem(note);
                    }
                });
    }

    private void openForm() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.form_fragment);

    }


}