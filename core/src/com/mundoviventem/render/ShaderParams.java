package com.mundoviventem.render;


import java.util.ArrayList;

/**
 * Data class for encapsulating a shader string alias and it's uniforms as well as in the case of a global shader
 * its lifetime
 */

public class ShaderParams {

    private String shader;
    private ArrayList<CustomUniform> uniforms = new ArrayList<>();
    private Double lifetime = 0.0;

    public ShaderParams(String shaderString){
        shader = shaderString;
    }

    public ShaderParams(String shaderString, ArrayList<CustomUniform> uniformList){
        shader = shaderString;
        uniforms = uniformList;
    }

    /**
     * Creates a object possessing the alias string, a list of uniforms and its lifetime.
     * Uniforms as well as lifetime are optional.
     * @param shaderString
     * @param uniformList
     * @param shaderLifetime
     */

    public ShaderParams(String shaderString, ArrayList<CustomUniform> uniformList, Double shaderLifetime){
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

    public Double getLifetime(){
        return lifetime;
    }
}
