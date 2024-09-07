package com.easycarehub;



import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class BookAppointment extends AppCompatActivity {



   AutoCompleteTextView city,spec;
    String unm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookapntment);
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        unm  = b.getString("unm");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Book Appointment");
        city = findViewById(R.id.city);
        spec = findViewById(R.id.spec);


        // Spinner Drop down elements
        List<String> citylist = new ArrayList<String>();
        citylist.add("Select City");
        citylist.add("Hyderabad");
        citylist.add("Bengaluru");
        citylist.add("Chennai");
        citylist.add("Kolkata");
        citylist.add("Mumbai");
        citylist.add("Delhi");
        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(BookAppointment.this, android.R.layout.simple_spinner_item, citylist);
        city.setAdapter(adapter);

        List<String> categories = new ArrayList<String>();
        categories.add("Select Speciality");
        categories.add("Audiologist");
        categories.add("Cardiologist");
        categories.add("Dentist");
        categories.add("Dermatologist");
        categories.add("Epidemiologist");
        categories.add("Gynecologist");
        categories.add("Immunologist");
        categories.add("Microbiologist");
        categories.add("Neonatologist");
        categories.add("Neurosurgeon");
        categories.add("Obstetrician");
        categories.add("Orthopedic Surgeon");
        categories.add("Pediatrician");
        categories.add("Physiologist");
        categories.add("Psychiatrist");
        categories.add("Radiologist");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(BookAppointment.this, android.R.layout.simple_spinner_item, categories);
        spec.setAdapter(adapter2);



    }
public void search(View v){

    String city1  = city.getText().toString();
    String catgry  = spec.getText().toString();

    if (null == city1 || city1.trim().length() == 0) {
        city.setError("Select City");
        city.requestFocus();
    }  else if (null == catgry || catgry.trim().length() == 0) {
        spec.setError("Select Specialist ");
        spec.requestFocus();
    }else {
        Intent i = new Intent( BookAppointment.this, HospitalList.class );
        i.putExtra( "city", city1 );
        i.putExtra( "catgry", catgry );
        i.putExtra( "unm", unm );
        startActivity( i );
    }

}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();


            default:
                break;

        }
        return true;
    }




}