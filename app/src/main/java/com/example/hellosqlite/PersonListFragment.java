package com.example.hellosqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PersonListFragment extends Fragment {

    private PersonDataHolder dataHolder;
    private RecyclerView rvPersonList;
    private PersonListAdapter.OnClickListener onClickListener;

    public PersonListFragment(PersonDataHolder dataHolder, PersonListAdapter.OnClickListener onClickListener) {
        this.dataHolder = dataHolder;
        this.onClickListener = onClickListener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        rvPersonList = view.findViewById(R.id.rv_fragment_main_list);

        PersonListAdapter adapter = new PersonListAdapter(dataHolder, onClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext(), RecyclerView.VERTICAL, false);
        rvPersonList.setAdapter(adapter);
        rvPersonList.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
