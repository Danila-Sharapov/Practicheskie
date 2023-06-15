package com.example.paperbd;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import io.paperdb.Paper;

public class PaperDbClass {

    public ArrayList<Guns> getGuns(){
        return Paper.book("guns").read("guns", new ArrayList<Guns>());
    }

    public void saveGunsList(ArrayList<Guns> guns){
        Paper.book("guns").write("guns", guns);
    }

    public void addGun (Guns gun, Context cont){

        ArrayList<Guns> guns = getGuns();

        for (int i = 0; i < guns.size(); i++){
            if (guns.get(i).getHeader().equals(gun.getHeader())){
                Toast.makeText(cont, "Такое оружие уже существует", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        guns.add(gun);
        saveGunsList(guns);
    }

}
