package com.example.divyanshu.distbwpointseditnew;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends FragmentActivity {

    GoogleMap map;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration;
    // int distanceMatrix[][] = new int[5][5];
    double distarray[] = new double[15];
    int k = 0;
    TextView distanceMat;
    StringBuilder stringbuilder = new StringBuilder();
    String finaldist = "";
   // AutoCompleteTextView at;
   // int sample[] = (2,3,4);

    static int order[] = new int[5];
    static int visit[] = new int[5];
    static double gmin = 1000000;
    static double b[][] = new double[5][5];
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDistanceDuration = (TextView) findViewById(R.id.tv_distance_time);
        distanceMat = (TextView)findViewById(R.id.distanceMat);
        b1 = (Button) findViewById(R.id.button);
      //  ArrayAdapter<int> adap = new ArrayAdapter<int>(getApplicationContext(),Android.R);
/*        for(int i=0;i<5;i++)
            order[i]=0;

       final int a = order[0];
       final int b = order[1];
       final int c = order[2];
       final int d = order[3];
       final int e = order[4]; */

       final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Wait for 5 sec. Then click OK");
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
     /*   builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }*/
   // });

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting Map for the SupportMapFragment
        map = fm.getMap();

        // Enable MyLocation Button in the Map
        map.setMyLocationEnabled(true);

        // Setting onclick event listener for the map
        map.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Already five locations
                if (markerPoints.size() > 4) {
                    markerPoints.clear();
                    map.clear();
                }

                // Adding new item to the ArrayList
                markerPoints.add(point);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_marker_a));

                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_marker_b));
                } else if (markerPoints.size() == 3) {
                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_marker_c));
                } else if (markerPoints.size() == 4) {
                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.green_marker_d));
                } else if (markerPoints.size() == 5) {
                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker_e));
                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }


                // Add new marker to the Google Map Android API V2
                map.addMarker(options);

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 5) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    LatLng origin2 = markerPoints.get(0);
                    LatLng dest2 = markerPoints.get(2);

                    LatLng origin3 = markerPoints.get(0);
                    LatLng dest3 = markerPoints.get(3);

                    LatLng origin4 = markerPoints.get(0);
                    LatLng dest4 = markerPoints.get(4);

                    LatLng origin5 = markerPoints.get(1);
                    LatLng dest5 = markerPoints.get(2);

                    LatLng origin6 = markerPoints.get(1);
                    LatLng dest6 = markerPoints.get(3);

                    LatLng origin7 = markerPoints.get(1);
                    LatLng dest7 = markerPoints.get(4);

                    LatLng origin8 = markerPoints.get(2);
                    LatLng dest8 = markerPoints.get(3);

                    LatLng origin9 = markerPoints.get(2);
                    LatLng dest9 = markerPoints.get(4);

                    LatLng origin10 = markerPoints.get(3);
                    LatLng dest10 = markerPoints.get(4);


                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);
                    String url2 = getDirectionsUrl(origin2, dest2);
                    String url3 = getDirectionsUrl(origin3, dest3);
                    String url4 = getDirectionsUrl(origin4, dest4);
                    String url5 = getDirectionsUrl(origin5, dest5);
                    String url6 = getDirectionsUrl(origin6, dest6);
                    String url7 = getDirectionsUrl(origin7, dest7);
                    String url8 = getDirectionsUrl(origin8, dest8);
                    String url9 = getDirectionsUrl(origin9, dest9);
                    String url10 = getDirectionsUrl(origin10, dest10);

                    DownloadTask downloadTask = new DownloadTask();
                    downloadTask.execute(url);// Start downloading json data from Google Directions API

                    DownloadTask downloadTask2 = new DownloadTask();
                    downloadTask2.execute(url2);

                    DownloadTask downloadTask3 = new DownloadTask();
                    downloadTask3.execute(url3);

                    DownloadTask downloadTask4 = new DownloadTask();
                    downloadTask4.execute(url4);

                    DownloadTask downloadTask5 = new DownloadTask();
                    downloadTask5.execute(url5);

                    DownloadTask downloadTask6 = new DownloadTask();
                    downloadTask6.execute(url6);

                    DownloadTask downloadTask7 = new DownloadTask();
                    downloadTask7.execute(url7);

                    DownloadTask downloadTask8 = new DownloadTask();
                    downloadTask8.execute(url8);

                    DownloadTask downloadTask9 = new DownloadTask();
                    downloadTask9.execute(url9);

                    DownloadTask downloadTask10 = new DownloadTask();
                    downloadTask10.execute(url10);
                }
            }
        });

    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                JSONParser parser = new JSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            ArrayList<PolylineOptions> lineOp = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            PolylineOptions lineOptions = null;

            if(result.size()<1){
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    if(j==0){    // Get distance from the list
                        distance = (String)point.get("distance");
                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String)point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(2);
                    lineOptions.color(Color.RED);

            }

            //tvDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);
            String str = distance;
            String[] splited = str.split("\\s+");

            String split_one =splited[0];
            double number = Double.parseDouble(split_one);
            distarray[k] = number; // Drawing polyline in the Google Map for the i-th route

            if(k>9){
            map.addPolyline(lineOptions);}

            k++;
        }
    }

    public void calculate(View v){
     //   SystemClock.sleep(3000);
        int it = 0;
        for (int i=0;i<5;i++) { visit[i] = -1; order[i] = -1; }
        visit[4] = 4;
        for (int i = 0;i < 5;i++)
            for (int j = i;j < 5;j++)
                b[i][j] = b[j][i] = ((i == j) ? 0.0 : distarray[it++]);
        recurse(-1, 0, 0);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        df.format(gmin);
        stringbuilder.append("Distance = " + gmin + "        ");
        for (int i = 0;i < 5;i++) {
            if (order[i] == 0)
              stringbuilder.append("A" + "        ");
            else if(order[i] == 1)
              stringbuilder.append("B" + "        ");
            else if(order[i] == 2)
                stringbuilder.append("C" + "        ");
            else if(order[i] == 3)
                stringbuilder.append("D" + "        ");
            else if(order[i] == 4)
                stringbuilder.append("E" + "        ");
        }
        finaldist = stringbuilder.toString();
        distanceMat.setText(finaldist);

    }

    public static void recurse(int prev, int curr, double sum) {
        if (curr == 4)
            if (sum + b[prev][4]< gmin)
            {
                gmin = sum + b[prev][4];
                for (int i = 0;i < 5;i++) order[i] = visit[i];
            }
        for (int i = 0;i < 4;i++)
            if (visit[i] == -1)
            {
                visit[i] = curr;
                recurse(i,curr + 1, sum + ((prev==-1)?0.0:b[prev][i]));
                visit[i] = -1;
            }
    }

    public void reset(View v){
        markerPoints.clear();
        map.clear();
        StringBuilder s1 = stringbuilder.delete(0,stringbuilder.length());
        finaldist = s1.toString();
        distanceMat.setText(finaldist);
        distarray = new double[distarray.length];
        k = 0;
        order = new int[order.length];
        visit = new int[visit.length];
        b = new double[5][5];
        gmin = 1000000;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}