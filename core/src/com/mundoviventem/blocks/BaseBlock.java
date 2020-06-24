package com.mundoviventem.blocks;

import com.mundoviventem.component.GameObjectConvertable;
import com.mundoviventem.component.core.SoundManager;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.sound_manager.SoundBuild;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.util.Printer;

import java.util.ArrayList;
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
     * Whether the block has an associated sound manager or not
     */
    protected boolean hasSoundManager = true;

    private String id;

    public BaseBlock(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
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

    /**
     * If block has sound manager, then this method should get overwritten and
     * return all sound builds
     *
     * @return ArrayList<SoundBuild>
     */
    protected ArrayList<SoundBuild> getAllSoundBuilds()
    {
        return new ArrayList<>();
    }

    @Override
    public GameObject convertToGameObject()
    {
        GameObject blockObject = new GameObject(UUID.randomUUID());
        if(isRenderableBlock()) {
            blockObject.addComponent(new SpriteRenderer(blockObject.getTransformComponent()));
        }

        if(hasSoundManager) {
            SoundManager soundManager = new SoundManager();
            if(this.getAllSoundBuilds().isEmpty()) {
                Printer.print("WARNING: Block with id '" + this.getId() + "' has a SoundManager component, but no sound builds.", Printer.Printing_State.RAW);
            }
            for (SoundBuild soundBuild : this.getAllSoundBuilds()) {
                soundManager.registerNewSound(soundBuild.getSound(), soundBuild.getRegistrationName(), soundBuild.getSoundConfiguration());
            }
        }
        return blockObject;
    }
}
