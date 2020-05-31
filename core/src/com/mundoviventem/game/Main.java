package com.mundoviventem.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mundoviventem.component.GameObjectManager;
import com.mundoviventem.component.core.WorldComponent;
import com.mundoviventem.component.game_objects.GameObject;
import com.mundoviventem.io.FileManager;
import com.mundoviventem.io.file_type_managers.KeyValueFileByteManager;
import com.mundoviventem.texture.TextureRepository;

import java.util.UUID;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameObjectManager gameObjectManager;

	GameObject gameObject;
	GameObject world;

	TextureRepository textureRepository;

	public static String Project_Path;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Main.Project_Path = FileManager.determineProjectPath();
		gameObjectManager = new GameObjectManager();

		gameObject = new GameObject(UUID.randomUUID());
		gameObject.setName("Hannibal Lecter");
		gameObjectManager.addInstantiatedGameObject(gameObject);

		GameObject testimg = new GameObject(UUID.randomUUID());
		testimg.setName("Test Image");
		gameObjectManager.addInstantiatedGameObject(testimg);

		world = new GameObject(UUID.randomUUID());
		world.setName("World");
		world.addComponent(new WorldComponent(new Vector2(100, 50)));
		gameObjectManager.addInstantiatedGameObject(world);

		textureRepository = new TextureRepository();
		img = new Texture(textureRepository.getTexture("badlogic"));

		KeyValueFileByteManager keyValueFileManager = new KeyValueFileByteManager();



		gameObjectManager.callRender();



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
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		gameObjectManager.updateInstantiatedGameObjects();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
