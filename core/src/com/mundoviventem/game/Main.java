package com.mundoviventem.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.mundoviventem.io.FileManager;
import com.mundoviventem.util.UpdateExecutor;

/**
 * Main class for the application
 */
public class Main extends ApplicationAdapter {

	public static String Project_Path;

	private Playground playground;
	private UpdateExecutor updateExecutor;

	@Override
	public void create () {
		Main.Project_Path = FileManager.determineProjectPath();

		// Initializes all Managers that have things that should get disposed
		ManagerMall.getDisposingManager().addNewDisposableObject(ManagerMall.getTextureRepository());
		ManagerMall.getDisposingManager().addNewDisposableObject(ManagerMall.getRenderManager());
		ManagerMall.getDisposingManager().addNewDisposableObject(ManagerMall.getSoundRepository());

		// Testing ground for development purposes
		this.playground = new Playground();

		this.updateExecutor = ManagerMall.getUpdateExecutor();
		this.updateExecutor.startFixedUpdateThread();
	}

	@Override
	public void render ()
	{
		playground.render();

		this.updateExecutor.update();
		this.updateExecutor.lateUpdate();
	}
	
	@Override
	public void dispose () {
		ManagerMall.getDisposingManager().killThemAll();
	}
}
