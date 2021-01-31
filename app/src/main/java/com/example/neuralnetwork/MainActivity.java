package com.example.neuralnetwork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v_click){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);
    }

    public void onProcessClick(View v_click){
        ImageView iv = (ImageView)findViewById(R.id.owoc);
        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
        Bitmap image = drawable.getBitmap();
        Bitmap bMapScaled = Bitmap.createScaledBitmap(image, 100, 100, true);
        String res = neuroclassifier(bMapScaled);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, res, duration);
        toast.show();
    }

    public String neuroclassifier(Bitmap x){
        Tensor inpTens = TensorImageUtils.bitmapToFloat32Tensor(x,TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_MEAN_RGB);
        Module model;
        model = Module.load(Utils.assetsFile(this, "nowymodelessa.pt"));
        IValue input = IValue.from(inpTens);
        Tensor output = model.forward(input).toTensor();
        float[] scores = output.getDataAsFloatArray();
        int inx = Utils.argMax(scores);
        return Constants.FRUITS360[inx];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode ==RESULT_OK){
            Intent res = new Intent(this, SecondActivity.class);
            res.putExtra("data_raw", data.getExtras());
            startActivity(res);
        }
    }
}
