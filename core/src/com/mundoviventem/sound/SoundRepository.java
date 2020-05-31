package com.mundoviventem.sound;

import com.mundoviventem.io.file_type_managers.KeyValueFileResourceManager;

import java.util.HashMap;

/**
 * Contains all sound-alias mappings
 */
public class SoundRepository
{

    public static final String SOUNDS_FOLDER          = "sounds";
    public static final String SOUNDS_ALIAS_FILE_NAME = "files\\sound_aliases";

    private HashMap<String, String> sounds;

    /**
     * Reads of the data of the sound aliases
     */
    public SoundRepository()
    {
        KeyValueFileResourceManager keyValueFileSoundManager = new KeyValueFileResourceManager(SOUNDS_FOLDER);
        try {
            this.sounds = keyValueFileSoundManager.getContent(SOUNDS_ALIAS_FILE_NAME);
        } catch (Exception exception) {
            System.err.println("Could not load sound aliases of file '" + SOUNDS_ALIAS_FILE_NAME + "'");
        }
    }

    /**
     * Returns associated sound of the given alias
     *
     * @param alias = The alias, which is directed to the sound sound
     *
     * @return String
     */
    public String getSound(String alias)
    {
        return SOUNDS_FOLDER + "\\" + this.sounds.get(alias);
    }
}
