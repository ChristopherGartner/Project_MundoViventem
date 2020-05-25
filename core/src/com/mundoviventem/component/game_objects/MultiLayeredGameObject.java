package com.mundoviventem.component.game_objects;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Base class for game objects that consist of other game objects
 */
public class MultiLayeredGameObject extends GameObject
{

    protected ArrayList<GameObject> subComponents;

    /**
     * Initializes the sub component array list
     */
    public MultiLayeredGameObject(UUID uuid)
    {
        super(uuid);
        this.subComponents = new ArrayList<>();
    }

    /**
     * Method for adding a new sub component
     *
     * @param subComponent = Sub GameObject that should be added
     */
    public void addSubComponent(GameObject subComponent)
    {
        this.subComponents.add(subComponent);
    }

    /**
     * Removes sub component by given uuid
     *
     * @param removeObjectUUID = the UUID of the sub game object that should get removed
     */
    public void removeSubComponent(UUID removeObjectUUID)
    {
        this.subComponents.removeIf(gameObject -> removeObjectUUID.equals(gameObject.getGameObjectUUID()));
    }

    /**
     * If this method gets overwritten, it is important to keep the parent call.
     * Otherwise the sub game objects wont get updated.
     */
    protected void updateSubGameObjects()
    {
        for (GameObject subGameObject: this.subComponents) {
            subGameObject.updateGameObject();
        }
    }

    @Override
    public void updateGameObject()
    {
        super.updateGameObject();
        this.updateSubGameObjects();
    }
}
