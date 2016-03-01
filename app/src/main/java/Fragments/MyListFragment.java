package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adrja.weatherdata.R;

import java.sql.SQLException;
import java.util.ArrayList;

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
}
