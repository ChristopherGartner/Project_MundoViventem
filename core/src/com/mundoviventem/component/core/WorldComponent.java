package com.mundoviventem.component.core;

import com.badlogic.gdx.math.Vector2;

/**
 * Component for world data
 */
public class WorldComponent extends BaseComponent
{
    private Vector2 worldSize;

    /**
     * Constructor of WorldComponent.
     * Needs a size
     *
     * @param worldSize = The size of the world
     */
    public WorldComponent(Vector2 worldSize)
    {
        this.setWorldSize(worldSize);
    }

    /**
     * Returns the size of the world
     *
     * @return Vector2
     */
    public Vector2 getWorldSize()
    {
        return this.worldSize;
    }

    /**
     * Sets the size of the world
     *
     * @param worldSize = the world size as Vector2
     */
    public void setWorldSize(Vector2 worldSize)
    {
        this.worldSize = worldSize;
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public void update()
    {
        System.out.println("Successfully updated world");
    }
}
