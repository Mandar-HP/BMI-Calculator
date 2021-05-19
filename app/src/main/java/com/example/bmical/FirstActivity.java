package com.example.bmical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private ImageView boyimage,girlimage;


    private int[] imgsrc = { R.drawable.boy_logo, R.drawable.girl_logo };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        init();


    }

    public void init(){
        boyimage = findViewById(R.id.boyimgbtn);
        girlimage = findViewById(R.id.girlimgbtn);

        // APPLYING LISTENERS :
        boyimage.setOnClickListener(new imagelistener());
        girlimage.setOnClickListener(new imagelistener());


    }




    private class imagelistener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v == boyimage){
                Intent boyimgsrc = new Intent(FirstActivity.this,Second_Activity.class);
                boyimgsrc.putExtra("image",imgsrc[0]);

                //Toastpopup("You have selected boy");
                startActivity(boyimgsrc);
            }
            else if (v == girlimage){
                Intent girlimgsrc = new Intent(FirstActivity.this,Second_Activity.class);
                girlimgsrc.putExtra("image",imgsrc[1]);

                startActivity(girlimgsrc);
            }

        }
    }

    public void Toastpopup(String x){
        Toast.makeText(FirstActivity.this,x,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        Toastpopup("Thank You for using Mandar's BMI Calculator");
        super.onDestroy();
    }
}
