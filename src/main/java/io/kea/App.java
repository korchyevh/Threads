package io.kea;

import io.kea.utils.FUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Hello world");
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
        List <String> folderListForSearching = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        String filepath = "/media/veracrypt1/";
        folderListForSearching = FUtil.getRootFolderList(filepath);
        ExecutorService threadPool = (ExecutorService) Executors.newFixedThreadPool(5);
        for (String path: folderListForSearching){
            FUtil thread = new FUtil(path);
            thread.setFilepath(path);
            Future futureTask = threadPool.submit(thread);
            resultList.addAll((ArrayList <String>) futureTask.get());

        }
        threadPool.shutdown();


        for (String path : resultList){
            System.out.println(path);
        }

        System.out.println(resultList.size());

    }
}
