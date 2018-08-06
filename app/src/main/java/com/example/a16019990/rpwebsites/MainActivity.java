package com.example.a16019990.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spnCategory;
    Spinner spnSubCategory;
    Button btnGo;
    ArrayList<String> alSubCat;
    ArrayAdapter<String> aaSubCat;
    int catID, subCatId;


    String [][] sites = {
            {
                    "https://rp.edu.sg",
                    "https://www.rp.edu.sg/student-life",
            },
            {
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnCategory = findViewById(R.id.spinner);
        spnSubCategory = findViewById(R.id.spinner2);
        btnGo = findViewById(R.id.buttonGo);


        alSubCat = new ArrayList<>();
        aaSubCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alSubCat);
        spnSubCategory.setAdapter(aaSubCat);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        catID = prefs.getInt("catID",0);
        subCatId = prefs.getInt("subCatId", 0);
        spnCategory.setSelection(catID);
        spnSubCategory.setSelection(subCatId);


        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catID = position;
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("catID", catID);
                prefEdit.commit();
                switch(position) {
                    case 0:
                        alSubCat.clear();
                        String[] strCat = getResources().getStringArray(R.array.subcategoryRP);
                        alSubCat.addAll(Arrays.asList(strCat));
                        aaSubCat.notifyDataSetChanged();
                        break;
                    case 1:
                        alSubCat.clear();
                        strCat = getResources().getStringArray(R.array.subcategorySOI);
                        alSubCat.addAll(Arrays.asList(strCat));
                        aaSubCat.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subCatId = position;
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("subCatID", subCatId);
                prefEdit.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = sites[spnCategory.getSelectedItemPosition()][spnSubCategory.getSelectedItemPosition()];
                Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

}
