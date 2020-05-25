package com.mundoviventem.component.game_objects;

import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.Transform;

import java.util.ArrayList;
import java.util.UUID;

/**
 * resembles implementation of Component pattern
 */
public class GameObject
{

    private ArrayList<BaseComponent> components;
    private boolean isSleeping;
    private UUID gameObjectUUID;
    private String name;

    /**
     * Initializes components list.
     * Initial name of game object is always the uuid. Can be changed
     * with "setName" method.
     *
     * @param uuid = UUID of the game object
     */
    public GameObject(UUID uuid)
    {
        this.components = new ArrayList<>();

        // Every game object has a dedicated transform instance
        this.components.add(new Transform());

        this.setGameObjectUUID(uuid);
        this.setName(uuid.toString());
    }

    /**
     * Returns all components that are part of the given game object
     *
     * @return ArrayList<BaseComponent>
     */
    public ArrayList<BaseComponent> getComponents()
    {
        return this.components;
    }

    /**
     * Returns the UUID of the game object
     *
     * @return UUID
     */
    public UUID getGameObjectUUID()
    {
        return this.gameObjectUUID;
    }

    /**
     * Changes the value of the uuid attribute of the game object
     *
     * @param uuid = The new UUID of the game object
     */
    public void setGameObjectUUID(UUID uuid)
    {
        this.gameObjectUUID = uuid;
    }

    /**
     * Returns the name of the game object
     *
     * @return String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name of the game object
     *
     * @param name = Name of the game object
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the components of the game object
     *
     * @param components = All components the game object owns
     */
    public void setComponents(ArrayList<BaseComponent> components)
    {
        for (BaseComponent component: components) {
            component.setGameObject(this);
        }
        this.components = components;
    }

    /**
     * Adds a new component to the game object
     *
     * @param component = Component that should be added to the game object
     */
    public void addComponent(BaseComponent component)
    {
        component.setGameObject(this);
        this.components.add(component);
        component.onEnable();
    }

    /**
     * Removes a special component of the game object
     *
     * @param componentClass = Class of component that should be removed
     */
    public void removeComponent(Class componentClass)
    {
        BaseComponent component = this.getComponentFromClass(componentClass);
        component.setGameObject(null);
        component.onDisable();
        this.components.remove(component);
    }

    /**
     * Returns the component
     *
     * @param componentClass = the class of the component that should get removed
     *
     * @return Class
     */
    public BaseComponent getComponentFromClass(Class componentClass)
    {
        for(BaseComponent component : this.components) {
            if(component.getClass().equals(componentClass)) {
                return component;
            }
        }
        return null;
    }

    /**
     * Calls all update method of the game objects.
     * This method shouldn't get overwritten for normal
     * update functionality adding. Use "update()" method
     * instead
     */
    public void updateGameObject()
    {
        this.updateComponents();
    }

    /**
     * Updates each components. This gets called once per tick
     */
    protected void updateComponents()
    {
        for (BaseComponent component: this.components) {
            component.update();
        }
    }

    /**
     * Sets the sleeping state of game object to true.
     * This results in a stopping of updates for this object
     * while this state is enabled.
     */
    public void setGameObjectAsleep()
    {
        this.isSleeping = true;
    }

    /**
     * Sets the sleeping state of game object to false.
     * This results in a continuing of updates for this object
     * each tick.
     */
    public void setGameObjectAwake()
    {
        this.isSleeping = false;
    }

    /**
     * Returns whether the game object is asleep or not
     *
     * @return boolean
     */
    public boolean isGameObjectSleeping()
    {
        return this.isSleeping;
    }
}
