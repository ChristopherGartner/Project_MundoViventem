package com.mundoviventem.component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages all instantiated game objects
 */
public class GameObjectManager
{
    private HashMap<UUID, GameObject> instantiatedGameObjects;

    /**
     * Initializes game objects
     */
    public GameObjectManager()
    {
        this.instantiatedGameObjects = new HashMap<>();
    }

    /**
     * Returns all instantiated game objects
     *
     * @return HashMap<UUID, GameObject>
     */
    public HashMap<UUID, GameObject> getInstantiatedGameObjects()
    {
        return this.instantiatedGameObjects;
    }

    /**
     * Removes GameObject bound to the given key
     *
     * @param key = Key as UUID
     */
    public void removeInstantiatedGameObject(UUID key)
    {
        this.instantiatedGameObjects.remove(key);
    }

    /**
     * Adds the given GameObject to the list of instantiated game objects,
     * that get updated for each frame
     *
     * @param key        = Key as UUID
     * @param gameObject = GameObject that should get updated each frame
     */
    public void addInstantiatedGameObject(UUID key, GameObject gameObject)
    {
        this.instantiatedGameObjects.put(key, gameObject);
    }

    /**
     * Calls update method for all instantiated game objects
     */
    public void updateInstantiatedGameObjects()
    {
        for (Map.Entry<UUID, GameObject> entry : this.instantiatedGameObjects.entrySet()) {
            UUID uuid             = entry.getKey();
            GameObject gameObject = entry.getValue();

            if(!gameObject.isGameObjectSleeping()) {
                gameObject.updateGameObject();
                System.out.println("Update successfully for GameObject " + uuid);
            }
        }
    }
}
