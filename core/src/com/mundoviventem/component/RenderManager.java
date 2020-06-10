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
    private TreeMap<Integer, SpriteRenderer> renderSequence;

    /**
     * Link RenderManager to GameObjectManager
     * @param gom
     */

    public RenderManager(GameObjectManager gom) {

        gameObjectManager = gom;
        renderSequence = new TreeMap<>();
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
        Integer level = trnsfrm.getBackgroundLevel();

        while(true){
            if(!renderSequence.containsKey(level)){
                break;
            } else {
                if(level.intValue() == Integer.MAX_VALUE) throw new RuntimeException("Cannot insert spriteRenderer" +
                        " from GameObject " + go.getName() + " at level " + trnsfrm.getBackgroundLevel() +
                        ", all following levels are already in use!");
                level = new Integer(level.intValue() + 1);
            }
        }

        renderSequence.put(level, renderer);
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
