package com.mundoviventem.component;

import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.Transform;
import com.mundoviventem.component.game_objects.GameObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Manages render calls to all gameobjects' spriterenderers
 */

public class RenderManager {

    private GameObjectManager gameObjectManager;
    private TreeMap<Integer, ArrayList<SpriteRenderer>> renderSequence;

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

    public void addGameObject(GameObject go){
        BaseComponent comp = go.getComponentFromClass(SpriteRenderer.class);
        if(comp == null) return;
        SpriteRenderer renderer = (SpriteRenderer) comp;
        Transform trnsfrm = (Transform) go.getComponentFromClass(Transform.class);
        Integer level = trnsfrm.getBackgroundLevel();

        ArrayList<SpriteRenderer> al = renderSequence.get(level);
        if(al == null){
            al = new ArrayList<>();
            renderSequence.put(level, al);
        }
        al.add(renderer);
    }

    /**
     * Removes a GameObjects' SpriteRenderer from the list of renderers to call
     * @param go
     */

    public void removeGameObject(GameObject go){
        BaseComponent comp = go.getComponentFromClass(SpriteRenderer.class);
        if(comp == null) return;
        SpriteRenderer renderer = (SpriteRenderer) comp;
        Transform trnsfrm = (Transform) go.getComponentFromClass(Transform.class);

        ArrayList<SpriteRenderer> al = renderSequence.get(trnsfrm.getBackgroundLevel());
        if(!renderer.useDefBatch()) renderer.dispose();
        al.remove(renderer);
    }

    /**
     * Calls all SpriteRenderers in sequence to render
     */

    public void renderObjects(){

        for(Map.Entry<Integer, ArrayList<SpriteRenderer>> entry : renderSequence.entrySet()){
            for(SpriteRenderer sr : entry.getValue()){
                sr.render();
            }
        }

    }
}
