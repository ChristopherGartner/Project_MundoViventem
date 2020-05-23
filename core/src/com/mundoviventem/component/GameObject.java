package com.mundoviventem.component;

import com.mundoviventem.component.core.BaseComponent;
import com.mundoviventem.component.core.Transform;

import java.util.ArrayList;

/**
 * resembles implementation of Component pattern
 */
public abstract class GameObject
{

    private ArrayList<BaseComponent> components;

    /**
     * Initializes components list
     */
    public GameObject()
    {
        this.components = new ArrayList<>();

        // Every game object has a dedicated transform instance
        this.components.add(new Transform());
        System.out.println("asd");
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
        this.components = components;
    }

    /**
     * Adds a new component to the game object
     *
     * @param component = Component that should be added to the game object
     */
    public void addComponent(BaseComponent component)
    {
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
     * This method gets called once per tick. It should contain all the logic by the game object.
     * If super call is missing, the components wont get updated
     */
    public void update()
    {
        for (BaseComponent component: this.components) {
            component.update();
        }
    }

}
