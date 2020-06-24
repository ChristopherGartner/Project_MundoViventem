package com.mundoviventem.render;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TextureParams {

    private String texture;
    private ShaderParams shaderParams;
    private ArrayList<Vector2> locations;
    private Vector2 size;

    public TextureParams(String textureString, ArrayList<Vector2> Locations, Vector2 Size){
        texture = textureString;
        locations = Locations;
        size = Size;
    }

    public TextureParams(String textureString, Vector2 textureLocation, Vector2 Size){
        texture = textureString;
        ArrayList<Vector2> al = new ArrayList<>();
        al.add(textureLocation);
        locations = al;
        size = Size;
    }

    public TextureParams(String textureString, ArrayList<Vector2> Locations, Vector2 Size, ShaderParams shaderParameters){
        texture = textureString;
        locations = Locations;
        size = Size;
        shaderParams = shaderParameters;
    }

    public String getTexture(){
        return texture;
    }

    public ShaderParams getShaderParams(){
        return shaderParams;
    }

    public ArrayList<Vector2> getLocations(){
        return locations;
    }

    public Vector2 getSize(){
        return size;
    }

    public void addLocation(Vector2 location){
        locations.add(location);
    }

    public void removeLocation(Vector2 location){
        locations.remove(location);
    }
}
