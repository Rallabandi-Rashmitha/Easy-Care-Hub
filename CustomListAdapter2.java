package com.easycarehub;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter2 extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<DoctorView> hsplist;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter2(Activity activity, List<DoctorView> hsplist) {
		this.activity = activity;
		this.hsplist = hsplist;
	}

	@Override
	public int getCount() {
		return hsplist.size();
	}

	@Override
	public Object getItem(int location) {
		return hsplist.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView hospnm = (TextView) convertView.findViewById(R.id.title);
		TextView catg = (TextView) convertView.findViewById(R.id.catgry);
		TextView city = (TextView) convertView.findViewById(R.id.city);


		// getting movie data for the row
		DoctorView m = hsplist.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// title
		hospnm.setText(m.getDoctname());
		
		// rating
		catg.setText(m.getCatgry());
		city.setText(m.getExp());


		return convertView;
	}

}