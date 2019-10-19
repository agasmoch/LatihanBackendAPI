package com.agasmochfauzan.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPlayer;
    private PlayerAdapter adapter;
    private ArrayList<Player> players;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPlayer=findViewById(R.id.rv_player);
        adapter = new PlayerAdapter(this);
        players = new ArrayList<>();
        gson = new Gson();

        amdilData();

        LinearLayoutManager lm = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(this,lm.getOrientation());
        rvPlayer.setLayoutManager(lm);
        rvPlayer.setAdapter(adapter);
        rvPlayer.addItemDecoration(divider);
    }

    public void amdilData() {
    //Meminta requeast dengan Volley
    //jika request berhasil, tampilkan ke RecyclerView
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.thesportsdb.com/api/v1/json/1/searchplayers.php?t=Arsenal",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //ambil data dari response -> JSON-ArrayList
                        PlayerResult result = gson.fromJson(response, PlayerResult.class);

                        players = result.getPlayer();
                        //tampilkan data via adapter
                        adapter.setPlayers(players);


                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(stringRequest);


    }

}
