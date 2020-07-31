package com.mundoviventem.world;

import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.blocks.BaseBlock;

import java.util.ArrayList;
import java.util.HashMap;

public class ChunkManager {

    /**
     * Manages the different zones of active Chunks
     */

    private Vector2 worldSize;
    private Vector2 chunkCount;

    private HashMap<Vector2, Chunk> allChunks = new HashMap<>();
    private ArrayList<ArrayList<Chunk>> chunksByRows = new ArrayList<>();
    private ArrayList<ArrayList<Chunk>> chunksByCols = new ArrayList<>();

    public ChunkManager(Vector2 size){
        worldSize = size;

        if(worldSize.x % Chunk.ChunkSize != 0 || worldSize.y % Chunk.ChunkSize != 0){
            //TODO proper exception handling
            System.err.println("Invalid world size");
            System.exit(1);
        }
        chunkCount = new Vector2(worldSize.x / Chunk.ChunkSize, worldSize.y / Chunk.ChunkSize);

        for(int i = 0; i < chunkCount.x; i++){
            chunksByCols.add(new ArrayList<>());
        }
        for(int i = 0; i < chunkCount.y; i++){
            chunksByRows.add(new ArrayList<>());
        }
    }


    public void generateNewWorld(){
        for(int x = 0; x < chunkCount.x; x++){
            for(int y = 0; y < chunkCount.y; y++){
                addChunk(Chunk.getRandomFilledChunk(new Vector2(x, y)), new Vector2(x, y));
            }
        }
    }


    private void addChunk(Chunk chunk, Vector2 location){
        allChunks.put(location, chunk);
        chunksByRows.get((int) location.y).add((int) location.x, chunk);
        chunksByCols.get((int) location.x).add((int) location.y, chunk);
    }
}
