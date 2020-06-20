package com.mundoviventem.render;

import java.io.File;

public class ShaderHandler {

    public void readShaders(String path){
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if(child.isDirectory()) readShaders(child.getAbsolutePath());
                System.out.println(child.getAbsolutePath());
            }
        }
    }
}
