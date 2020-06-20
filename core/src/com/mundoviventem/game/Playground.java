package com.mundoviventem.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.component.core.SoundManager;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.WorldComponent;
import com.mundoviventem.component.core.sound_manager.SoundConfiguration;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.io.file_type_managers.KeyValueFileByteManager;
import com.mundoviventem.render.TextureList;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Testing class for testing purposes.
 * Should not contain code in finished product
 */
public class Playground
{

    GameObject world;
    GameObject soundObject;

    public Playground()
    {
        this.create();
    }

    /**
     * Gets called when game gets started
     */
    public void create()
    {
        ManagerMall.getShaderManager().readShaders(Main.Project_Path + "\\core\\assets\\shaders\\test_directory");


        GameObject testimg = new GameObject(UUID.randomUUID());
        testimg.setName("Test Image");
        ManagerMall.getGameObjectManager().addInstantiatedGameObject(testimg);
        testimg.getTransformComponent().setBackgroundLevel(0);
        SpriteRenderer sr = new SpriteRenderer(testimg.getTransformComponent());
        testimg.addComponent(sr);
        ManagerMall.getRenderManager().addGameObject(testimg);
        ShaderProgram.pedantic = false;
        ShaderProgram testShader = new ShaderProgram(new FileHandle(new File(Main.Project_Path + "\\core\\assets\\shaders\\test_directory\\shader.vert")),
                new FileHandle(new File(Main.Project_Path + "\\core\\assets\\shaders\\test_directory\\test.frag")));
        TextureList a = new TextureList("test_img", new Vector2(150,150));
        ArrayList<Vector2> al = new ArrayList<>();
        al.add(new Vector2(100,100));
        al.add(new Vector2(1000,100));
        al.add(new Vector2(100,1000));
        al.add(new Vector2(1000,1000));
        TextureList b = new TextureList("badlogic", al);

        sr.addTexture(a, 2);
        sr.addTexture(b, 1);



        GameObject huso = new GameObject(UUID.randomUUID());
        huso.setName("Hurensohn");
        ManagerMall.getGameObjectManager().addInstantiatedGameObject(huso);
        huso.getTransformComponent().setBackgroundLevel(1);
        SpriteRenderer huso_sr = new SpriteRenderer(huso.getTransformComponent(), testShader);
        huso.addComponent(huso_sr);
        ManagerMall.getRenderManager().addGameObject(huso);
        huso_sr.addTexture("test_img", 3, new Vector2(300,300));




        world = new GameObject(UUID.randomUUID());
        world.setName("World");
        world.addComponent(new WorldComponent(new Vector2(10000, 5000)));
        world.addComponent(new WorldComponent(new Vector2(20, 30)));
        ManagerMall.getGameObjectManager().addInstantiatedGameObject(world);



        Sound sound = Gdx.audio.newSound(Gdx.files.internal(ManagerMall.getSoundRepository().getSound("test_song")));

        soundObject = new GameObject(UUID.randomUUID());
        soundObject.setName("Sound Object");
        SoundManager soundManager = new SoundManager();
        soundObject.addComponent(soundManager);
        ((SoundManager) soundObject.getComponentFromClass(SoundManager.class)).registerNewSound(sound, "Test_Registration", new SoundConfiguration());
        ((SoundManager) soundObject.getComponentFromClass(SoundManager.class)).getSoundRegistrations().forEach((soundRegistration -> {soundRegistration.setPlaying(true);}));
        ManagerMall.getGameObjectManager().addInstantiatedGameObject(soundObject);

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
    }

    /**
     * Gets called each render cycle
     */
    public void render()
    {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ManagerMall.getGameObjectManager().callRender();
        ManagerMall.getGameObjectManager().updateInstantiatedGameObjects();
    }

}
