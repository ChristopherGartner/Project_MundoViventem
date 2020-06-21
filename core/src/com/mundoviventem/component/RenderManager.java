package com.mundoviventem.component;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.Transform;
import com.mundoviventem.component.game_objects.GameObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Manages render calls to all gameobjects' spriterenderers
 */

public class RenderManager implements Disposable{

    private SpriteBatch batch = new SpriteBatch();

    /**
     * Returns the SpriteBatch instance
     *
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

        for(Map.Entry<Integer, ArrayList<SpriteRenderer>> entry : renderSequence.entrySet()){
            for(SpriteRenderer sr : entry.getValue()){
                sr.render();
            }
        }

    }
}
