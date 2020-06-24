package com.mundoviventem.render;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.TreeMap;

public class RenderParams {

    private TreeMap<Integer, ArrayList<TextureParams>> renderSeq;
    private TreeMap<Integer, ArrayList<ShaderParams>> glblShaders;
    private boolean usesCustomShaders = false;

    public RenderParams(TreeMap<Integer, ArrayList<TextureParams>> renderSequence){
        renderSeq = renderSequence;
        checkForCustomShader();
    }

    public RenderParams(TreeMap<Integer, ArrayList<TextureParams>> renderSequence,
                        TreeMap<Integer, ArrayList<ShaderParams>> globalShaders){
        renderSeq = renderSequence;
        glblShaders = globalShaders;
        checkForCustomShader();
    }

    private void checkForCustomShader(){
        for(ArrayList<TextureParams> al : renderSeq.values()){
            for(TextureParams tp : al){
                if(tp.getShaderParams() != null) usesCustomShaders = true;
            }
        }
    }

    // Shortcuts

    public RenderParams(String textureString, Vector2 location, Vector2 size){
        renderSeq = new TreeMap<>();
        ArrayList<TextureParams> al = new ArrayList<>();
        al.add(new TextureParams(textureString, location, size));
        renderSeq.put(0, al);
    }

    public TreeMap<Integer, ArrayList<TextureParams>> getRenderSequence(){
        return renderSeq;
    }

    public TreeMap<Integer, ArrayList<ShaderParams>> getGlobalShaders(){
        return glblShaders;
    }

    public boolean areCustomShadersUsed(){
        return usesCustomShaders;
    }
}
