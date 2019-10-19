package com.agasmochfauzan.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
//Ini Langkah 2 Selesai diakhiri dengan membuat PlayerResualt+Constructor+Getter
public class PlayerResult {
    @SerializedName(value = "player",alternate = {"players"})
    private ArrayList<Player> player;

    public ArrayList<Player> getPlayer() {
        return player;
    }

    public PlayerResult(ArrayList<Player> player) {
        this.player = player;
    }
}
