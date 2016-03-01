package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adrja.weatherdata.R;

import java.util.ArrayList;

import Models.WeatherData;

/**
 * Created by adrja on 01.03.2016.
 */
public class MyListFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ArrayList<WeatherData> værDataItems =null;
    private ListView myListView = null;
    private VærDataKilde dataSource = null;
    private WeatherData valgVærData = null;

    @Override
    public View OnCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.mylistfragment, container, false);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
