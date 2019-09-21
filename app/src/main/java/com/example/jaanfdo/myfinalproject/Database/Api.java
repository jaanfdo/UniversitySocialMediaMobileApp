package com.example.jaanfdo.myfinalproject.Database;


import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Api {
    // Instantiate the RequestQueue.
//    RequestQueue queue = Volley.newRequestQueue(this);
//    String url ="http://www.google.com";
//
//    // Request a string response from the provided URL.
//    StringRequest stringRequest = new StringRequest(DownloadManager.Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    // Display the first 500 characters of the response string.
//                    textView.setText("Response is: "+ response.substring(0,500));
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            textView.setText("That didn't work!");
//        }
//    });
//
//    // Add the request to the RequestQueue.
//    queue.add(stringRequest);
//
//
//    private void getVolley(){
//
//        final String username = etUname.getText().toString().trim();
//        final String password = etPass.getText().toString().trim();
//
//        URLline = "https://demonuts.com/Demonuts/JsonTest/Tennis/loginGETrequest.php?username="+username+"&password="+password;
//
//        //Log.d("getxxx",URLline);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLline,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("strrrrr",">>"+response);
//                        parseData(response);
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //displaying the error in toast if occurrs
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        // request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        requestQueue.add(stringRequest);
//    }
//
//    public void parseData(String response) {
//
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            if (jsonObject.getString("status").equals("true")) {
//                JSONArray dataArray = jsonObject.getJSONArray("data");
//                for (int i = 0; i < dataArray.length(); i++) {
//
//                    JSONObject dataobj = dataArray.getJSONObject(i);
//                    publicFirstName = dataobj.getString("name");
//                    publicHobby = dataobj.getString("hobby");
//                }
//
//                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                startActivity(intent);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
}
