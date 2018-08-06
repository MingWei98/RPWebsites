package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    ArrayList<String> alSubCategory;
    ArrayAdapter<String> aaSubCategory;

    @Override
    protected void onPause() {
        super.onPause();

        int CategoryPos = spnCategory.getSelectedItemPosition();
        int SubCategoryPos = spnSubCategory.getSelectedItemPosition();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("CatPos", CategoryPos);
        prefEdit.putInt("SubCatPos", SubCategoryPos);
        prefEdit.commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int CategoryPos = prefs.getInt("CatPos", 0);
        int SubCategoryPos = prefs.getInt("SubCatPos", 0);

        spnCategory.setSelection(CategoryPos);
        spnSubCategory.setSelection(SubCategoryPos);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCategory = findViewById(R.id.spinnerCategory);
        spnSubCategory = findViewById(R.id.spinnerSubCategory);
        btnGo = findViewById(R.id.buttonGo);

        alSubCategory = new ArrayList<>();
        aaSubCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, alSubCategory);
        spnSubCategory.setAdapter(aaSubCategory);

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        // code
                        alSubCategory.clear(); //
                        String[] RP = getResources().getStringArray(R.array.subcategoryRP); //
                        alSubCategory.addAll(Arrays.asList(RP)); //
                        aaSubCategory.notifyDataSetChanged(); //
                        break;

                    case 1:
                        // code
                        alSubCategory.clear();
                        String[] SOI = getResources().getStringArray(R.array.subcategorySOI);
                        alSubCategory.addAll(Arrays.asList(SOI));
                        aaSubCategory.notifyDataSetChanged();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Additional Challenge
                String[][] sites = {
                        {
                                "https://www.rp.edu.sg",
                                "https://www.rp.edu.sg/student-life",


                        },
                        {
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",

                        }
                };

                String url = sites[spnCategory.getSelectedItemPosition()][spnSubCategory.getSelectedItemPosition()];

                Intent intent = new Intent(getBaseContext(), WebActivity.class);

                if(url == sites[0][0]){
                    intent.putExtra("url", url);
                }
                if(url == sites[0][1]){
                    intent.putExtra("url", url);
                }
                if(url == sites[1][0]){
                    intent.putExtra("url", url);
                }
                if(url == sites[1][1]){
                    intent.putExtra("url", url);
                }

//                if(spnCategory.getSelectedItemPosition() == 0 && spnSubCategory.getSelectedItemPosition() == 0){
//                    intent.putExtra("url", "https://www.rp.edu.sg");
//                }
//                if(spnCategory.getSelectedItemPosition() == 0 && spnSubCategory.getSelectedItemPosition() == 1){
//                    intent.putExtra("url", "https://www.rp.edu.sg/student-life");
//                }
//                if(spnCategory.getSelectedItemPosition() == 1 && spnSubCategory.getSelectedItemPosition() == 0){
//                    intent.putExtra("url", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47");
//                }
//                if(spnCategory.getSelectedItemPosition() == 1 && spnSubCategory.getSelectedItemPosition() == 1){
//                    intent.putExtra("url", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12");
//                }
                startActivity(intent);
            }
        });

    }

}
