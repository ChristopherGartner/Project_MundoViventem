package com.mundoviventem.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mundoviventem.io.FileManager;

/**
 * Main class for the application
 */
public class Main extends ApplicationAdapter {

	public static String Project_Path;

	private Playground playground;

	@Override
	public void create () {
		Main.Project_Path = FileManager.determineProjectPath();

		// Initializes all Managers that have things that should get disposed
		ManagerMall.getDisposingManager().addNewDisposableObject(ManagerMall.getTextureRepository());
		ManagerMall.getDisposingManager().addNewDisposableObject(ManagerMall.getRenderManager());

		// Testing ground for development purposes
		this.playground = new Playground();
	}

	@Override
	public void render ()
	{
		playground.render();
	}
	
	@Override
	public void dispose () {
		ManagerMall.getDisposingManager().killThemAll();
	}
}
