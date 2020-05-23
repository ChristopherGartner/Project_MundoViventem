package com.mundoviventem.component.core;

/**
 * Base class for components
 */
public abstract class BaseComponent
{
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
