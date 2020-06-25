package com.mundoviventem.render;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * A data class to deliver all relevant instructions on what and how to render to the SpriteRenderer
 */

public class RenderParams {

    private TreeMap<Integer, ArrayList<TextureParams>> renderSeq;
    private TreeMap<ShaderManager.GlobalShader, ShaderParams> glblShaders;
    private boolean usesCustomShaders = false;

    public RenderParams(TreeMap<Integer, ArrayList<TextureParams>> renderSequence){
        renderSeq = renderSequence;
        checkForCustomShader();
    }

    /**
     * Builds the RenderParams object and checks if custom shaders are used, if so, sets flag
     * @param renderSequence A treemap with the render priority as its key (0 - draw first (furthest in the back),
     *                       max_value - drawn last (closest in the front)) and a List of TextureParams to draw at
     *                       that priority.
     * @param globalShaders Can optionally also carry ShaderParams for global shader
     */

    public RenderParams(TreeMap<Integer, ArrayList<TextureParams>> renderSequence,
                        TreeMap<ShaderManager.GlobalShader, ShaderParams> globalShaders){
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

    public TreeMap<ShaderManager.GlobalShader, ShaderParams> getGlobalShaders(){
        return glblShaders;
    }

    public boolean areCustomShadersUsed(){
        return usesCustomShaders;
    }
}
