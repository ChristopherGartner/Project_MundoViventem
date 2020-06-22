package com.mundoviventem.component.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
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


    public SpriteRenderer(Transform transformComponent){
        trnsfrmCmp = transformComponent;
        batch = new SpriteBatch(8191);//ManagerMall.getRenderManager().getSpriteBatch();
        ManagerMall.getDisposingManager().addNewDisposableObject(this);
    }


    public SpriteRenderer(Transform transformComponent, RenderParams renderParams){
        trnsfrmCmp = transformComponent;
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
        for(ArrayList<TextureParams> al : renderSequence.values()){
            for(TextureParams tp : al){
                if(tp.getShaderParams() == null) continue;
                ManagerMall.getShaderManager().update(tp.getShaderParams());
            }
        }
    }


    @Override
    public void gameObjectStartsSleeping()
    {
        // TODO
    }

    public void render()
    {
        for(Map.Entry<Integer, ArrayList<TextureParams>> entry : renderSequence.entrySet()){
            for(TextureParams tp : entry.getValue()){

                Texture tex = ManagerMall.getTextureRepository().getTexture(tp.getTexture());

                if(tp.getShaderParams() != null){
                    batch.setShader(ManagerMall.getShaderManager().getShaderProgram(tp.getShaderParams().getShader()));
                } else if(areCustomShadersUsed) {
                    batch.setShader(ManagerMall.getShaderManager().getDefaultShader());
                }

                Vector2 size = tp.getSize().x < 0 ? new Vector2(tex.getWidth(), tex.getHeight()) : tp.getSize();
                batch.begin();
                for(Vector2 coord : tp.getLocations()){
                    tex.bind(0);
                    batch.draw(tex, coord.x, coord.y, size.x, size.y);
                }
                batch.end();
            }
        }
    }

    public void dispose(){
        batch.dispose();
    }
}
