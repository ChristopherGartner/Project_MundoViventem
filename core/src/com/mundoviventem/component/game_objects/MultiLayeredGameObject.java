package com.mundoviventem.component.game_objects;

import com.mundoviventem.component.game_objects.GameObject;

import java.util.ArrayList;

/**
 * Base class for game objects that consist of other game objects
 */
public abstract class MultiLayeredGameObject extends GameObject
{

    protected ArrayList<GameObject> subComponents;

    /**
     * Initializes the sub component array list
     */
    public MultiLayeredGameObject()
    {
        super();
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
     * This method needs to get implemented in every daughter class, as
     * the daughter should determine what the conditions of removing of the sub component are
     */
    public abstract void removeSubComponent();

    /**
     * If this method gets overwritten, it is important to keep the parent call.
     * Otherwise the sub game objects wont get updated.
     */
    protected void updateSubGameObjects()
    {
        for (GameObject subGameObject: this.subComponents) {
            subGameObject.update();
        }
    }

    @Override
    public void updateGameObject()
    {
        super.updateGameObject();
        this.updateSubGameObjects();
    }
}
