package com.example.volley_api;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {
    String url;
    RecyclerView rv1;
    public List<model> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "https://mutable-villages.000webhostapp.com/test_post.php";
rv1=findViewById(R.id.recyclerview);

        StringRequest stringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("resopnse", response);
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray array=jsonObject.getJSONArray("user");
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        model model=new model();
                        model.setName(object.getString("name"));
                        model.setEmail(object.getString("email"));
                        list.add(model);
                    }
                    RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(list);
                    rv1.setHasFixedSize(true);
                    rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    rv1.setAdapter(recyclerViewAdapter);
Log.d("model", String.valueOf(list));
                    Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_LONG).show();

                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("resopnse1", String.valueOf(error));
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("u_name", "dev");
                return map;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

