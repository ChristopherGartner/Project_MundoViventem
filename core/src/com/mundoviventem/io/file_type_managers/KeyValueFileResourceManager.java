package com.mundoviventem.io.file_type_managers;

import com.mundoviventem.game.Main;
import com.mundoviventem.io.FileTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class for content mapping of ".kvfr" files
 */
public class KeyValueFileResourceManager extends FileTypeManager
{
    public static final String KVFR_SEPERATION_REGEX = "=";
    private String resourceSpecificPath;

    /**
     * Constructor of KeyValueFileResourceManager.
     * Needs a resource specific path to validate the resources in this directory
     *
     * @param resourcePath = The resource specific path
     */
    public KeyValueFileResourceManager(String resourcePath)
    {
        this.resourceSpecificPath = resourcePath;
    }

    /**
     * Sets the resource specific path of the given key value file resource manager instance
     *
     * @return String
     */
    public String getResourceSpecificPath()
    {
        return this.resourceSpecificPath;
    }

    /**
     * Sets the resource specific path for this manager
     *
     * @param resourcePath = The resource specific path for the manager
     */
    public void setResourceSpecificPath(String resourcePath)
    {
        this.resourceSpecificPath = resourcePath;
    }

    @Override
    public String getFileType()
    {
        return FileTypes.KEY_VALUE_RESOURCE;
    }

    @Override
    protected boolean lint(String fileName)
    {
        String content = this.getFileContent(fileName);

        ArrayList<String> resourcePathArrayList  = new ArrayList<>();
        ArrayList<String> resourceAliasArrayList = new ArrayList<>();

        for(String line: content.split(System.lineSeparator())) {

            String lineWithRemovedEmptySpaces = line.replaceAll("\\s", "");

            // Replaces all empty space types and then splits the line
            if(!lineWithRemovedEmptySpaces.equals("")) {
                String[] lineArr = lineWithRemovedEmptySpaces.split(KVFR_SEPERATION_REGEX);

                String alias = lineArr[0];
                String path  = lineArr[1];

                // Validate that structure only consists of one key and one value
                if(lineArr.length != 2) {
                    return false;
                }

                String filePath = Main.Project_Path + "\\core\\assets\\" + this.getResourceSpecificPath() + "\\" + path;
                // Validate that given resource path is correct
                if(!(new File(filePath)).exists()) {
                    return false;
                }
                resourceAliasArrayList.add(alias);
                resourcePathArrayList.add(path);
            }
        }

        // Validate that resource paths and aliases are unique
        boolean pathsUnique = resourcePathArrayList.size() == Arrays.stream(resourcePathArrayList.toArray()).distinct().toArray().length;
        boolean aliasUnique = resourceAliasArrayList.size() == Arrays.stream(resourceAliasArrayList.toArray()).distinct().toArray().length;

        return pathsUnique && aliasUnique;
    }

    /**
     * Returns the content of the resource alias file
     *
     * @param fileName = The file containing the resource aliases
     *
     * @return HashMap<String, String>
     * @throws Exception = When linting of file fails
     */
    public HashMap<String, String> getContent(String fileName) throws Exception
    {
        if(!this.lint(fileName)) {
            throw new Exception("Linting of file '" + fileName + "' failed!");
        }

        String content = this.getFileContent(fileName);
        HashMap<String, String> convertedContent = new HashMap<>();

        for (String line: content.split(System.lineSeparator())) {

            // Remove all types of empty spaces from line
            String lineWithRemovedEmptySpaces = line.replaceAll("\\s", "");
            if(!lineWithRemovedEmptySpaces.equals("")) {
                String[] lineArr = line.split(KVFR_SEPERATION_REGEX);
                convertedContent.put(lineArr[0], lineArr[1]);
            }
        }

        return convertedContent;
    }
}
