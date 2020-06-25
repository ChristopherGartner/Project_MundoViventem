package com.mundoviventem.component.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mundoviventem.component.RenderManager;
import com.mundoviventem.game.ManagerMall;
import com.mundoviventem.render.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * component for all game objects that should be able to get drawn
 */
public class SpriteRenderer extends BaseComponent implements Disposable {

    private Transform trnsfrmCmp;
    private TreeMap<Integer, ArrayList<TextureParams>> renderSequence;
    private RenderParams renderParameters;
    private SpriteBatch batch;
    private boolean areCustomShadersUsed = false;
    private boolean sleep = false;


    public SpriteRenderer(Transform transformComponent){
        trnsfrmCmp = transformComponent;
        batch = new SpriteBatch(8191);
        ManagerMall.getDisposingManager().addNewDisposableObject(this);
    }


    public SpriteRenderer(Transform transformComponent, RenderParams renderParams){
        trnsfrmCmp = transformComponent;
        batch = new SpriteBatch(8191);
        ManagerMall.getDisposingManager().addNewDisposableObject(this);
        renderParameters = renderParams;
        processRenderParams();
    }

    private void processRenderParams(){
        renderSequence = renderParameters.getRenderSequence();
        areCustomShadersUsed = renderParameters.areCustomShadersUsed();
        if(renderParameters.getGlobalShaders() != null) ManagerMall.getShaderManager().commitGlobalShaders(renderParameters.getGlobalShaders());
        if(areCustomShadersUsed) batch = new SpriteBatch(8191);
    }

    public void setNewRenderParams(RenderParams renderParams){
        renderParameters = renderParams;
        processRenderParams();
    }

    public RenderParams getRenderParams(){
        return renderParameters;
    }


    @Override
    public void onEnable()
    {
        // TODO
    }

    @Override
    public void onDisable()
    {
        // TODO
    }

    @Override
    public void update()
    {
        if(sleep) return;
        for(ArrayList<TextureParams> al : renderSequence.values()){
            for(TextureParams tp : al){
                if(tp.getShaderParams() == null || tp.getShaderParams().getShader() == null) continue;
                ManagerMall.getShaderManager().updateUniforms(tp.getShaderParams());
            }
        }
    }


    @Override
    public void gameObjectStartsSleeping()
    {
        this.sleep = true;
    }

    @Override
    public void gameObjectAwakens() {
        this.sleep = false;
    }

    public void render()
    {
        if(sleep) return;
        for(Map.Entry<Integer, ArrayList<TextureParams>> entry : renderSequence.entrySet()){
            for(TextureParams tp : entry.getValue()){

                Texture tex = ManagerMall.getTextureRepository().getTexture(tp.getTexture());

                if(tp.getShaderParams() != null){
                    batch.setShader(ManagerMall.getShaderManager().getShaderProgram(tp.getShaderParams().getShader()));
                } else if(areCustomShadersUsed) {
                    batch.setShader(ManagerMall.getShaderManager().getDefaultShader());
                }

                Vector2 size = tp.getSize().equals(TextureParams.USE_TEX_SIZE) ?
                        new Vector2(tex.getWidth(), tex.getHeight()) : tp.getSize();


                ManagerMall.getRenderManager().beginPrimaryFB();
                batch.begin();
                for(Vector2 coord : tp.getLocations()){
                    tex.bind(0);
                    batch.draw(tex, coord.x, coord.y, size.x, size.y);
                }
                batch.end();
                ManagerMall.getRenderManager().endPrimaryFB();
            }
        }
    }

    public void dispose(){
        if(!batch.equals(ManagerMall.getRenderManager().getSpriteBatch())) batch.dispose();
    }
}
