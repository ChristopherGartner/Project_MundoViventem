package com.mundoviventem.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.blocks.GrayStoneBrickBlock;
import com.mundoviventem.component.core.InputHandler;
import com.mundoviventem.component.core.SoundManager;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.WorldComponent;
import com.mundoviventem.component.core.sound_manager.SoundConfiguration;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.io.file_type_managers.KeyValueFileByteManager;
import com.mundoviventem.render.RenderParams;
import com.mundoviventem.render.ShaderManager;
import com.mundoviventem.render.ShaderParams;
import com.mundoviventem.render.TextureParams;
import com.mundoviventem.states.MenuState;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Testing class for testing purposes.
 * Should not contain code in finished product
 */
public class Playground
{

    GameObject world;
    GameObject soundObject;
    GameObject inputObject;
    GameObject gray_stone_brick_test;

    MenuState menuState;

    public Playground()
    {
        this.create();
    }

    /**
     * Gets called when game gets started
     */
    public void create()
    {

        this.menuState = new MenuState();
        /*
        GameObject testimg = new GameObject(UUID.randomUUID());
        testimg.setName("Test Image");
        menuState.addInstantiatedGameObject(testimg);
        testimg.getTransformComponent().setBackgroundLevel(0);
        SpriteRenderer sr = new SpriteRenderer(testimg.getTransformComponent());
        testimg.addComponent(sr);
        menuState.getGameStateRenderer().addGameObject(testimg);
        TextureParams a = new TextureParams("test_img", new Vector2(150,150), new Vector2(50, 50));
        ArrayList<Vector2> al = new ArrayList<>();
        al.add(new Vector2(100,100));
        al.add(new Vector2(1000,100));
        al.add(new Vector2(100,1000));
        al.add(new Vector2(1000,1000));
        String newShaderPair = ManagerMall.getShaderManager().getCustomPair("DEFAULT", "rainbow");
        ShaderParams sp = new ShaderParams(newShaderPair);
        TextureParams b = new TextureParams("badlogic", al, TextureParams.USE_TEX_SIZE, sp);
        ArrayList<TextureParams> tl1 = new ArrayList<>();
        tl1.add(a);
        ArrayList<TextureParams> tl2 = new ArrayList<>();
        tl2.add(b);
        TreeMap<Integer, ArrayList<TextureParams>> map1 = new TreeMap<>();
        map1.put(1, tl1);
        map1.put(0, tl2);

        TreeMap<ShaderManager.GlobalShader, ShaderParams> globalShaders = new TreeMap<>();
        globalShaders.put(ShaderManager.GlobalShader.BLOODY_SCREEN, new ShaderParams("test", new ArrayList<>(), 3.0));//
                //ManagerMall.getShaderManager().SHADER_LIFETIME_INFINITY));

        RenderParams rp1 = new RenderParams(map1, globalShaders);
        sr.setNewRenderParams(rp1);

        GameObject huso = new GameObject(UUID.randomUUID());
        huso.setName("Guter Mann");
        menuState.addInstantiatedGameObject(huso);
        huso.getTransformComponent().setBackgroundLevel(1);
        SpriteRenderer huso_sr = new SpriteRenderer(huso.getTransformComponent());
        huso.addComponent(huso_sr);
        menuState.getGameStateRenderer().addGameObject(huso);
        RenderParams rp2 = new RenderParams("hitler", new Vector2(300,300), TextureParams.USE_TEX_SIZE);
        huso_sr.setNewRenderParams(rp2);
*/
        inputObject = new GameObject(UUID.randomUUID());
        inputObject.setName("Leon's Nightmare");
        InputHandler inputHandler = new InputHandler();
        inputObject.addComponent(inputHandler);
        menuState.setInputObject(inputObject);
        menuState.initializeGameState();


        this.gray_stone_brick_test = (new GrayStoneBrickBlock(new Vector2(0, 0))).convertToGameObject();
        this.gray_stone_brick_test.setName("Test Gray Stone Brick");
        ((SoundManager) this.gray_stone_brick_test.getComponentFromClass(SoundManager.class)).getSoundRegistrations().forEach(soundRegistration -> {soundRegistration.setPlaying(true);});
        menuState.addInstantiatedGameObject(this.gray_stone_brick_test);
        menuState.getGameStateRenderer().addGameObject(this.gray_stone_brick_test);


        world = new GameObject(UUID.randomUUID());
        world.setName("World");
        world.addComponent(new WorldComponent(new Vector2(10000, 5000)));
        world.addComponent(new WorldComponent(new Vector2(20, 30)));
        menuState.addInstantiatedGameObject(world);


        Sound sound = ManagerMall.getSoundRepository().getSound("test_song");

        soundObject = new GameObject(UUID.randomUUID());
        soundObject.setName("Sound Object");
        SoundManager soundManager = new SoundManager();
        soundObject.addComponent(soundManager);
        ((SoundManager) soundObject.getComponentFromClass(SoundManager.class)).registerNewSound(sound, "Test_Registration", new SoundConfiguration());
        ((SoundManager) soundObject.getComponentFromClass(SoundManager.class)).getSoundRegistrations().forEach((soundRegistration -> {soundRegistration.setPlaying(true);}));
        menuState.addInstantiatedGameObject(soundObject);

        KeyValueFileByteManager keyValueFileManager = new KeyValueFileByteManager();


//		ArrayList<String> contentOfFile = new ArrayList<>();
//		for (int i = 0; i < 1000; i++) {
//			contentOfFile.add(i + "===Paul_Ist_Gay");
//		}
        //keyValueFileManager.createFile(contentOfFile, "files\\test");

        try {
            System.out.println(keyValueFileManager.getContent("files\\test"));
        } catch (Exception exception) {
            System.err.println("Du Lappen");
        }


        // Pushes the Menu Game State to the top
        ManagerMall.getGameStateManager().push(menuState);
    }

    /**
     * Gets called each render cycle
     */
    public void render()
    {

    }

}
