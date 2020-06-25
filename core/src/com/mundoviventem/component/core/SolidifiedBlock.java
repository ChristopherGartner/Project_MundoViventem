package com.mundoviventem.component.core;

/**
 * Component for solid blocks containing all data needed for them
 */
public class SolidifiedBlock extends BaseComponent
{

    /**
     * The different material types of the blocks
     */
    public enum Block_Material
    {
        Stone,
    }

    private float blockHardness;
    private Block_Material blockMaterial;

    /**
     * Returns the hardness of the block
     *
     * @return float
     */
    public float getBlockHardness()
    {
        return this.blockHardness;
    }

    /**
     * Sets the hardness of the block
     *
     * @param blockHardness = The hardness of the block
     */
    public void setBlockHardness(float blockHardness)
    {
        this.blockHardness = blockHardness;
    }

    /**
     * Returns the material of the given block
     *
     * @return Block_Material
     */
    public Block_Material getBlockMaterial()
    {
        return this.blockMaterial;
    }

    /**
     * Sets the material of the block
     *
     * @param blockMaterial = the material of the block
     */
    public void setBlockMaterial(Block_Material blockMaterial)
    {
        this.blockMaterial = blockMaterial;
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void update() {

    }

    @Override
    public void gameObjectStartsSleeping() {

    }

    @Override
    public void gameObjectAwakens() {

    }
}
