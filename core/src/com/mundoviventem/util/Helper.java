package com.mundoviventem.util;

import java.io.File;

public class Helper {

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /**
     * Returns the given Files name without the file extension, e.g. shader.xml.OLD -> shader.xml
     * @param file
     * @return
     */
    public static String nameWithoutExtension(File file){
        return file.getName().replaceFirst("[.][^.]+$", "");
    }
}
