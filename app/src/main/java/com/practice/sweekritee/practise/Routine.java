package com.practice.sweekritee.practise;



        import android.app.ProgressDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.widget.ProgressBar;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

public class Routine extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<RoutineItem> routineItems;
    private static String url="http://192.168.1.10/notifyme/getroutine.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        recyclerView =(RecyclerView) findViewById(R.id.routine_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        routineItems= new ArrayList<>();
        loadRoutine();
    }
    private  void loadRoutine()
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("loading data");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response",response.toString());
                progressDialog.dismiss();

                try {
                    int i=0;
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("response_list");

                    while(i<jsonArray.length())
                    {

                        JSONObject object=jsonArray.getJSONObject(i);
                        String name=object.getString("name");
                        String image_path=object.getString("path");

                        RoutineItem item=new RoutineItem(name,image_path);

                        routineItems.add(item);


                        i++;
                    }
                    //studentAdapter.notifyDataSetChanged();
                    adapter= new Adapter_routine(routineItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

