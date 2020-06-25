package com.mundoviventem.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.mundoviventem.io.file_type_managers.KeyValueFileResourceManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all sound-alias mappings
 */
public class SoundRepository implements Disposable
{

    public static final String SOUNDS_FOLDER          = "sounds";
    public static final String SOUNDS_ALIAS_FILE_NAME = "files\\sound_aliases";

    private HashMap<String, Sound> sounds;

    /**
     * Reads of the data of the sound aliases
     */
    public SoundRepository()
    {
        KeyValueFileResourceManager keyValueFileSoundManager = new KeyValueFileResourceManager(SOUNDS_FOLDER);
        try {
            HashMap<String, String> soundPathsToString = keyValueFileSoundManager.getContent(SOUNDS_ALIAS_FILE_NAME);
            HashMap<String, Sound> sounds              = new HashMap<>();

            for (Map.Entry<String, String> pair : soundPathsToString.entrySet()) {
                sounds.put(pair.getKey(), Gdx.audio.newSound(Gdx.files.internal(SOUNDS_FOLDER + "\\" + pair.getValue())));
            }

            this.sounds = sounds;
        } catch (Exception exception) {
            System.err.println("Could not load sound aliases of file '" + SOUNDS_ALIAS_FILE_NAME + "'");
        }
    }

    /**
     * Returns associated sound of the given alias
     *
     * @param alias = The alias, which is directed to the sound sound
     *
     * @return Sound
     */
    public Sound getSound(String alias)
    {
        return this.sounds.get(alias);
    }

    @Override
    public void dispose() {
        for(Map.Entry<String, Sound> pair : this.sounds.entrySet()) {
            // Dispose given initialized sound
            pair.getValue().dispose();
        }
    }
}
