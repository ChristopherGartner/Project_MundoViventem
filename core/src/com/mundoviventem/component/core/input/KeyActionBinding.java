package com.mundoviventem.component.core.input;

import com.badlogic.gdx.math.Vector2;

/**
 * data class for key action bindings
 */
public class KeyActionBinding
{

    private int key;
    private String action;
    private String gameState;
    private boolean isActive;
    private String id;
    private Vector2 coords;


    public KeyActionBinding(String binding_id){
        id = binding_id;
        coords = new Vector2(0, 0);
    }


    /**
     * Returns the id of the key action binding
     *
     * @return String
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Sets the id of the key action binding
     *
     * @param id = The id of the key action binding
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Returns the keyboard key
     *
     * @return int
     */
    public int getKey()
    {
        return this.key;
    }

    /**
     * Sets the keyboard key
     *
     * @param key = New key
     */
    public void setKey(int key)
    {
        this.key = key;
    }

    /**
     * Returns the action
     *
     * @return String
     */
    public String getAction()
    {
        return this.action;
    }

    /**
     * Sets the action
     *
     * @param action = New action
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * Returns the game state
     *
     * @return String
     */
    public String getGameState()
    {
        return this.gameState;
    }

    /**
     * Sets the game state
     *
     * @param gameState = Game state
     */
    public void setGameState(String gameState)
    {
        this.gameState = gameState;
    }

    /**
     * Returns whether the current action binding is active or not
     *
     * @return boolean
     */
    public boolean isActive()
    {
        return this.isActive;
    }

    /**
     * Sets whether the current action binding is active or not
     *
     * @param isActive = whether the action binding is active or not
     */
    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }


    /**
     * Sets coordinates, only used with mouse inputs
     *
     */
    public void setCoords(Vector2 coordinates){
        coords = coordinates;
    }

    /**
     * Gets coordinates, only used with mouse inputs
     *
     */
    public Vector2 getCoords(){
        return coords;
    }
}
