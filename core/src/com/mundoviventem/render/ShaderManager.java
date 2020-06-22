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
import java.util.TreeMap;

public class ShaderManager {

    public final Integer SHADER_LIFETIME_INFINITY = -1;

    private final String DEFAULT_SHADER_NAME = "DEFAULT";

    private HashMap<String, String> availableVertShaders = new HashMap<>();
    private HashMap<String, String> availableFragShaders = new HashMap<>();
    private HashMap<String, ShaderProgram> availableShaders = new HashMap<>();
    private ShaderProgram def = null;

    private ArrayList<CustomUniform> defUniforms = new ArrayList<>();

    //default uniforms
    private CustomUniform u_resolution;
    private CustomUniform u_mouseLocation;
    private CustomUniform u_time;


    public ShaderManager(){
        readShaders(Main.Project_Path + "\\core\\assets\\shaders");

        initializeDefUniforms();

        buildShaderPairs();
    }



    private void initializeDefUniforms(){
        u_resolution = new CustomUniform("u_resolution", CustomUniform.TYPE.VEC2,
                new float[]{Gdx.graphics.getWidth(), Gdx.graphics.getHeight()});
        Vector2 mouse = ManagerMall.getKeyActionBindingRepository().getKeyActionBindingById("mouse").getCoords();
        u_mouseLocation = new CustomUniform("u_mouseLocation", CustomUniform.TYPE.VEC2,
                new float[]{mouse.x, mouse.y});
        u_time = new CustomUniform("u_time", CustomUniform.TYPE.FLOAT, new float[]{0.0f});


        defUniforms.add(u_resolution);
        defUniforms.add(u_mouseLocation);
        defUniforms.add(u_time);
    }



    public void commitGlobalShaders(TreeMap<Integer, ArrayList<ShaderParams>> globalShaders){

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
                availableShaders.put(entryVert.getKey(), shader);
            }
        }
        def = availableShaders.get(DEFAULT_SHADER_NAME);
        if(def == null) System.err.println("Failed to construct default shader");
    }



    private void updateDefaultUniforms(ShaderProgram shader){
        u_time.updateValue(new float[]{u_time.getValues()[0] + Gdx.graphics.getDeltaTime()});
        u_resolution.updateValue(new float[]{
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        });
        Vector2 mouse = ManagerMall.getKeyActionBindingRepository().getKeyActionBindingById("mouse").getCoords();
        u_mouseLocation.updateValue(new float[]{mouse.x, mouse.y});
        System.out.println("Res: " + Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight() + " Mouse: " +
                mouse.x + " " + mouse.y);
        u_time.update(shader);
        u_resolution.update(shader);
        u_mouseLocation.update(shader);
    }



    public void update(ShaderParams shaderParams){

        ShaderProgram shader = getShaderProgram(shaderParams.getShader());
        updateDefaultUniforms(shader);

        for (CustomUniform u : defUniforms){
            u.update(shader);
        }

        for(CustomUniform u : shaderParams.getUniforms()){
            u.update(shader);
        }
    }




    public ShaderProgram getShaderProgram(String shaderName){
        return availableShaders.get(shaderName);
    }



    public String getCustomPair(String vertName, String fragName){
        String v = availableVertShaders.get(vertName);
        String f = availableFragShaders.get(fragName);
        if(v == null || f == null) return null;
        ShaderProgram sp = new ShaderProgram(vertName, fragName);
        if(!sp.isCompiled()) return null;
        String shaderName = vertName + "+" + fragName;
        availableShaders.put(shaderName, sp);
        return shaderName;
    }



    public String getShaderName(ShaderProgram shader){
        for(Map.Entry<String, ShaderProgram> entry : availableShaders.entrySet()){
            if(entry.getValue().equals(shader)) return entry.getKey();
        }
        return null;
    }

    public ShaderProgram getDefaultShader(){
        return def;
    }
}
