package com.mundoviventem.component.core;

import java.util.TreeMap;

/**
 * component for all game objects that should be able to get drawn
 */
public class SpriteRenderer extends BaseComponent {

    private TreeMap<Integer, String> renderSequence = new TreeMap<>();

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


    public void addTexture(String alias, Integer level){
        if(alias.equals(renderSequence.get(level))){
            throw new RuntimeException("Texture " + alias + " with level " + level + " already exists");
        } else if(renderSequence.containsKey(level)){
            throw new RuntimeException("Level " + level + " for texture " + alias + " is already being used");
        } else {
            renderSequence.put(level, alias);
        }
    }

    public void removeTexture(Integer level) {
        renderSequence.remove(level);
    }


    @Override
    public void gameObjectStartsSleeping()
    {
        // TODO
    }

    public void render()
    {





    }
}
