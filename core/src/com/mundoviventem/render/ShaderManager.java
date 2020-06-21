package com.mundoviventem.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.game.Main;
import com.mundoviventem.game.ManagerMall;
import com.mundoviventem.util.Helper;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShaderManager {

    private final String DEFAULT_SHADER_NAME = "DEFAULT";

    private HashMap<String, ShaderProgram> availableShaderPairs = new HashMap<>();
    private HashMap<String, String> availableVertShaders = new HashMap<>();
    private HashMap<String, String> availableFragShaders = new HashMap<>();
    private ShaderProgram def = null;
    private String defVert = "";
    private String defFrag = "";

    private ArrayList<CustomUniform> defUniforms = new ArrayList<>();
    private HashMap<String, ArrayList<CustomUniform>> customUniforms = new HashMap<>();

    //default uniforms
    CustomUniform u_resolution;
    CustomUniform u_mouseLocation;
    CustomUniform u_time;


    public ShaderManager(){
        readShaders(Main.Project_Path + "\\core\\assets\\shaders");

        u_resolution = new CustomUniform("u_resolution", CustomUniform.TYPE.VEC2,
                new float[]{Gdx.graphics.getWidth(), Gdx.graphics.getHeight()});
        Vector2 mouse = ManagerMall.getKeyActionBindingRepository().getKeyActionBindingById("mouse").getCoords();
        u_mouseLocation = new CustomUniform("u_mouseLocation", CustomUniform.TYPE.VEC2,
                new float[]{mouse.x, mouse.y});
        u_time = new CustomUniform("u_time", CustomUniform.TYPE.FLOAT, new float[]{0.0f});


        defUniforms.add(u_resolution);
        defUniforms.add(u_mouseLocation);
        defUniforms.add(u_time);

        buildShaderPairs();
    }


    private void readShaders(String path){
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if(child.isDirectory()) {
                    readShaders(child.getAbsolutePath());
                    return;
                }
                String extension = Helper.getFileExtension(child);
                String fileContent = "";
                try{
                    ArrayList<String> temp = new ArrayList(Files.readAllLines(child.toPath()));
                    fileContent = String.join("\n", temp);
                } catch(Exception e){
                    System.err.println("Couldn't read shader " + child.getName());
                    e.printStackTrace();
                }
                if(extension.equals(".vert")){
                    availableVertShaders.put(Helper.nameWithoutExtension(child), fileContent);
                } else if(extension.equals(".frag")){
                    availableFragShaders.put(Helper.nameWithoutExtension(child), fileContent);
                }
                if(child.getName().equals("DEFAULT.vert")) defVert = fileContent;
                if(child.getName().equals("DEFAULT.frag")) defFrag = fileContent;
            }
        }
    }

    private void buildShaderPairs(){
        for(Map.Entry<String, String> entryVert : availableVertShaders.entrySet()){
            String tmp = availableFragShaders.get(entryVert.getKey());
            if(tmp != null){
                ShaderProgram shader = new ShaderProgram(entryVert.getValue(), tmp);
                if(!shader.isCompiled()) {
                    System.err.println("Failed to compile Shader " + entryVert.getKey());
                    continue;
                }
                availableShaderPairs.put(entryVert.getKey(), shader);
            }
        }
        def = availableShaderPairs.get(DEFAULT_SHADER_NAME);
        if(def == null) System.err.println("Failed to construct default shader");
    }

    public void update(ShaderProgram shader){

        u_time.updateValue(new float[]{u_time.getValues()[0] + Gdx.graphics.getDeltaTime()});
        u_resolution.updateValue(new float[]{
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        });
        Vector2 mouse = ManagerMall.getKeyActionBindingRepository().getKeyActionBindingById("mouse").getCoords();
        u_mouseLocation.updateValue(new float[]{mouse.x, mouse.y});

        u_time.update(shader);
        u_resolution.update(shader);
        u_mouseLocation.update(shader);

        for (CustomUniform u : defUniforms){
            u.update(shader);
        }

        for(CustomUniform u : customUniforms.get(getShaderName(shader))){
            u.update(shader);
        }
    }

    public void setCustomUniforms(String targetShader, ArrayList<CustomUniform> uniforms){
        customUniforms.put(targetShader, uniforms);
    }

    public ShaderProgram getShaderProgram(String shaderName){
        return availableShaderPairs.get(shaderName);
    }

    public ShaderProgram getCustomPair(String vertName, String fragName){
        String v = availableVertShaders.get(vertName);
        String f = availableFragShaders.get(fragName);
        if(v == null || f == null) return null;
        return new ShaderProgram(v, f);
    }

    public String getShaderName(ShaderProgram shader){
        for(Map.Entry<String, ShaderProgram> entry : availableShaderPairs.entrySet()){
            if(entry.getValue().equals(shader)) return entry.getKey();
        }
        return null;
    }
}
