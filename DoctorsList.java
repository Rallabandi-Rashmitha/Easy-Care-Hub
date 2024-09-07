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

public class DoctorsList extends AppCompatActivity implements AdapterView.OnItemClickListener{
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog  pDialog1, pDialog2;
	JSONParser jsonParser = new JSONParser();
	JSONParser jsonParser1 = new JSONParser();
	// Movies json url
	private static final String url ="http://orangewebtools.com/HealthMonitoring/doctorslist.php";
	private ProgressDialog pDialog;
	String unm,hid,catgry,hnm,city;
	private List<DoctorView> doctList = new ArrayList<DoctorView>();
	private ListView listView;
	private CustomListAdapter2 adapter;
	JSONArray json1 = null, json2 = null;
	String at;
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
		hid  = b.getString("hid");
		catgry  = b.getString("catgry");
		unm= b.getString("unm");
		hnm= b.getString("hnm");
		city=b.getString("city");
		setTitle("List of Doctors");
		listView.setOnItemClickListener(this);




new doctorslist().execute();

	}

	class doctorslist extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog1 = new ProgressDialog(DoctorsList.this);
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
			params.add(new BasicNameValuePair("hid",hid));
			params.add(new BasicNameValuePair("catgry",catgry));
			// getting JSON string from URL
			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			// Check your log cat for JSON reponse
			Log.d("JSON: ", json.toString());

			try {
				doctList.clear();

                  if(json.getInt("success")==1) {
					  //json.getString("sql");
					  json2 = json.getJSONArray("ddetail");
					  // looping through All messages
					  for (int i = 0; i < json2.length(); i++) {
						  JSONObject c = json2.getJSONObject(i);
						  // Storing each json item in variable
						 at=c.getString("days")+" | "+c.getString("times");
						  DoctorView m = new DoctorView();
						  m.setDoctname("Dr. "+c.getString("docname"));
						  m.setThumbnailUrl(c.getString("imgurl"));
						  m.setCatgry(catgry);
						  m.setExp(at + "\n\n" + "Experience: " + c.getString("exp")+" yrs");
						  m.setDoctid(c.getString("docid"));
						  doctList.add(m);

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

			adapter = new CustomListAdapter2(DoctorsList.this, doctList);
			listView.setAdapter(adapter);
			pDialog1.dismiss();
if(sts==0){
	tv.setVisibility(View.VISIBLE);
	tv.setText("No Records Found");
	//Toast.makeText(MovieSearchItems.this,"No Records Found",Toast.LENGTH_SHORT).show();

}
			// updating UI from Background Thread


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

		DoctorView p=(DoctorView)doctList.get(position);
		String did=p.getDoctid();
		String dnm=p.getDoctname();
		/*Intent intent=new Intent(DoctorsList.this,Appointment.class);
		intent.putExtra("hid",hid);
		intent.putExtra("did",did);
		intent.putExtra("catgry",catgry);
		intent.putExtra("unm",unm);
		intent.putExtra("dnm",dnm);
		intent.putExtra("hnm",hnm);
		intent.putExtra("city",city);
		intent.putExtra("at",at);
		startActivity(intent);*/

	}


}
