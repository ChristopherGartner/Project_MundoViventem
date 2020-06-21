package com.mundoviventem.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Manages the different game states by a stack based LIFO system
 */
public class GameStateManager
{

    private Stack<GameState> states;

    /**
     * Constructs GameStateManager
     */
    public GameStateManager()
    {
        states = new Stack<>();
    }

    /**
     * Pushes a new GameState to the top of the GameState stack
     *
     * @param state = The GameState that should be pushed to the top
     */
    public void push(GameState state)
    {
        this.states.push(state);
    }

    /**
     * Removes and Returns the top GameState of the stack
     *
     * @return GameState
     */
    public GameState pop()
    {
        return states.pop();
    }

    /**
     * Resets the top GameState with the given game state
     *
     * @param gameState = The new GameState that should be on top of the stack
     */
    public void setGameState(GameState gameState)
    {
        this.pop();
        this.push(gameState);
    }

    /**
     * Returns the top GameState of the Stack without removing it
     *
     * @return GameState
     */
    public GameState peekTopGameState()
    {
        return this.states.peek();
    }

    /**
     * Updates the top GameState of the Stack
     */
    public void updateTopState()
    {
        this.peekTopGameState().update();
    }

    /**
     * Renders the top GameState of the Stack
     */
    public void renderTopState()
    {
        this.peekTopGameState().render();
    }

}
