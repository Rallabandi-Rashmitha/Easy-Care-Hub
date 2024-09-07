package com.easycarehub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HospitalList extends AppCompatActivity implements AdapterView.OnItemClickListener{
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog  pDialog1, pDialog2;
	JSONParser jsonParser = new JSONParser();

	// Movies json url
	private static final String url ="http://orangewebtools.com/HealthMonitoring/hospitalsearches.php";
	private ProgressDialog pDialog;
	String unm,city,catgry;
	private List<HospitalView> productList = new ArrayList<HospitalView>();
	private ListView listView;
	private CustomListAdapter adapter;
	JSONArray json1 = null, json2 = null;
	TextView tv;
int sts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hospital_items);
		tv=(TextView)findViewById(R.id.textView);
		tv.setVisibility(View.GONE);
		listView = (ListView) findViewById(R.id.list);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent=getIntent();
		Bundle b=intent.getExtras();
		city  = b.getString("city");
		catgry  = b.getString("catgry");
		unm= b.getString("unm");
		setTitle("List of Hospitals");
		listView.setOnItemClickListener(this);




new hosplist().execute();

	}

	class hosplist extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog1 = new ProgressDialog(HospitalList.this);
			pDialog1.setMessage("Loading ...");
			pDialog1.setIndeterminate(false);
			pDialog1.setCancelable(false);
			pDialog1.show();
		}

		/**
		 * getting Inbox JSON
		 */
		protected String doInBackground(String... args) {
			// Building Parameters
			String s = null;
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("city",city));
			params.add(new BasicNameValuePair("catgry",catgry));
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			// Check your log cat for JSON reponse
			Log.d("JSON: ", json.toString());

			try {
				Random rand = new Random();

                  if(json.getInt("success")==1) {
					  //json.getString("sql");
					  json2 = json.getJSONArray("hdetail");
					  // looping through All messages
					  for (int i = 0; i < json2.length(); i++) {
						  int rand_int1 = rand.nextInt(100);
						  JSONObject c = json2.getJSONObject(i);
						  // Storing each json item in variable
						  HospitalView m = new HospitalView();
						  m.setHospname(c.getString("hospname"));
						  m.setThumbnailUrl(c.getString("imgurl"));
						  m.setCatgry(catgry);
						  m.setCity(city);
						  m.setHospid(c.getString("hospid"));
						  m.setBedsava("Available Beds : "+rand_int1 );
						  productList.add(m);

					  }
sts=1;
				  }else{

					  sts=0;
				  }
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 **/
		protected void onPostExecute(String file_url) {

			adapter = new CustomListAdapter(HospitalList.this, productList);
			listView.setAdapter(adapter);
			pDialog1.dismiss();
if(sts==0){
	tv.setVisibility(View.VISIBLE);
	tv.setText("No Records Found");


}



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
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		HospitalView p=(HospitalView)productList.get(position);
		String hid=p.getHospid();
		String hnm=p.getHospname();
		Intent intent=new Intent(HospitalList.this,DoctorsList.class);
		intent.putExtra("hid",hid);
		intent.putExtra("catgry",catgry);
		intent.putExtra("unm",unm);
		intent.putExtra("hnm",hnm);
		intent.putExtra("city",city);
		startActivity(intent);

	}


}
