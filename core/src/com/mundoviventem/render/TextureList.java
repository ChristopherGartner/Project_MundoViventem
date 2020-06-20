package com.mundoviventem.render;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TextureList {

    private String texture;
    private ArrayList<Vector2> coordinates;

    public TextureList(String Texture){
        texture = Texture;
        coordinates = new ArrayList<>();
    }

    public TextureList(String Texture, Vector2 firstCoords){
        texture = Texture;
        coordinates = new ArrayList<>();
        coordinates.add(firstCoords);
    }

    public TextureList(String Texture, ArrayList<Vector2> Coordinates){
        texture = Texture;
        coordinates = Coordinates;
    }

    public String getTexture(){
        return this.texture;
    }

    public ArrayList<Vector2> getCoordinates(){
        return this.coordinates;
    }

    public void addCoordinates(Vector2 coord){
        coordinates.add(coord);
    }

    public void removeCoordinates(Vector2 coord){
        coordinates.remove(coord);
    }
}
