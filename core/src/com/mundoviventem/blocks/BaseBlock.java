package com.mundoviventem.blocks;

import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.component.GameObjectConvertable;
import com.mundoviventem.component.core.SolidifiedBlock;
import com.mundoviventem.component.core.SoundManager;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.sound_manager.SoundBuild;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.render.RenderParams;
import com.mundoviventem.render.TextureParams;
import com.mundoviventem.util.Printer;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Base class for blocks
 */
public abstract class BaseBlock implements GameObjectConvertable
{

    /**
     * Constant for Block size
     */
    final public static Vector2 BLOCK_SIZE = new Vector2(16, 16);

    /**
     * Whether the block can be rendered or not. Should get overwritten in
     * child class if it shouldn't be the case
     */
    protected boolean isRenderableBlock = true;

    /**
     * Whether the block has an associated sound manager or not
     */
    protected boolean hasSoundManager = false;

    private String id;
    private SolidifiedBlock.Block_Material blockMaterial = null;
    private float blockHardness = 1.0f;
    private Vector2 initialLocation = new Vector2();

    /**
     * Constructor for BaseBlock
     *
     * @param id              = The id of the block
     * @param initialLocation = The initial location where the block is in the world
     */
    public BaseBlock(String id, Vector2 initialLocation)
    {
        this.id = id;
        this.initialLocation = initialLocation;
    }

    /**
     * Returns the id of the block
     *
     * @return String
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Returns the initial location of the block
     *
     * @return Vector2
     */
    public Vector2 getInitialLocation()
    {
        return this.initialLocation;
    }

    /**
     * Returns the material of the block
     *
     * @return SolidifiedBlock.Block_Material
     */
    public SolidifiedBlock.Block_Material getBlockMaterial()
    {
        return this.blockMaterial;
    }

    /**
     * Returns the hardness of a block
     *
     * @return float
     */
    public float getBlockHardness()
    {
        return this.blockHardness;
    }

    /**
     * Sets the hardness of a block
     *
     * @param blockHardness = the hardness of the block
     */
    protected void setBlockHardness(float blockHardness)
    {
        this.blockHardness = blockHardness;
    }

    /**
     * Sets the material of the block
     *
     * @param blockMaterial = the material of the block
     */
    protected void setBlockMaterial(SolidifiedBlock.Block_Material blockMaterial)
    {
        this.blockMaterial = blockMaterial;
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

    /**
     * Returns the render params. If the block is renderable, this should be overwritten and return all render params
     *
     * @return RenderParams
     */
    protected RenderParams getRenderParams()
    {
        TreeMap<Integer, ArrayList<TextureParams>> treeMap = new TreeMap<>();
        return new RenderParams(treeMap);
    }

    @Override
    public GameObject convertToGameObject()
    {
        GameObject blockObject = new GameObject(UUID.randomUUID());
        if(this.isRenderableBlock()) {
            if(this.getRenderParams().getRenderSequence().isEmpty()) {
                Printer.print("WARNING: Block with id '" + this.getId() + "' is renderable, but has no RenderParams", Printer.Printing_State.RAW);
            }
            blockObject.addComponent(new SpriteRenderer(blockObject.getTransformComponent(), this.getRenderParams()));
        }

        if(hasSoundManager) {
            SoundManager soundManager = new SoundManager();
            if(this.getAllSoundBuilds().isEmpty()) {
                Printer.print("WARNING: Block with id '" + this.getId() + "' has a SoundManager component, but no sound builds.", Printer.Printing_State.RAW);
            }
            for (SoundBuild soundBuild : this.getAllSoundBuilds()) {
                soundManager.registerNewSound(soundBuild.getSound(), soundBuild.getRegistrationName(), soundBuild.getSoundConfiguration());
            }
            blockObject.addComponent(soundManager);
        }

        if(this.getBlockMaterial() != null) {
            SolidifiedBlock solidifiedBlockComponent = new SolidifiedBlock();
            solidifiedBlockComponent.setBlockHardness(this.getBlockHardness());
            solidifiedBlockComponent.setBlockMaterial(this.getBlockMaterial());

            blockObject.addComponent(solidifiedBlockComponent);
        }

        return blockObject;
    }
}
