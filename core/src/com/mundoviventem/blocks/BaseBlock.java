package com.mundoviventem.blocks;

import com.mundoviventem.component.GameObjectConvertable;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.game_objects.GameObject;

import java.util.UUID;

/**
 * Base class for blocks
 */
public abstract class BaseBlock implements GameObjectConvertable
{
    /**
     * Whether the block can be rendered or not. Should get overwritten in
     * child class if it shouldn't be the case
     */
    protected boolean isRenderableBlock = true;

    /**
     * Returns if the block is renderable. If not, the game object
     * of the block won't have a SpriteRenderer component.
     *
     * @return boolean
     */
    public boolean isRenderableBlock()
    {
        return this.isRenderableBlock;
    }

    @Override
    public GameObject convertToGameObject()
    {
        GameObject blockObject = new GameObject(UUID.randomUUID());
        if(isRenderableBlock()) {
            blockObject.addComponent(new SpriteRenderer(blockObject.getTransformComponent()));
        }
        return blockObject;
    }
}
