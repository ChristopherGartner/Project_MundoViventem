package com.mundoviventem.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.Transform;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.game.ManagerMall;
import com.mundoviventem.render.ShaderManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Manages render calls to all gameobjects' spriterenderers
 */

public class RenderManager implements Disposable{

    private SpriteBatch batch = new SpriteBatch();
    private FrameBuffer fb;
    private FrameBuffer oldFb;


    public RenderManager(){
        fb = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    }

    /**
     * Returns the SpriteBatch instance
     *9
     * @return SpriteBatch
     */
    public SpriteBatch getSpriteBatch()
    {
        return this.batch;
    }

    @Override
    public void dispose()
    {
        this.getSpriteBatch().dispose();
    }

    /**
     * Calls all SpriteRenderers in sequence to render
     */

    public void renderObjects(TreeMap<Integer, ArrayList<SpriteRenderer>> renderSequence){

        fb.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        fb.end();

        for(Map.Entry<Integer, ArrayList<SpriteRenderer>> entry : renderSequence.entrySet()){
            for(SpriteRenderer sr : entry.getValue()){
                sr.render();
            }
        }
        oldFb = fb;
    }

    public void renderGlobalShaders(){

        for(Map.Entry<ShaderManager.GlobalShader, ShaderProgram> entry : ManagerMall.getShaderManager().getActiveGlobalShaders().entrySet()){
            FrameBuffer currentFb = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

            Sprite sprite = new Sprite(oldFb.getColorBufferTexture());
            sprite.flip(false, true);

            currentFb.begin();
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.setShader(entry.getValue());
            batch.begin();
            batch.draw(sprite, 0.0f, 0.0f);
            batch.end();
            currentFb.end();

            oldFb = currentFb;
        }

        Sprite sprite = new Sprite(oldFb.getColorBufferTexture());
        sprite.flip(false, true);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setShader(ManagerMall.getShaderManager().getDefaultShader());
        batch.begin();
        batch.draw(sprite, 0.0f, 0.0f);
        batch.end();

    }

    public void beginPrimaryFB(){
        fb.begin();
    }

    public void endPrimaryFB(){
        fb.end();
    }
}
