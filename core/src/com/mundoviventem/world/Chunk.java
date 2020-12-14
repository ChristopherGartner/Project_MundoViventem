package com.mundoviventem.world;

import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.blocks.AirBlock;
import com.mundoviventem.blocks.BaseBlock;
import com.mundoviventem.blocks.GrayStoneBrickBlock;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.game.Playground;
import com.mundoviventem.util.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Chunk {

    public static final int ChunkSize = 32;

    private Vector2 chunkLocation;

    private HashMap<Vector2, BaseBlock> allBlocks = new HashMap<>();
    private ArrayList<ArrayList<BaseBlock>> blocksByRows = new ArrayList<>();
    private ArrayList<ArrayList<BaseBlock>> blocksByCols = new ArrayList<>();
    static long c = 0;

    public Chunk(Vector2 chunkCoords){
        chunkLocation = chunkCoords;
        for(int i = 0; i < ChunkSize; i++){
            blocksByRows.add(new ArrayList<>());
            blocksByCols.add(new ArrayList<>());
        }
    }

    public static Chunk getRandomFilledChunk(Vector2 chunkCoords){
        Chunk chunk = new Chunk(chunkCoords);
        Random rnd = new Random();
        for(int x = 0; x < ChunkSize; x++){
            for(int y = 0; y < ChunkSize; y++){
                BaseBlock block = null;
                if(rnd.nextBoolean()){
                    block = new AirBlock(new Vector2(chunkCoords.x * ChunkSize + x, chunkCoords.y * ChunkSize + y));
                } else {
                    block = new GrayStoneBrickBlock(new Vector2(chunkCoords.x * ChunkSize + x, chunkCoords.y * ChunkSize + y));
                }
                GameObject go = block.convertToGameObject();
                Playground.menuState.addInstantiatedGameObject(go);
                go.getTransformComponent().setBackgroundLevel(0);
                Playground.menuState.getGameStateRenderer().addGameObject(go);
                chunk.addBlock(block, new Vector2(x, y));
                c++;
                Printer.print("Chunk coords x= " + chunkCoords.x + " y=" + chunkCoords.y + " Added block to coords x=" + chunkCoords.x * ChunkSize + x + " y=" + chunkCoords.y * ChunkSize + y + " total " + c, Printer.Printing_State.RAW);
            }
        }
        return chunk;
    }

    private void addBlock(BaseBlock block, Vector2 location){
        allBlocks.put(location, block);
        blocksByRows.get((int) location.y).add((int) location.x, block);
        blocksByCols.get((int) location.x).add((int) location.y, block);
    }
}
