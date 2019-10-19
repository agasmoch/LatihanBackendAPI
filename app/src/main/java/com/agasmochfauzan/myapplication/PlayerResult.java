package com.agasmochfauzan.myapplication;

import java.util.ArrayList;
//Ini Langkah 2 Selesai diakhiri dengan membuat PlayerResualt+Constructor+Getter
public class PlayerResult {
    private ArrayList<Player> player;

    public ArrayList<Player> getPlayer() {
        return player;
    }

    public PlayerResult(ArrayList<Player> player) {
        this.player = player;
    }
}
