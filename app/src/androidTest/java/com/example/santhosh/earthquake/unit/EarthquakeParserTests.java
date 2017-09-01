package com.example.santhosh.earthquake.unit;

import android.os.AsyncTask;
import android.test.InstrumentationTestCase;

import com.example.santhosh.earthquake.network.model.EarthquakeModel;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.example.santhosh.earthquake.EarthQuakeFragment.getEarthquakeModelList;

public class EarthquakeParserTests extends InstrumentationTestCase {

    CountDownLatch signal = new CountDownLatch(1);
    private List<EarthquakeModel> parsedData;

    private void getAsyncContents() throws Throwable {
        final AsyncTask<String, String, List<EarthquakeModel>> asyncTask =
                new AsyncTask<String, String, List<EarthquakeModel>>() {
                    @Override
                    protected List<EarthquakeModel> doInBackground(String... params) {
                        parsedData = getEarthquakeModelList();
                        return parsedData;
                    }

                    @Override
                    protected void onPostExecute(List<EarthquakeModel> earthquakeModelList) {
                        super.onPostExecute(earthquakeModelList);
                        signal.countDown();
                    }
                };


        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                asyncTask.execute();
            }
        });

        signal.await(30, TimeUnit.SECONDS);

        assertNotNull(parsedData);
    }

    public void testEarthquakeDateTime() throws Throwable {
        getAsyncContents();

        assertEquals(parsedData.get(0).getDatetime(), "2011-03-11 04:46:23");
        assertEquals(parsedData.get(1).getDatetime(), "2012-04-11 06:38:37");
        assertEquals(parsedData.get(2).getDatetime(), "2007-09-12 09:10:26");
        assertEquals(parsedData.get(3).getDatetime(), "2012-04-11 08:43:09");
        assertEquals(parsedData.get(4).getDatetime(), "2007-04-01 18:39:56");
        assertEquals(parsedData.get(5).getDatetime(), "2017-01-22 04:32:20");
    }

    public void testEarthquakeEqid() throws Throwable {
        getAsyncContents();

        assertEquals(parsedData.get(0).getEqid(), "c0001xgp");
        assertEquals(parsedData.get(1).getEqid(), "c000905e");
        assertEquals(parsedData.get(2).getEqid(), "2007hear");
        assertEquals(parsedData.get(3).getEqid(), "c00090da");
        assertEquals(parsedData.get(4).getEqid(), "2007aqbk");
        assertEquals(parsedData.get(5).getEqid(), "us10007uph");
    }

    public void testEarthquakeDepth() throws Throwable {
        getAsyncContents();

        assertEquals(parsedData.get(0).getDepth(), 24.4, 24);
        assertEquals(parsedData.get(1).getDepth(), 22.9, 22);
        assertEquals(parsedData.get(2).getDepth(), 30.0, 30);
        assertEquals(parsedData.get(3).getDepth(), 16.4, 16);
        assertEquals(parsedData.get(4).getDepth(), 10.0, 10);
        assertEquals(parsedData.get(5).getDepth(), 136.0, 136);
    }

    public void testEarthquakeMagnitude() throws Throwable {
        getAsyncContents();

        assertEquals(parsedData.get(0).getMagnitude(), 8.8);
        assertEquals(parsedData.get(1).getMagnitude(), 8.6);
        assertEquals(parsedData.get(2).getMagnitude(), 8.4);
        assertEquals(parsedData.get(3).getMagnitude(), 8.2);
        assertEquals(parsedData.get(4).getMagnitude(), 8.0);
        assertEquals(parsedData.get(5).getMagnitude(), 7.9);
        assertEquals(parsedData.get(6).getMagnitude(), 7.9);
        assertEquals(parsedData.get(7).getMagnitude(), 7.9);
        assertEquals(parsedData.get(8).getMagnitude(), 7.8);
        assertEquals(parsedData.get(9).getMagnitude(), 7.8);
    }
}