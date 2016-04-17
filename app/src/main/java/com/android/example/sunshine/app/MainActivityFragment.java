package com.android.example.sunshine.app;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

	RequestQueue rq;
	String weatherURL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Panipat&mode=json&units=metric&cnt=7&appid=c7497c25dd8765dee1c72d4894b12198";
	public MainActivityFragment() {
	}


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		inflater.inflate(R.menu.forecastfragment, menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int id = item.getItemId();
		if(id == R.id.action_refresh){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView =  inflater.inflate(R.layout.fragment_main, container, false);



		final ArrayList<String> fakeData = new ArrayList<String>();
		{
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
			fakeData.add("Panipat 32");
		}


		final ArrayList<String> realdata = new ArrayList<String>();
		realdata.add("Temp");


		//listView.setAdapter(mAdapter);
		//rq = VolleySingleton.getInstance().getRequestQueue();

		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview,fakeData);
		ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);

		//ArrayAdapter<String> qAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview,realdata);
		listView.setAdapter(mAdapter);
		return rootView;
	}
	public class FetchWeatherTask extends AsyncTask<Void, Void, Void> {

		private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

		@Override
		protected Void doInBackground(Void... params) {
			// These two need to be declared outside the try/catch
			// so that they can be closed in the finally block.
			HttpURLConnection urlConnection = null;
			BufferedReader reader = null;

			// Will contain the raw JSON response as a string.
			String forecastJsonStr = null;

			try {
				// Construct the URL for the OpenWeatherMap query
				// Possible parameters are avaiable at OWM's forecast API page, at
				// http://openweathermap.org/API#forecast
				String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
				String apiKey = "&APPID=" + "c7497c25dd8765dee1c72d4894b12198";
				URL url = new URL(baseUrl.concat(apiKey));

				// Create the request to OpenWeatherMap, and open the connection
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.connect();

				// Read the input stream into a String
				InputStream inputStream = urlConnection.getInputStream();
				StringBuffer buffer = new StringBuffer();
				if (inputStream == null) {
					// Nothing to do.
					return null;
				}
				reader = new BufferedReader(new InputStreamReader(inputStream));

				String line;
				while ((line = reader.readLine()) != null) {
					// Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
					// But it does make debugging a *lot* easier if you print out the completed
					// buffer for debugging.
					buffer.append(line + "\n");
				}

				if (buffer.length() == 0) {
					// Stream was empty.  No point in parsing.
					return null;
				}
				forecastJsonStr = buffer.toString();
			} catch (IOException e) {
				Log.e(LOG_TAG, "Error ", e);
				// If the code didn't successfully get the weather data, there's no point in attemping
				// to parse it.
				return null;
			} finally {
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (final IOException e) {
						Log.e(LOG_TAG, "Error closing stream", e);
					}
				}
			}
			return null;
		}
	}
}
