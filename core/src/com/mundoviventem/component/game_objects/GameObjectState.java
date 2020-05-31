package com.mundoviventem.component.game_objects;

/**
 * Data class for game object states
 */
public class GameObjectState
{

    private String iD;

    /**
     * Sets the iD of the given game object
     *
     * @param iD = iD of the given game object
     */
    public void setiD(String iD)
    {
        this.iD = iD;
    }

    /**
     * Returns the iD of the given game object
     *
     * @return String
     */
    public String getID()
    {
        return this.iD;
    }
}
