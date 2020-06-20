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

    /**
     * Returns the instance of the RenderManager
     *
     * @return RenderManager
     */
    public static RenderManager getRenderManager()
    {
        return ManagerMall.renderManager;
    }

    /**
     * Returns the instance of the DisposingManager
     *
     * @return DisposingManager
     */
    public static DisposingManager getDisposingManager()
    {
        return ManagerMall.disposingManager;
    }

    /**
     * Returns the instance of the GameObjectManager
     *
     * @return GameObjectManager
     */
    public static GameObjectManager getGameObjectManager()
    {
        return ManagerMall.gameObjectManager;
    }

    /**
     * Returns the instance of the ShaderManager
     *
     * @return ShaderManager
     */
    public static ShaderManager getShaderManager()
    {
        return ManagerMall.shaderManager;
    }

    /**
     * Returns the instance of the TextureRepository
     *
     * @return TextureRepository
     */
    public static TextureRepository getTextureRepository()
    {
        return ManagerMall.textureRepository;
    }

    /**
     * Returns the instance of the SoundRepository
     *
     * @return SoundRepository
     */
    public static SoundRepository getSoundRepository()
    {
        return ManagerMall.soundRepository;
    }
}
