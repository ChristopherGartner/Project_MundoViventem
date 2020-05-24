package com.mundoviventem.io.file_type_managers;

import com.mundoviventem.game.Main;
import com.mundoviventem.io.FileTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class for content mapping of ".kvft" files
 */
public class KeyValueFileTextureManager extends FileTypeManager
{

    public static final String KVFT_SEPERATION_REGEX = "=";

    @Override
    public String getFileType()
    {
        return FileTypes.KEY_VALUE_TEXTURE;
    }

    @Override
    protected boolean lint(String fileName)
    {
        String content = this.getFileContent(fileName);

        ArrayList<String> texturePathArrayList  = new ArrayList<>();
        ArrayList<String> textureAliasArrayList = new ArrayList<>();

        for(String line: content.split(System.lineSeparator())) {
            String[] lineArr = line.split(KVFT_SEPERATION_REGEX);

            String alias = lineArr[0];
            String path  = lineArr[1];

            if(lineArr.length != 2) {
                return false;
            }
            String filePath = Main.Project_Path + "\\core\\assets\\textures\\" + path;
            if(!(new File(filePath)).exists()) {
                return false;
            }
            textureAliasArrayList.add(alias);
            texturePathArrayList.add(path);
        }

        boolean pathsUnique = texturePathArrayList.size() == Arrays.stream(texturePathArrayList.toArray()).distinct().toArray().length;
        boolean aliasUnique = textureAliasArrayList.size() == Arrays.stream(textureAliasArrayList.toArray()).distinct().toArray().length;

        return pathsUnique && aliasUnique;
    }

    public HashMap<String, String> getContent(String fileName) throws Exception
    {
        if(!this.lint(fileName)) {
            throw new Exception("Linting of file '" + fileName + "' failed!");
        }

        String content = this.getFileContent(fileName);
        HashMap<String, String> convertedContent = new HashMap<>();

        for (String line: content.split(System.lineSeparator())) {
            String[] lineArr = line.split(KVFT_SEPERATION_REGEX);
            convertedContent.put(lineArr[0], lineArr[1]);
        }

        return convertedContent;
    }
}
