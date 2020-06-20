package com.mundoviventem.game;

import com.mundoviventem.component.GameObjectManager;
import com.mundoviventem.component.RenderManager;
import com.mundoviventem.render.ShaderManager;
import com.mundoviventem.sound.SoundRepository;
import com.mundoviventem.texture.TextureRepository;

/**
 * Here you will find everything for your manager uses
 */
public class ManagerMall
{

    private static RenderManager renderManager         = new RenderManager();
    private static DisposingManager disposingManager   = new DisposingManager();
    private static GameObjectManager gameObjectManager = new GameObjectManager();
    private static ShaderManager shaderManager         = new ShaderManager();

    private static TextureRepository textureRepository = new TextureRepository();
    private static SoundRepository soundRepository     = new SoundRepository();

    public static RenderManager getRenderManager()
    {
        return ManagerMall.renderManager;
    }

    public static DisposingManager getDisposingManager()
    {
        return ManagerMall.disposingManager;
    }

    public static GameObjectManager getGameObjectManager()
    {
        return ManagerMall.gameObjectManager;
    }

    public static ShaderManager getShaderManager()
    {
        return ManagerMall.shaderManager;
    }

    public static TextureRepository getTextureRepository()
    {
        return ManagerMall.textureRepository;
    }

    public static SoundRepository getSoundRepository()
    {
        return ManagerMall.soundRepository;
    }
}
