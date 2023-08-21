package com.example.hellosqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PersonListAdapter.OnClickListener, AddNewPersonFragment.EventListener {
    private PersonDataHolder dataHolder;
    private Button btnAddNewPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHolder = new PersonDataHolder(this);

        FragmentTransaction addListFragment = getSupportFragmentManager().beginTransaction().add(R.id.fl_main, new PersonListFragment(dataHolder, this));

        addListFragment.commit();

        btnAddNewPerson = findViewById(R.id.btn_main_add_new_person);
        btnAddNewPerson.setOnClickListener(v -> {
            FragmentTransaction addNewPersonFragment = getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.fl_main, new AddNewPersonFragment(this));
            addNewPersonFragment.commit();
        });

    }

    @Override
    public void onRemove(int idx) {
        dataHolder.remove(idx);
    }

    @Override
    public void onAdd(Person p) {
        dataHolder.addPerson(p);
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "You clicked cancel", Toast.LENGTH_SHORT).show();
    }
}