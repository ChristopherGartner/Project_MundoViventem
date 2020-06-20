package com.mundoviventem.texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.mundoviventem.io.file_type_managers.KeyValueFileResourceManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Contains all texture-alias mappings
 */
public class TextureRepository implements Disposable
{

    public static final String TEXTURES_FOLDER         = "textures";
    public static final String TEXTURE_ALIAS_FILE_NAME = "files\\texture_aliases";

    private HashMap<String, Texture> textures;

    /**
     * Reads of the data of the texture aliases
     */
    public TextureRepository()
    {
        KeyValueFileResourceManager keyValueFileTextureManager = new KeyValueFileResourceManager(TEXTURES_FOLDER);
        try {
            HashMap<String, String> texturePathString = keyValueFileTextureManager.getContent(TEXTURE_ALIAS_FILE_NAME);
            HashMap<String, Texture> textures         = new HashMap<>();

            for (Map.Entry<String, String> pair : texturePathString.entrySet()) {
                textures.put(pair.getKey(), new Texture(TEXTURES_FOLDER + "\\" + pair.getValue()));
            }

            this.textures = textures;
        } catch (Exception exception) {
            System.err.println("Could not load texture aliases of file '" + TEXTURE_ALIAS_FILE_NAME + "'");
        }
    }

    /**
     * Returns associated texture of the given alias
     *
     * @param alias = The alias, which is directed to the requested texture
     *
     * @return Texture
     */
    public Texture getTexture(String alias)
    {
        return textures.get(alias);
    }

    @Override
    public void dispose()
    {
        for(Map.Entry<String, Texture> pair : this.textures.entrySet()) {
            // Dispose given initialized texture
            pair.getValue().dispose();
        }
    }
}
