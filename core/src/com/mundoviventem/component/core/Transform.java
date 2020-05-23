package com.mundoviventem.component.core;


import com.badlogic.gdx.math.Vector2;

/**
 * Basic class of Transform component.
 * Resembles the position of a game object in the world
 */
public class Transform extends BaseComponent
{

    private Vector2 position;
    private float rotation;
    private Vector2 scale;

    /**
     * Returns the position vector consisting of X and Y value
     *
     * @return Vector2
     */
    public Vector2 getPosition()
    {
        return this.position;
    }

    /**
     * Sets the position vector
     *
     * @param position = Vector consisting of X and Y value
     */
    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    /**
     * Returns the rotation of the game object
     *
     * @return Vector2
     */
    public float getRotation()
    {
        return this.rotation;
    }

    /**
     * Sets the rotation of the game object
     *
     * @param rotation = Vector containing the rotation data
     */
    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    /**
     * Returns the render scaling of the game object.
     *
     * @return Vector2
     */
    public Vector2 getScale()
    {
        return this.scale;
    }

    /**
     * Sets the scaling of the game object
     *
     * @param scale = Vector containing the scaling data
     */
    public void setScale(Vector2 scale)
    {
        this.scale = scale;
    }

}
