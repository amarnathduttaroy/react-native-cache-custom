package com.example.react_native_cache_custom;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class CacheModule extends ReactContextBaseJavaModule {
    Context context = null;
    CacheModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }

    private InternalStorageManager internalStorageManager = Storage.getInstance().getInternalStorage();

    @NonNull
    @Override
    public String getName() {
        return "CacheModule";
    }

    public String createCache() {
        try{
            String url = "https://dummyjson.com/c/3029-d29f-4014-9fb4";
            boolean isCreated = internalStorageManager.createCache(context, "json", url);
            if(isCreated) {
                //promise.resolve("File Created");
                //promise.resolve("Fetching data");
                return internalStorageManager.getCache(context, "json");
            }
            else {
                //promise.reject("File Not Created");
                return "File Not Created";
            }
        } catch (Exception e) {
            //promise.reject("Error returned from createCache", e);
            return e.getMessage();
        }
    }

    @ReactMethod
    public void getCache(Promise promise) {
        try{
            String url = "";
            String details = internalStorageManager.getCache(context, "json");
            if(details == null || details.isEmpty()) {
                promise.resolve("New cache: " + createCache());
            }
            else{
                promise.resolve("Using cache: " + details);
            }
            promise.resolve(details);
        } catch (Exception e) {
            promise.reject("Error returned from getCache", e);
        }
    }
}
