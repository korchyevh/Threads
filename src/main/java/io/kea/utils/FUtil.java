package io.kea.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FUtil implements Callable {
    private volatile List<String> foldersList;
    private String filepath;

    public FUtil(String path) {
        Thread.currentThread().setName(path);
    }

    public static List<String> getRootFolderList(String filepath){
        List<String> foldersList = new ArrayList<>();
        try {
            Files.list(new File(filepath).toPath())
                    .forEach(path -> {
                        foldersList.add(path.toString());
                        System.out.println(path);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return foldersList;
    }

    public void run(){
        this.foldersList = new ArrayList<>();
        try {
            Files.list(new File(this.filepath).toPath())
                    .forEach(path -> {
                        this.foldersList.add(path.toString());
                        System.out.println("Find path: " + path + " - " + Thread.currentThread().getName());
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public List<String> getFoldersList(){
        System.out.println("\n:" + this.foldersList.size());
        return this.foldersList;
    }

    @Override
    public List<String> call() throws Exception {
        this.foldersList = new ArrayList<>();
        try {
            Files.list(new File(this.filepath).toPath())
                    .forEach(path -> {
                        this.foldersList.add(path.toString());
                        System.out.println("Find path: " + path + " - " + Thread.currentThread().getName());
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this.foldersList;
    }
}
