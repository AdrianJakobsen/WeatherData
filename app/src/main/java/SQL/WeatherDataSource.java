package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.WeatherData;

/**
 * Created by adrja on 01.03.2016.
 */
public class WeatherDataSource {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns;

    public WeatherDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
        allColumns = new String[] {"station_name","station_position", "timestamp", "temperature", "pressure", "humidity"};
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public WeatherData createWeatherData (String stationName, String stationPosition, Date timestamp, double temperature,
                                          double pressure, double humidity)  {

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_STATION_NAME, stationName);
        values.put(SQLiteHelper.KEY_STATION_POSITION, stationPosition);
        values.put(SQLiteHelper.KEY_TIMESTAMP, timestamp.toString());
        values.put(SQLiteHelper.KEY_TEMPERATURE, temperature);
        values.put(SQLiteHelper.KEY_PRESSURE, pressure);
        values.put(SQLiteHelper.KEY_HUMIDITY, humidity);

        long insertId = database.insert(SQLiteHelper.WEATHER_TABLE, null, values);
        Cursor cursor = database.query(SQLiteHelper.WEATHER_TABLE, allColumns, SQLiteHelper.KEY_ID + " = " +
        insertId, null, null, null, null);

        WeatherData newWeatherData = cursorToContact(cursor);
        cursor.close();
        return  newWeatherData;
    }

    // TODO: 01.03.2016 do this shit
    private WeatherData cursorToContact(Cursor cursor) {
        return null;
    }

    public ArrayList<WeatherData> getAllData() {
        return null;
    }
}
