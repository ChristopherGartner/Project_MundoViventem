package com.mundoviventem.render;


import java.util.ArrayList;

public class ShaderParams {

    private String shader;
    private ArrayList<CustomUniform> uniforms = new ArrayList<>();
    private Integer lifetime = 0;

    public ShaderParams(String shaderString){
        shader = shaderString;
    }

    public ShaderParams(String shaderString, ArrayList<CustomUniform> uniformList){
        shader = shaderString;
        uniforms = uniformList;
    }

    public ShaderParams(String shaderString, ArrayList<CustomUniform> uniformList, Integer shaderLifetime){
        shader = shaderString;
        uniforms = uniformList;
        lifetime = shaderLifetime;
    }

    public String getShader(){
        return shader;
    }

    public ArrayList<CustomUniform> getUniforms(){
        return uniforms;
    }
    public Integer getLifetime(){
        return lifetime;
    }
}
