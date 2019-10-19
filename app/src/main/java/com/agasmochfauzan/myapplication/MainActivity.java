//** HARAP INSTALL
//Android Studio 3.4.2
//        Build #AI-183.6156.11.34.5692245, built on June 27, 2019
//        JRE: 1.8.0_152-release-1343-b01 amd64
//        JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
//        Windows 10 10.0
//Genymotion: 3.0.2
package com.agasmochfauzan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
        // LinearLayout bisa setting horizontal atau vertical tinggal ganti "HORIZONTAL/VERTICAL"
        // GridLayoutManager tampilan secara grid, bisa dipilih gridnya tinggal ganti "2" dengan yang diinginkan
        // StaggeredGridLayoutManager tampilan bisa dibilang grid tapi tidak teratur
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        DividerItemDecoration divider = new DividerItemDecoration(this,lm.getOrientation());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2,RecyclerView.VERTICAL,false);
        StaggeredGridLayoutManager lm3 = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvPlayer.setLayoutManager(gridLayoutManager); // Buat nampilkan perintah grid tinggal ganti gridlayoutmanager
        //dengan variabel (lm,lm3,gridLayoutManager)
        rvPlayer.setAdapter(adapter);
        rvPlayer.addItemDecoration(divider);

        //Nembahkan Aksi onclick selagigus perpindahakan halaman
        adapter.setListener(new OnClickListener() {
            @Override
            public void aksiKlik(int position) {
                //cara berpindah activity/halaman
                Intent intent=new Intent(MainActivity.this, DetailActivity.class);
                //buat mengambil id player yang di klik
                intent.putExtra("idPlayers", players.get(position).getIdPlayer());
                startActivity(intent);
            }
        });
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
