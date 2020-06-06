package com.mundoviventem.blocks;

import com.badlogic.gdx.math.Vector2;
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
     * The size of blocks
     */
    public static final Vector2 BLOCK_SIZE = new Vector2(32, 32);

    /**
     * Whether the block can be rendered or not. Should get overwritten in
     * child class if it shouldn't be the case
     */
    protected boolean isRenderableBlock = true;

    private GameObject blockObject;

    /**
     * Constructor of BaseBlock.
     * Creates game object with all it's needed component.
     * In child classes this game object can get extended.
     */
    public BaseBlock()
    {
        GameObject blockObject = new GameObject(UUID.randomUUID());

        if(isRenderableBlock()) {
            blockObject.addComponent(new SpriteRenderer());
        }
        this.blockObject = blockObject;
    }

    /**
     * Returns the game object of the given block
     *
     * @return GameObject
     */
    protected GameObject getBlockGameObject()
    {
        return this.blockObject;
    }

    /**
     * Sets the game object of the given block
     *
     * @param blockObject = GameObject of the given block
     */
    protected void setBlockGameObject(GameObject blockObject)
    {
        this.blockObject = blockObject;
    }

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
        return null;
    }
}
