package com.ada.edwingsantos.yqlvivian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    ArrayList<Results> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        listView = (ListView) findViewById(R.id.listView);

        String searchTerm = getIntent().getStringExtra("SearchTerm");
        String zipCode = getIntent().getStringExtra("postalCode");

        Downloader downloader = new Downloader(this);
        downloader.execute(searchTerm, zipCode);

    }

    public void drawListView(ArrayList<Results> resultsArrayList){
        arrayList = new ArrayList<>();
        arrayList = resultsArrayList;
        ResultsAdapter adapter = new ResultsAdapter(this, resultsArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Results result = arrayList.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
