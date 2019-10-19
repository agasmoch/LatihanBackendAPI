package com.agasmochfauzan.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Langkah 4. Diawali dengan membuat Class PlayerAdapter.java
public class PlayerAdapter  extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Player> players;

    public PlayerAdapter(Context mContext) {
        this.mContext = mContext;
        this.players= new ArrayList<>();
    }

    //Isi data Player dengan setter
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_player_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Menghubungkan Objeck dengan satu layer
    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
        Player currentPlayer = players.get(position);
        holder.tvName.setText(currentPlayer.getName());
        Picasso.get().load(currentPlayer.getImagePath()).fit().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView =  itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.tv_name);
        }

    }
}