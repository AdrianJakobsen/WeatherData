package Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adrja.weatherdata.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.WeatherData;
import SQL.WeatherDataSource;

/**
 * Created by adrja on 01.03.2016.
 */
public class MyListFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<WeatherData> weatherDataItems = null;
    private ListView myListView = null;
    private WeatherDataSource weatherDataSource = null;
    private WeatherData chosenWeatherData = null;
    private boolean downloadIsInProgress = false;
    private Context context;

    public MyListFragment(final Context context){
        this.context = context;
        weatherDataSource = new WeatherDataSource(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mylistfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button  btnDwnl = (Button)this.getActivity().findViewById(R.id.btnDownloadStartStop);
        btnDwnl.setOnClickListener(this);

        Button btnShowData = (Button)this.getActivity().findViewById(R.id.btnShowData);
        btnShowData.setOnClickListener(this);

        myListView = (ListView)getActivity().findViewById(R.id.listviewWeatherData);
        myListView.setOnClickListener(this);

        weatherDataSource = new WeatherDataSource(this.getActivity());
        try {
            weatherDataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        weatherDataItems = (ArrayList<WeatherData>) weatherDataSource.getAllData();
        ArrayAdapter<WeatherData> adapter = new ArrayAdapter<WeatherData>(this.getActivity(), android.R.layout.simple_list_item_1
        ,weatherDataItems);
        myListView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDownloadStartStop:
                if(downloadIsInProgress){
                    //stopDownload();
                    downloadIsInProgress = false;
                }else{
                    //startDownload();
                    downloadIsInProgress = true;
                }
                break;
            case R.id.btnShowData:
                // TODO: 01.03.2016 send data til view eller noe.
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chosenWeatherData = weatherDataItems.get(position);

        EditText temperature = (EditText)getActivity().findViewById(R.id.etShowDataTemperature);
        temperature.setText((int) chosenWeatherData.getTemperature());
    }

    private void startDownload(){
        String urlString = "http://kark.hin.no/~wfa/fag/android/2016/weather/vstations.php";
        HttpURLConnection httpURLConnection = null;
        try{
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                addDataToDB(httpURLConnection.getInputStream());
            }else{
                Toast toast = Toast.makeText(context,"Could not connect to server",Toast.LENGTH_SHORT);
                toast.show();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDataToDB(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        List inputList = gson.fromJson(reader, new TypeToken<List<WeatherData>>(){}.getType());
        try {
//            weatherDataSource.createWeatherData()
            Toast toast = Toast.makeText(context,"Could not connect to server",Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
