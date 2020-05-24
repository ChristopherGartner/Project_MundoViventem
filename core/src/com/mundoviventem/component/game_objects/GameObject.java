package com.mundoviventem.component.game_objects;

import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.Transform;

import java.util.ArrayList;

/**
 * resembles implementation of Component pattern
 */
public abstract class GameObject
{

    private ArrayList<BaseComponent> components;
    private boolean isSleeping;

    /**
     * Initializes components list
     */
    public GameObject()
    {
        this.components = new ArrayList<>();

        // Every game object has a dedicated transform instance
        this.components.add(new Transform());
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
        this.update();
    }

    /**
     * This method gets called once per tick. It should contain all the logic by the game object.
     */
    protected abstract void update();

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
