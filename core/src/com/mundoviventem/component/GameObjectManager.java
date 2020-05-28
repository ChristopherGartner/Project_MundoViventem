package com.mundoviventem.component;

import com.mundoviventem.component.game_objects.GameObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages all instantiated game objects
 */
public class GameObjectManager
{
    private ArrayList<GameObject> instantiatedGameObjects;

    /**
     * Initializes game objects
     */
    public GameObjectManager()
    {
        this.instantiatedGameObjects = new ArrayList<>();
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
    }

    /**
     * Calls update method for all instantiated game objects
     */
    public void updateInstantiatedGameObjects()
    {
        this.instantiatedGameObjects.forEach((gameObject) -> {
            if(!gameObject.isGameObjectSleeping()) {
                gameObject.updateGameObject();
                System.out.println("Update successfully for GameObject with the name '" + gameObject.getName() + "' and the UUID '" + gameObject.getGameObjectUUID() + "'");
            }
        });
    }
}
