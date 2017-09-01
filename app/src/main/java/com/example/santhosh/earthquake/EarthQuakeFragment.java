package com.example.santhosh.earthquake;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.santhosh.earthquake.network.model.EarthquakeModel;
import com.example.santhosh.earthquake.network.presenter.EarthquakeParser;
import com.example.santhosh.earthquake.ui.adapter.EarthquakeAdapter;
import com.example.santhosh.earthquake.ui.util.RecyclerItemClickListener;

import java.util.List;

public class EarthQuakeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = EarthQuakeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressDialog progressDialog;
    private EarthquakeAdapter earthquakeAdapter;
    private List<EarthquakeModel> earthquakeData;

    // URL to get Earthquake JSON
    private static String url = "http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.9&east=-22.4&west=55.2&username=mkoppelman";

    public static EarthQuakeFragment newInstance() {
        return new EarthQuakeFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.earthquake_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        getAsyncData();
        return view;
    }

    public void getAsyncData() {
        new StartAsyncTask().execute();
    }

    /**
     * Async task class to get json by making network call
     */
    private class StartAsyncTask extends AsyncTask<String, String, List<EarthquakeModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected List<EarthquakeModel> doInBackground(String... params) {

            EarthquakeParser jsonParser = new EarthquakeParser();
            List<EarthquakeModel> modelList = jsonParser.getEarthquakeData(url);
            return modelList;
        }

        @Override
        protected void onPostExecute(List<EarthquakeModel> earthquakeModelList) {
            super.onPostExecute(earthquakeModelList);
            // Dismiss the progress dialog
            if (progressDialog.isShowing())
                progressDialog.dismiss();

            earthquakeData = earthquakeModelList;
            final Bundle bundle = new Bundle();


            earthquakeAdapter = new EarthquakeAdapter(getContext(), earthquakeModelList);
            recyclerView.setAdapter(earthquakeAdapter);
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                    new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    final EarthquakeModel model = earthquakeData.get(position);
                    bundle.putDouble("lng",model.getLng());
                    bundle.putDouble("lat",model.getLat());

                    MapFragment map = new MapFragment();
                    map.setArguments(bundle);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, map)
                            .addToBackStack(null)
                            .commit();

                    Toast.makeText(view.getContext(), "EQID : " + model.getEqid(), Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }

    @VisibleForTesting
    public static List<EarthquakeModel> getEarthquakeModelList() {
        EarthquakeParser jsonParser = new EarthquakeParser();
        List<EarthquakeModel> modelList = jsonParser.getEarthquakeData(url);
        return modelList;
    }
}
