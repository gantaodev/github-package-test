package com.zd.core.resource;

import com.zd.core.util.FileResourceLocationHelper;

import java.io.IOException;

public class FileResourceLocationTest {

    public static void main(String[] args) throws IOException {
        System.setProperty("pro.config.dir", "file");
        FileResource fileResource = FileResourceLocationHelper.getFileResource("application.properties");
        System.out.println(fileResource.getPath());
    }
}
