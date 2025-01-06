package com.example.react_native_cache_custom;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class InternalStorageManager {

    private static InternalStorageManager instance = null;

    private InternalStorageManager() {

    }

    public static InternalStorageManager getInstance(){
        if(instance == null) {
            instance = new InternalStorageManager();
        }
        return instance;
    }

    boolean createCache(Context context, String filename, String imageUrl) throws IOException {
        File file = null;
        URL url = new URL(imageUrl);
        BufferedReader bis = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
        try{
            file = File.createTempFile(filename, null, context.getCacheDir());
            FileOutputStream fos = new FileOutputStream(file);

            String inputLine;
            while ((inputLine = bis.readLine()) != null)
                fos.write(inputLine.getBytes());
            bis.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return file != null;
    }

    String getCache(Context context, String filename){

        File tempFolder = context.getCacheDir();
        String filenameExact = "";
        if(tempFolder.isDirectory()){
            File[] tempfiles = tempFolder.listFiles();
            for (File tempFile: tempfiles) {
                if (tempFile.getName().contains(filename)){
                    filenameExact = tempFile.getName();
                    break;
                }
            }
        }
        File myFile = new File(context.getCacheDir(), filenameExact);

        String aBuffer = "";
        try {
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aBuffer;
    }

}
