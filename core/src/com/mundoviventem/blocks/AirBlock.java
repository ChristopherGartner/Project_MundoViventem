package com.mundoviventem.blocks;

import com.badlogic.gdx.math.Vector2;

public class AirBlock extends BaseBlock {

    public AirBlock(Vector2 location){
        super("air", location);
        isRenderableBlock = false;
    }
}
