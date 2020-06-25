package com.mundoviventem.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mundoviventem.component.core.InputHandler;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.game.ManagerMall;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Abstract class for GameStates
 */
public abstract class GameState
{

    private GameObject cameraObject;
    private GameObject inputObject;

    private ArrayList<GameObject> instantiatedGameObjects;
    private String id;

    private GameStateRenderer gameStateRenderer;

    /**
     * Initializes the GameState
     *
     * @param id = The id of the game state
     */
    protected GameState(String id)
    {
        GameObject cameraObject = new GameObject(UUID.randomUUID());
        cameraObject.setName("Camera obj");
        // TODO Make camera component and associate it to the game object
        this.cameraObject = cameraObject;

        this.instantiatedGameObjects = new ArrayList<>();
        this.id = id;

        this.gameStateRenderer = new GameStateRenderer();
    }

    /**
     * Returns the id of the game state
     *
     * @return String
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Sets the id of the game state
     *
     * @param id = The id of the game state
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Returns the game state renderer of the game state
     *
     * @return GameStateRenderer
     */
    public GameStateRenderer getGameStateRenderer()
    {
        return this.gameStateRenderer;
    }

    /**
     * Sets the game state renderer of the game state
     *
     * @param gameStateRenderer = The new game state renderer
     */
    public void setGameStateRenderer(GameStateRenderer gameStateRenderer)
    {
        this.gameStateRenderer = gameStateRenderer;
    }

    /**
     * Returns the camera object of the game state
     *
     * @return GameObject
     */
    public GameObject getCameraObject()
    {
        return this.cameraObject;
    }

    /**
     * Sets the camera object of the game state
     *
     * @param cameraObject = The camera game object
     */
    public void setCameraObject(GameObject cameraObject)
    {
        // TODO Look if given camera object has the camera component
        this.cameraObject = cameraObject;
    }

    /**
     * Returns the input object of the game state
     *
     * @return GameObject
     */
    public GameObject getInputObject()
    {
        return this.inputObject;
    }

    /**
     * Sets the input object of the game state
     *
     * @param inputObject = The input game object
     */
    public void setInputObject(GameObject inputObject)
    {
        if(!(inputObject.hasComponent(InputHandler.class))) {
            System.err.println("Couldn't add GameObject '" + inputObject + "' as InputObject to " + this + ", as the given GameObject dosn't have the needed InputHandler component!");
            return;
        }
        this.inputObject = inputObject;
    }

    /**
     * Returns all instantiated game objects
     *
     * @return ArrayList<GameObject>
     */
    public ArrayList<GameObject> getInstantiatedGameObjects()
    {
        return this.instantiatedGameObjects;
    }

    /**
     * Removes GameObject bound to the given key
     *
     * @param removeObjectUUID = uuid of the game object that should get removed
     */
    public void removeInstantiatedGameObject(UUID removeObjectUUID)
    {
        for (GameObject gameObject : this.getInstantiatedGameObjects()) {
            if(gameObject.getGameObjectUUID().equals(removeObjectUUID)) {
                gameObject.dispose();
                this.instantiatedGameObjects.remove(gameObject);
                this.gameStateRenderer.removeGameObject(gameObject);
            }
        }
    }

    /**
     * Adds the given GameObject to the list of instantiated game objects,
     * that get updated for each frame
     *
     * @param gameObject = GameObject that should get updated each frame
     */
    public void addInstantiatedGameObject(GameObject gameObject)
    {
        this.instantiatedGameObjects.add(gameObject);
        this.gameStateRenderer.addGameObject(gameObject);
    }

    /**
     * Initializes the InputHandler
     */
    protected void initializeInputHandler()
    {
        InputHandler inputHandler = (InputHandler) this.inputObject.getComponentFromClass(InputHandler.class);
        if(!inputHandler.isActiveInputHandler()) {
            inputHandler.setToActiveInputHandler();
        }
    }

    /**
     * Performs an updateUniforms for all instantiated game objects
     */
    public void update()
    {
        ManagerMall.getGameObjectManager().updateInstantiatedGameObjects(this.getInstantiatedGameObjects());
        ManagerMall.getShaderManager().updateGlobalShaders();
    }

    /**
     * Performs a render for all game objects that are in the render cycle of the game state renderer
     */
    public void render()
    {
        ManagerMall.getGameObjectManager().callRender(this);
    }

    @Override
    public String toString() {
        return "GameState{id=" + this.getId() + "}";
    }

    /**
     * This method should be called before the start of the game state when it gets loaded.
     */
    public void initializeGameState()
    {
        this.initializeInputHandler();
    }
}
