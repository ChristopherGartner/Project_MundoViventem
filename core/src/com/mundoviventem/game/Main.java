package com.mundoviventem.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.component.GameObjectManager;
import com.mundoviventem.component.core.SoundManager;
import com.mundoviventem.component.core.SpriteRenderer;
import com.mundoviventem.component.core.WorldComponent;
import com.mundoviventem.component.core.sound_manager.SoundConfiguration;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.io.FileManager;
import com.mundoviventem.io.file_type_managers.KeyValueFileByteManager;
import com.mundoviventem.render.ShaderHandler;
import com.mundoviventem.render.TextureList;
import com.mundoviventem.sound.SoundRepository;
import com.mundoviventem.texture.TextureRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class Main extends ApplicationAdapter {
	public static SpriteBatch batch;
	GameObjectManager gameObjectManager;


	GameObject world;
	GameObject soundObject;

	public static TextureRepository textureRepository;
	SoundRepository soundRepository;

	public static String Project_Path;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Main.Project_Path = FileManager.determineProjectPath();
		gameObjectManager = new GameObjectManager();
		textureRepository = new TextureRepository();

		ShaderHandler sh = new ShaderHandler();
		sh.readShaders(Project_Path + "\\core\\assets\\shaders\\test_directory");


		GameObject testimg = new GameObject(UUID.randomUUID());
		testimg.setName("Test Image");
		gameObjectManager.addInstantiatedGameObject(testimg);
		testimg.getTransformComponent().setBackgroundLevel(0);
		SpriteRenderer sr = new SpriteRenderer(testimg.getTransformComponent());
		testimg.addComponent(sr);
		gameObjectManager.getRenderManager().addGameObject(testimg);
		ShaderProgram.pedantic = false;
		ShaderProgram testShader = new ShaderProgram(new FileHandle(new File(Project_Path + "\\core\\assets\\shaders\\test_directory\\shader.vert")),
				new FileHandle(new File(Project_Path + "\\core\\assets\\shaders\\test_directory\\test.frag")));
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
		gameObjectManager.addInstantiatedGameObject(huso);
		huso.getTransformComponent().setBackgroundLevel(1);
		SpriteRenderer huso_sr = new SpriteRenderer(huso.getTransformComponent(), testShader);
		huso.addComponent(huso_sr);
		gameObjectManager.getRenderManager().addGameObject(huso);
		huso_sr.addTexture("test_img", 3, new Vector2(300,300));




		world = new GameObject(UUID.randomUUID());
		world.setName("World");
		world.addComponent(new WorldComponent(new Vector2(10000, 5000)));
		world.addComponent(new WorldComponent(new Vector2(20, 30)));
		gameObjectManager.addInstantiatedGameObject(world);




		soundRepository = new SoundRepository();
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundRepository.getSound("test_song")));

		soundObject = new GameObject(UUID.randomUUID());
		soundObject.setName("Sound Object");
		SoundManager soundManager = new SoundManager();
		soundObject.addComponent(soundManager);
		((SoundManager) soundObject.getComponentFromClass(SoundManager.class)).registerNewSound(sound, "Test_Registration", new SoundConfiguration());
		((SoundManager) soundObject.getComponentFromClass(SoundManager.class)).getSoundRegistrations().forEach((soundRegistration -> {soundRegistration.setPlaying(true);}));
		gameObjectManager.addInstantiatedGameObject(soundObject);

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

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameObjectManager.callRender();
		gameObjectManager.updateInstantiatedGameObjects();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		textureRepository.dispose();
	}
}
