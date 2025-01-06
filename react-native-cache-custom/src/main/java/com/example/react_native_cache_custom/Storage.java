package com.example.react_native_cache_custom;

public class Storage {

    private static Storage instance = null;

    private Storage(){

    }

    public static Storage getInstance(){
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    InternalStorageManager getInternalStorage() {return InternalStorageManager.getInstance();};
}