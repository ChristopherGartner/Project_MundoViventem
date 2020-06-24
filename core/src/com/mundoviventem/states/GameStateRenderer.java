package com.mundoviventem.states;

import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.Transform;
import com.mundoviventem.component.game_objects.GameObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Specified Renderer for GameStates
 */
public class GameStateRenderer
{

    private TreeMap<Integer, ArrayList<SpriteRenderer>> renderSequence;

    /**
     * Initializes the GameStateRenderer
     */
    public GameStateRenderer()
    {
        this.renderSequence = new TreeMap<>();
    }

    /**
     * Adds a GameObjects' SpriteRenderer and corresponding background-level to the list
     *
     * @param go = the GameObject that should be added to the render sequence
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
     *
     * @param go = The GameObject that should be removed from the render sequence
     */

    public void removeGameObject(GameObject go){
        BaseComponent comp = go.getComponentFromClass(SpriteRenderer.class);
        if(comp == null) return;
        SpriteRenderer renderer = (SpriteRenderer) comp;
        Transform trnsfrm = (Transform) go.getComponentFromClass(Transform.class);

        ArrayList<SpriteRenderer> al = renderSequence.get(trnsfrm.getBackgroundLevel());
        renderer.dispose();
        al.remove(renderer);
    }

    /**
     * Returns the current render sequence
     *
     * @return TreeMap<Integer, ArrayList<SpriteRenderer>>
     */
    public TreeMap<Integer, ArrayList<SpriteRenderer>> getRenderSequence()
    {
        return this.renderSequence;
    }

    /**
     * Resets the render sequence
     *
     * @param renderSequence = TreeMap<Integer, ArrayList<SpriteRenderer>>
     */
    public void setRenderSequence(TreeMap<Integer, ArrayList<SpriteRenderer>> renderSequence)
    {
        this.renderSequence = renderSequence;
    }

}
