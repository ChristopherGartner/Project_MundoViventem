package com.mundoviventem.io.file_type_managers;

import com.mundoviventem.io.FileManager;
import com.mundoviventem.io.FileTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class for content managing of ".kvfb" files
 */
public class KeyValueFileManager extends FileTypeManager
{

    public static final int BYTE_MAX_SIZE       = 255;
    public static final String SEPERATION_REGEX = "===";

    @Override
    public String getFileType()
    {
        return FileTypes.KEY_VALUE_Byte;
    }

    @Override
    protected boolean lint(String fileName) {
        String content = this.getFileContent(fileName);

        ArrayList<Integer> idArrayList = new ArrayList<>();

        for (String line: content.split(System.lineSeparator())) {
            String[] lineArr = line.split(SEPERATION_REGEX);
            // Validate that structure only consists of one key and one value
            if(lineArr.length != 2) {
                return false;
            }
            idArrayList.add(Integer.parseInt(lineArr[0]));
        }

        // validate that given keys are unique
        return idArrayList.size() == Arrays.stream(idArrayList.toArray()).distinct().toArray().length;
    }

    /**
     * Returns the Content of the key value pairs in form of a multi dimensional HashMap byte string structure.
     * Foreach surpassing of 255 ids, a new Hashmap instance gets created
     *
     * @param fileName = the name of the file
     *
     * @return HashMap<Byte, String>[]
     */
    public ArrayList<HashMap<Byte, String>> getContent(String fileName)
    {
        this.lint(fileName);
        int byteCounter = 0;

        String content                               = this.getFileContent(fileName);
        HashMap<Byte, String> contentMap             = new HashMap<>();
        ArrayList<HashMap<Byte, String>> contentMaps = new ArrayList<>();

        for(String line: content.split(System.lineSeparator())) {
            String[] lineArr = line.split(SEPERATION_REGEX);

            if(!(byteCounter < BYTE_MAX_SIZE)) {
                   contentMaps.add(contentMap);
                   contentMap  = new HashMap<>();
                   byteCounter = 0;
            }

            contentMap.put((byte) byteCounter, lineArr[1]);
            byteCounter++;
        }

        return contentMaps;
    }

}
