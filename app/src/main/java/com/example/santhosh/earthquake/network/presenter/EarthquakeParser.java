package com.example.santhosh.earthquake.network.presenter;

import com.example.santhosh.earthquake.network.model.EarthquakeModel;
import com.example.santhosh.earthquake.network.utils.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeParser {

    private List<EarthquakeModel> earthquakeModelList;

    // JSON Node names
    private static final String TAG_EARTHQUAKES = "earthquakes";
    private static final String TAG_DATETIME = "datetime";
    private static final String TAG_DEPTH = "depth";
    private static final String TAG_LONGITUDE = "lng";
    private static final String TAG_LATITUDE = "lat";
    private static final String TAG_SOURCE = "src";
    private static final String TAG_EQUID = "eqid";
    private static final String TAG_MAGNITUDE = "magnitude";

    public List<EarthquakeModel> getEarthquakeData(String url) {
        String parsedData;
        ServiceHandler serviceHandler = new ServiceHandler();
        parsedData = serviceHandler.getServiceData(url);

        if (serviceHandler.toString() != null) {
            try {
                earthquakeModelList = new ArrayList<>();
                JSONObject jsonObj = new JSONObject(parsedData);
                JSONArray jsonArray = jsonObj.getJSONArray(TAG_EARTHQUAKES);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    String source = c.getString(TAG_SOURCE);
                    int depth = c.getInt(TAG_DEPTH);
                    String dateTime = c.getString(TAG_DATETIME);
                    Double magnitude = c.getDouble(TAG_MAGNITUDE);
                    String equid = c.getString(TAG_EQUID);
                    Double latitude = c.getDouble(TAG_LATITUDE);
                    Double longitude = c.getDouble(TAG_LONGITUDE);

                    EarthquakeModel model = new EarthquakeModel();
                    model.setSrc(source);
                    model.setDepth(depth);
                    model.setDatetime(dateTime);
                    model.setMagnitude(magnitude);
                    model.setEqid(equid);
                    model.setLat(latitude);
                    model.setLng(longitude);

                    earthquakeModelList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return earthquakeModelList;
    }
}