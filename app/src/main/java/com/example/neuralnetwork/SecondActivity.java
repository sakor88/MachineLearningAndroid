package com.example.neuralnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;


public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent photo = getIntent();
        Bundle data = photo.getBundleExtra("data_raw");
        Bitmap image = (Bitmap)data.get("data");
        Bitmap bMapScaled = Bitmap.createScaledBitmap(image, 100, 100, false);
        ImageView ivi = (ImageView)findViewById(R.id.imageView);
        ivi.setImageBitmap(bMapScaled);
        String res = neuroclassifier(bMapScaled);
        TextView tx = (TextView)findViewById(R.id.textView);
        tx.setText(res);
    }

    public String neuroclassifier(Bitmap x){
        Tensor inpTens = TensorImageUtils.bitmapToFloat32Tensor(x,TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_MEAN_RGB);
        Module model;
        model = Module.load(Utils.assetsFile(this, "nowymodelandroid.pt"));
        IValue input = IValue.from(inpTens);
        Tensor output = model.forward(input).toTensor();
        float[] scores = output.getDataAsFloatArray();
        int inx = Utils.argMax(scores);
        return Constants.FRUITS360[inx];
    }
}