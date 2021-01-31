package com.example.neuralnetwork;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static int argMax(float[] inp){
        int maxInx = -1;
        float maxVal = 0.0f;

        for(int i = 0; i<inp.length; i++){
            if((inp[i]) > maxVal){
                maxVal = inp[i];
                maxInx = i;
            }
        }
        return maxInx;
    }

    public static String assetsFile(Context context, String AssetName){
        File f = new File(context.getFilesDir(), AssetName);

        try(InputStream is = context.getAssets().open(AssetName)){
            try(OutputStream os = new FileOutputStream(f)){
                byte[] buf = new byte[4096];
                int r;
                while ((r = is.read(buf)) != -1){
                    os.write(buf, 0, r);
                }
                os.flush();
            }
            return f.getAbsolutePath();
        }
        catch(IOException e) {
            Log.e("pytorchandroid","Error on path:" );
        }
        return null;
    }
}
