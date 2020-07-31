package com.mundoviventem.world;

import com.badlogic.gdx.math.Vector2;

public class World {

    private Vector2 worldSize;
    private ChunkManager chunkManager;

    public World(Vector2 size){
        worldSize = size;
        chunkManager = new ChunkManager(size);
        chunkManager.generateNewWorld();
    }


}
