package com.mundoviventem.component;

import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.Transform;
import com.mundoviventem.component.game_objects.GameObject;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Manages render calls to all gameobjects' spriterenderers
 */

public class RenderManager {

    private GameObjectManager gameObjectManager;
    private TreeMap<Integer, SpriteRenderer> renderSequence = new TreeMap<>();

    /**
     * Link RenderManager to GameObjectManager
     * @param gom
     */

    public RenderManager(GameObjectManager gom) {

        gameObjectManager = gom;
    }

    /**
     * Adds a GameObjects' SpriteRenderer and corresponding backgroundlevel to the list
     * @param go
     */

    protected void addGameObject(GameObject go){
        BaseComponent comp = go.getComponentFromClass(SpriteRenderer.class);
        if(comp == null) return;
        SpriteRenderer renderer = (SpriteRenderer) comp;
        Transform trnsfrm = (Transform) go.getComponentFromClass(Transform.class);
        renderSequence.put(trnsfrm.getBackgroundLevel(), renderer);
    }

    /**
     * Removes a GameObjects' SpriteRenderer from the list of renderers to call
     * @param go
     */

    protected void removeGameObject(GameObject go){
        BaseComponent comp = go.getComponentFromClass(SpriteRenderer.class);
        if(comp == null) return;
        SpriteRenderer renderer = (SpriteRenderer) comp;

        Integer keyToRemove = null;

        for (Map.Entry<Integer, SpriteRenderer> entry : renderSequence.entrySet()) {
            if (Objects.equals(renderer, entry.getValue())) {
                keyToRemove = entry.getKey();
            }
        }

        if(keyToRemove != null){
            renderSequence.remove(keyToRemove);
        } else {
            System.err.println("SpriteRenderer of object " + go.getName() + " was never added to RenderManager!");
        }
    }

    /**
     * Calls all SpriteRenderers in sequence to render
     */

    public void renderObjects(){

        for(Map.Entry<Integer, SpriteRenderer> entry : renderSequence.entrySet()){
            entry.getValue().render();
        }

    }
}
