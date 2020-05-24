package com.mundoviventem.texture;

import com.mundoviventem.io.file_type_managers.KeyValueFileTextureManager;

import java.util.HashMap;

/**
 * Contains all texture-alias mappings
 */
public class TextureRepository
{

    public static final String TEXTURES_FOLDER         = "textures\\";
    public static final String TEXTURE_ALIAS_FILE_NAME = "files\\texture_aliases";

    private HashMap<String, String> textures;

    /**
     * Reads of the data of the texture aliases
     */
    public TextureRepository()
    {
        KeyValueFileTextureManager keyValueFileTextureManager = new KeyValueFileTextureManager();
        try {
            this.textures = keyValueFileTextureManager.getContent(TEXTURE_ALIAS_FILE_NAME);
        } catch (Exception exception) {
            System.err.println("Could not load texture aliases of file '" + TEXTURE_ALIAS_FILE_NAME + "'");
        }
    }

    /**
     * Returns associated texture of the given alias
     *
     * @param alias = The alias, which is directed to the requested texture
     *
     * @return String
     */
    public String getTexture(String alias)
    {
        return TEXTURES_FOLDER + this.textures.get(alias);
    }
}
