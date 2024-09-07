package com.easycarehub;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UserHome extends AppCompatActivity {


        CardView bkapntmnt,viewapntmnts;
        Intent i1,i2;
        String unm;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_home);
            setTitle("EasyCareHub");
            Intent intent=getIntent();
            Bundle b=intent.getExtras();
            if(b!=null) {

                unm = b.getString("unm");
            }


            bkapntmnt = (CardView) findViewById(R.id.bkapntmnt);
            i1 = new Intent(this,BookAppointment.class);
            i1.putExtra( "unm",unm );
            bkapntmnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(i1);
                }
            });

/*
            viewslots= (CardView) findViewById(R.id.viewslots);
            i2 = new Intent(this,ViewBookings.class);
            i2.putExtra( "unm",unm );
            viewslots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(i2);
                }
            });*/


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Executing the specific operations based on chosen of particular switch case
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                break;

            default:
                break;

        }


        return true;
    }



}
