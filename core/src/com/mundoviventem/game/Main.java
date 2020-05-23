package com.mundoviventem.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mundoviventem.component.EmptyGameObject;
import com.mundoviventem.component.GameObjectManager;

import java.util.UUID;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameObjectManager gameObjectManager;
	EmptyGameObject emptyGameObject;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		gameObjectManager = new GameObjectManager();
		emptyGameObject = new EmptyGameObject();
		gameObjectManager.addInstantiatedGameObject(UUID.randomUUID(), emptyGameObject);
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
