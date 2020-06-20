package com.mundoviventem.component.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.game.Main;
import com.mundoviventem.game.ManagerMall;
import com.mundoviventem.render.TextureList;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * component for all game objects that should be able to get drawn
 */
public class SpriteRenderer extends BaseComponent {

    private TreeMap<Integer, ArrayList<TextureList>> renderSequence = new TreeMap<>();
    private Transform trnsfrmCmp;
    private boolean useDefaultBatch = true;
    private ShaderProgram shader;
    private SpriteBatch batch;

    public SpriteRenderer(Transform transformComponent){
        trnsfrmCmp = transformComponent;
        batch = ManagerMall.getRenderManager().getSpriteBatch();
    }

    /**
     * Using this constructor will make the SpriteRenderer create his own SpriteBatch, and manipulate its Shader.
     * Use this if you want a special shader applied to only a subgroup of textures, but mind the additional resources
     * an additional draw call costs.
     * @param transformComponent
     * @param batchShader
     */
    public SpriteRenderer(Transform transformComponent, ShaderProgram batchShader){
        trnsfrmCmp = transformComponent;
        useDefaultBatch = false;
        shader = batchShader;
        batch = new SpriteBatch(1000, shader);
        batch.setShader(shader);
        /*
        batch.setProjectionMatrix(new Matrix4(new float[]{
                1.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
        }));
        shader.begin();
        shader.setUniformf("u_resolution",(float)2000,(float)1500);
        shader.end();
        */

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
        // TODO
    }


    public void addTexture(String alias, Integer level, Vector2 coordinates){
        ArrayList<TextureList> al = renderSequence.get(level);
        if(al == null) {
            al = new ArrayList<>();
            renderSequence.put(level, al);
        }
        al.add(new TextureList(alias, coordinates));
    }


    public void addTexture(TextureList texList, Integer level){
        ArrayList<TextureList> al = renderSequence.get(level);
        if(al == null) {
            al = new ArrayList<>();
            renderSequence.put(level, al);
        }
        al.add(texList);
    }

    /**
     * Removes the given texture from the list of textures to draw, without knowing which level it is on.
     * try to avoid
     * @param texture
     */
    public void removeTexture(String texture){
        for(ArrayList<TextureList> al : renderSequence.values()){
            for(TextureList tl : al){
                if(texture.equals(tl.getTexture())) {
                    al.remove(tl.getTexture());
                    return;
                }
            }
        }
    }


    /**
     * Removes the given texture from the list of textures to draw
     * @param level
     * @param texture
     */
    public void removeTexture(Integer level, String texture) {
        ArrayList al = renderSequence.get(level);
        al.remove(texture);
    }


    public boolean useDefBatch(){
        return this.useDefaultBatch;
    }

    @Override
    public void gameObjectStartsSleeping()
    {
        // TODO
    }

    public void render()
    {
        batch.begin();
        if(!useDefaultBatch) {
            batch.setShader(shader);
        }
        for(Map.Entry<Integer, ArrayList<TextureList>> entry : renderSequence.entrySet()){
            for(TextureList tl : entry.getValue()){
                //TODO have this handled by a Texture Handler
                Texture tex = ManagerMall.getTextureRepository().getTexture(tl.getTexture());

                for(Vector2 coord : tl.getCoordinates()){
                    tex.bind(0);
                    batch.draw(tex, coord.x, coord.y);
                }
            }
        }
        batch.end();
    }

    public void dispose(){
        batch.dispose();
    }
}
