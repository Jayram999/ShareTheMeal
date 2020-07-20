package com.example.sharethemeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;


import java.util.ArrayList;

public class Res_Actvites extends AppCompatActivity {
    //Declare the Adapter, RecyclerView and our custom ArrayList
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_actvites);
        mRecyclerView= findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this,getMyList());
        mRecyclerView.setAdapter(myAdapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private ArrayList<Model> getMyList(){
        ArrayList<Model> models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Rice");
        m.setDescription("15");
        m.setId(R.drawable.basmati_rice);
        models.add(m);

        m = new Model();
        m.setTitle("Dal");
        m.setDescription("5");
        m.setId(R.drawable.dal);
        models.add(m);
        m = new Model();
        m.setTitle("Potato Onion Vegetable");
        m.setDescription("6");
        m.setId(R.drawable.vegetable);
        models.add(m);
        m = new Model();
        m.setTitle("Chapati");
        m.setDescription("20");
        m.setId(R.drawable.chapati);
        models.add(m);
        return models;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
