package com.mundoviventem.component.core.input;

/**
 * data class for key action bindings
 */
public class KeyActionBinding
{

    private int key;
    private String action;
    private String gameState;
    private boolean isActive;

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
}
