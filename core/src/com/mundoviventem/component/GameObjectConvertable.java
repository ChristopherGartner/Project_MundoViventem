package com.mundoviventem.component;

import com.mundoviventem.component.game_objects.GameObject;

/**
 * Interface for all objects that can be converted into game objects
 */
public interface GameObjectConvertable
{

    /**
     * This method is used to convert this object into a game object
     *
     * @return GameObject
     */
    public GameObject convertToGameObject();

}
