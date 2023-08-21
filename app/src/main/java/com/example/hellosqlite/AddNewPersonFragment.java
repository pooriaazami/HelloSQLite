package com.example.hellosqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AddNewPersonFragment extends Fragment {

    private EditText etName;
    private EditText etFamily;
    private EditText etAge;

    private Button btnAdd;
    private Button btnCancel;

    private EventListener eventListener;

    public AddNewPersonFragment(EventListener eventListener) {
        this.eventListener = eventListener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_person, container, false);

        etName = view.findViewById(R.id.et_fragment_add_new_person_name);
        etFamily = view.findViewById(R.id.et_fragment_add_new_person_family);
        etAge = view.findViewById(R.id.et_fragment_add_new_person_age);

        btnAdd = view.findViewById(R.id.btn_fragment_add_new_person_add);
        btnCancel = view.findViewById(R.id.btn_fragment_add_new_person_cancel);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String family = etFamily.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());

            Person person = new Person(-1, name, family, age);
            eventListener.onAdd(person);

            FragmentTransaction removeTransaction = getParentFragmentManager().beginTransaction();
            removeTransaction.remove(this);

            removeTransaction.commit();
        });

        btnCancel.setOnClickListener(v -> {
            eventListener.onCancel();

            FragmentTransaction removeTransaction = getParentFragmentManager().beginTransaction();
            removeTransaction.remove(this);

            removeTransaction.commit();
        });

        return view;
    }

    public interface EventListener {
        public void onAdd(Person p);

        public void onCancel();
    }
}
