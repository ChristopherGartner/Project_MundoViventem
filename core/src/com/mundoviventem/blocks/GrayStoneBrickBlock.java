package com.mundoviventem.blocks;

import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.component.core.SolidifiedBlock;
import com.mundoviventem.component.core.sound_manager.SoundBuild;
import com.mundoviventem.component.core.sound_manager.SoundConfiguration;
import com.mundoviventem.game.ManagerMall;
import com.mundoviventem.render.RenderParams;
import com.mundoviventem.render.TextureParams;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Basic gray stone brick block
 */
public class GrayStoneBrickBlock extends BaseBlock
{

    /**
     * Constructor for BaseBlock
     */
    public GrayStoneBrickBlock(Vector2 initialLocation)
    {
        super("block_gray_stone_brick", initialLocation);
        this.setBlockMaterial(SolidifiedBlock.Block_Material.Stone);
        this.setBlockHardness(2.0f);
    }

    @Override
    protected ArrayList<SoundBuild> getAllSoundBuilds()
    {
        ArrayList<SoundBuild> soundBuilds = new ArrayList<>();

        SoundConfiguration soundConfiguration = new SoundConfiguration();
        soundConfiguration.setLooping(true);

        SoundBuild soundBuild = new SoundBuild("gray_stone_brick_base", soundConfiguration, ManagerMall.getSoundRepository().getSound("block_gray_stone_brick"));
        soundBuilds.add(soundBuild);

        return soundBuilds;
    }

    @Override
    protected RenderParams getRenderParams()
    {
        TreeMap<Integer, ArrayList<TextureParams>> treeMap = new TreeMap<>();

        ArrayList<Vector2> locations = new ArrayList<>();

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                locations.add(new Vector2(getInitialLocation().x+160*x, getInitialLocation().y+160*y));
            }
        }

        TextureParams textureParam = new TextureParams("block_gray_stone_brick", locations, new Vector2(160, 160));
        ArrayList<TextureParams> textureParams = new ArrayList<>();
        textureParams.add(textureParam);

        treeMap.put(-100, textureParams);
        RenderParams renderParams = new RenderParams(treeMap);

        return renderParams;
    }
}
