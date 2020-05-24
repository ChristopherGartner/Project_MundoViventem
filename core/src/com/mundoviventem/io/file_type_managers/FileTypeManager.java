package com.mundoviventem.io.file_type_managers;

import com.mundoviventem.game.Main;
import com.mundoviventem.io.FileManager;

import java.util.ArrayList;

/**
 * Base class for file type managers
 */
public abstract class FileTypeManager
{

    public abstract String getFileType();

    /**
     * Returns the content of the given file.
     *
     * @param fileName = Path (from Project Path ongoing) till the name (without file type)
     *
     * @return String
     */
    protected String getFileContent(String fileName)
    {
        return FileManager.getContent(Main.Project_Path + "\\" + fileName + "." + this.getFileType());
    }

    public void createFile(ArrayList<String> content, String fileName)
    {
        FileManager.createFile(content, fileName + "." + this.getFileType());
    }

    /**
     * Method to evaluate correct file structure
     *
     * @return boolean
     */
    protected abstract boolean lint(String fileName);
}
