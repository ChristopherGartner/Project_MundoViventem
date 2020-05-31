package com.mundoviventem.component.core;

import com.badlogic.gdx.utils.Disposable;
import com.mundoviventem.component.game_objects.GameObject;

import java.util.ArrayList;

/**
 * Base class for components
 */
public abstract class BaseComponent
{
    private GameObject gameObject;
    private ArrayList<Disposable> disposableObjects = new ArrayList<>();

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
     * Adds a new resource that should get disposed
     *
     * @param disposableResource = The new resource that should get registered
     */
    public void addDisposableResource(Disposable disposableResource)
    {
        this.disposableObjects.add(disposableResource);
    }

    /**
     * Removes a registered object from the disposable resource list
     *
     * @param disposableResource = The object whose registration should be removed
     */
    public void removeDisposableResource(Disposable disposableResource)
    {
        this.disposableObjects.remove(disposableResource);
    }

    /**
     * Returns the list of all registered resources that should get disposed
     *
     * @return ArrayList<Disposable>
     */
    public ArrayList<Disposable> getDisposableObjects()
    {
        return this.disposableObjects;
    }

    /**
     * Returns whether the component has disposable resources registered or not
     *
     * @return boolean
     */
    public boolean hasDisposableResources()
    {
        return this.disposableObjects.isEmpty();
    }

    /**
     * Disposes all registered disposable resources
     */
    public void disposeResources()
    {
        for (Disposable disposable : this.disposableObjects) {
            disposable.dispose();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
