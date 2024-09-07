package com.easycarehub;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class User_Registration extends AppCompatActivity {
    TextView reg;
    private ProgressDialog pDialog;
    JSONParser parser = new JSONParser();
    private static String url_Register = "http://orangewebtools.com/HealthMonitoring/patientreg.php";
String name1,uid1,pwd1,mno1,email1;
    int sts = 0;
    TextInputEditText name,uid,pwd,mno,email;
    LinearLayout coordinatorLayout;

    /**
     * Invoking when the User_Registration activity executes.
     * Invoking the user_register layout.
     * Creating the objects for TextInputEditText of name,userid,password,email and mobile number.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        setTitle("User Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.name);
        uid = findViewById(R.id.uid);
        pwd = findViewById(R.id.pwd);
        mno = findViewById(R.id.mno);


        coordinatorLayout = (LinearLayout) findViewById(R.id.lr);
       }

    /**
     * Retriving the registration form values.
     * Performing the registration form validations.**/


       public void register(View view){


        name1=name.getText().toString();
           uid1=uid.getText().toString();
           pwd1=pwd.getText().toString();
           mno1=mno.getText().toString();


           if (null == name1 || name1.trim().length() == 0) {
               name.setError("Enter Your Name");
               name.requestFocus();
           }  else if (null == uid1 || uid1.trim().length() == 0) {
               uid.setError("Enter Username ");
               uid.requestFocus();
           }
           else if (null == pwd1 || pwd1.trim().length() == 0) {
               pwd.setError("Enter  Password");
               pwd.requestFocus();
           }
           else if (null == mno1 || mno1.trim().length() == 0) {
               mno.setError("Enter  Mobile No.");
               mno.requestFocus();
           } else if (mno1.length() != 10) {
               mno.setError("Invalid Phone Number.");
               mno.requestFocus();
           }
        else{
            new Signup().execute();
        }





}

    /**
     * Passing the registration form values to user_register PHP file,
     * for storing into database.
     */
    class Signup extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(User_Registration.this);
            pDialog.setMessage("Registering...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nm",name1));
            params.add(new BasicNameValuePair("unm",uid1));
            params.add(new BasicNameValuePair("pwd",pwd1));
            params.add(new BasicNameValuePair("mno",mno1));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = parser.makeHttpRequest(url_Register,
                    "POST", params);

            // check log cat for response
            Log.d("Response for Register=", json.toString());

            // check for success tag
            try {
                int success = json.getInt("success");


                if (success == 1) {

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);

                    sts=1;
                    finish();
                } else {
                    sts=2;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if(sts==1) {

                Toast.makeText(getApplicationContext(), "Registered Successfully..!", Toast.LENGTH_SHORT).show();
            }

            if(sts==2) {

                Toast.makeText(getApplicationContext(), "Username already available..!", Toast.LENGTH_SHORT).show();
            }

            pDialog.dismiss();
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
