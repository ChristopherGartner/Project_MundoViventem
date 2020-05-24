package com.mundoviventem.component.core;

import com.mundoviventem.component.game_objects.GameObject;

/**
 * Base class for components
 */
public abstract class BaseComponent
{
    private GameObject gameObject;

    /**
     * Sets the game object the component gets bound to
     *
     * @param gameObject = Game object, which owns the component instance
     */
    public void setGameObject(GameObject gameObject)
    {
        this.gameObject = gameObject;
    }

    /**
     * Returns the current bound game object
     *
     * @return GameObject
     */
    public GameObject getGameObject()
    {
        return this.gameObject;
    }

    /**
     * Method gets called when component gets bound to game object
     */
    public abstract void onEnable();

    /**
     * Method gets called when component gets unbound from game object
     */
    public abstract void onDisable();

    /**
     * Method gets called for each tick
     */
    public abstract void update();

}
