package com.agasmochfauzan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView tvName;
    private TextView tvNationality;
    private  TextView tvPlace;
    private  TextView tvBirhtdate;
    private  TextView tvDesciption;
    private String idPlayer;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.tv_imagePath);
        tvName = findViewById(R.id.tv_name);
        tvNationality = findViewById(R.id.tv_nationality);
        tvPlace = findViewById(R.id.tv_birthPlace);
        tvBirhtdate = findViewById(R.id.tv_birthDate);
        tvDesciption = findViewById(R.id.tv_description);
        gson = new Gson();
        //Mengambil data dengan idPlayer
        idPlayer = getIntent().getStringExtra("idPlayers");
        String url="https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id="+idPlayer;

        //ambil data demgam Volley dan GSON
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Ambil Nilai dan set Ke Komponen Layout
                        PlayerResult result =gson.fromJson(response,PlayerResult.class);
                        Player player = result.getPlayer().get(0);
                        //set ke komponnen
                        tvName.setText(player.getName());
                        tvNationality.setText(player.getNationality());
                        tvPlace.setText(player.getBirthPlace());
                        tvBirhtdate.setText(player.getBirthDate());
                        tvDesciption.setText(player.getDescription());
                        Picasso.get().load(player.getImagePath()).into(imageView);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(stringRequest);
    }
}
