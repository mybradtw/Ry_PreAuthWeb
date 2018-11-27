package tw.brad.sakuratestall;

import android.content.Intent;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.Telephony.Carriers.PASSWORD;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String value1 = uri.getQueryParameter("key1");
            String value2 = uri.getQueryParameter("key2");
            Log.v("brad", "key1 = " + value1);
            Log.v("brad", "key2 = " + value2);
        }
    }

    public void test1(View view) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://www1.sakura.com.tw/sakura/rstcom.nsf/org.xsp/per?u=987658",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.v("brad", response.toString());
                        parseJSON(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String credentials = "brad" + ":" + "bradpass!";
                String base64EncodedCredentials =
                        Base64.encodeToString(credentials.getBytes(),
                                Base64.NO_WRAP);
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + base64EncodedCredentials);
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    private void parseJSON(String json){
        //Log.v("brad", json);
        try {
            JSONObject data = new JSONObject(json);
            String auther = data.getString("Auther");
            Log.v("brad", auther);
        }catch (Exception e){
            Log.v("brad", e.toString());
        }

    }

    public void test2(View view) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://www1.sakura.com.tw/sakura/rstcom.nsf/prof.xsp/form?d=sakura\\asklev2.nsf",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.v("brad", response.toString());
                        parseJSON2(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String credentials = "brad" + ":" + "bradpass!";
                String base64EncodedCredentials =
                        Base64.encodeToString(credentials.getBytes(),
                                Base64.NO_WRAP);
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + base64EncodedCredentials);
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void parseJSON2(String json){
        try {
            JSONObject data = new JSONObject(json);
            JSONArray pt = data.getJSONArray("pt");
            for (int i=0; i<pt.length(); i++){
                Log.v("brad", pt.getString(i));
            }
        }catch (Exception e){
            Log.v("brad", e.toString());
        }

    }

    public void test3(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-E291D06C-CF52-470E-B20A-4D1C3257FEAE",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("brad", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void test4(View view) {
        Intent mServiceIntent = new Intent();
        mServiceIntent.putExtra("download_url", "");
        MyIntentService.acceptAJob(this, "job1");
        MyIntentService.acceptAJob(this, "job2");
        MyIntentService.acceptAJob(this, "job3");
        MyIntentService.acceptAJob(this, "job4");
    }
}
