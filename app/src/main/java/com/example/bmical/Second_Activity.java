package com.example.bmical;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Second_Activity extends AppCompatActivity {


    private ImageView Act2imgview;
    private TextView heightresTxt,weightresTxt;
    private Button calculatebtn;

    private int Act1imgid;
    private int HeightStrngfromNumberpick;
    public double Result;
    private String obesestrng;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        init();
        NumerpickInitiate();

    }

    private void init(){

        //ATTACHING REFERENCES :
        Act2imgview = findViewById(R.id.getimg_from_act_01);
        heightresTxt = findViewById(R.id.textViewofheightresult);
        weightresTxt = findViewById(R.id.textViewWeightresult);
        calculatebtn = findViewById(R.id.calculatebtn);


        //CREATING INETENTS :
        Intent getfromacti1 = getIntent();
        Bundle getidbundle = getfromacti1.getExtras();

        Act1imgid = getidbundle.getInt("image");

        //ASSOCIATING USER SELECT ID TO IMAGE VIEW:
        Act2imgview.setImageResource(Act1imgid);







        // ADDING LISTENERS :
        calculatebtn.setOnClickListener(new CalculateListener());


    }


    public  void NumerpickInitiate(){

        // ADDING NUMBERPICKERS :

        //Height :
        NumberPicker heightNoPicker = findViewById(R.id.HeightNoPickr);

        heightNoPicker.setMinValue(0);
        heightNoPicker.setMaxValue(41);

        heightNoPicker.setDisplayedValues(new String[]{"3","3.01","3.02","3.03","3.04","3.05","3.06","3.07","3.08","3.09","3.10","3.11","4","4.01","4.02","4.03","4.04","4.05","4.06","4.07","4.08","4.09","4.10","4.11","5","5.01","5.02","5.03","5.04","5.05","5.06","5.07","5.08","5.09","5.10","5.11","6","6.01","6.02","6.03","6.04","6.05"});
        heightNoPicker.setOnValueChangedListener(new HeightValChangeListener());



        //Weight :
        NumberPicker weightpicker = findViewById(R.id.WeightPickr);

        weightpicker.setMinValue(10);
        weightpicker.setMaxValue(120);

        weightpicker.setOnValueChangedListener(new WeightValChangeListener());
    }


    public class HeightValChangeListener implements NumberPicker.OnValueChangeListener{

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            String heightselectedval = picker.getDisplayedValues()[picker.getValue()];
            heightresTxt.setText(heightselectedval);
            //HeightStrngfromNumberpick = newVal;
        }
    }

    public class WeightValChangeListener implements NumberPicker.OnValueChangeListener{

        @Override
        public void onValueChange(NumberPicker picker1, int oldVal, int newVal) {
            String weightselectedval = String.valueOf(picker1.getValue());
            weightresTxt.setText(weightselectedval);
            //HeightStrngfromNumberpick = newVal;
        }
    }




    // ADDING GETTER SETTERS FOR ACCESING OTHER CLASS VALUES :

    public void setWeightresTxt(TextView weightresTxt) {
        this.weightresTxt = weightresTxt;
    }

    public void setHeightresTxt(TextView heightresTxt) {
        this.heightresTxt = heightresTxt;
    }





    public class CalculateListener implements View.OnClickListener{

        //WeightValChangeListener weight = new WeightValChangeListener();
        //HeightValChangeListener height = new HeightValChangeListener();



        @Override
        public void onClick(View v) {


            // TO AVOID NULL INPUT PROCESSING :

            if (heightresTxt.getText().toString() == ""){
                Toastpop("Please provide valid details !");
            }
            else if (weightresTxt.getText().toString() == ""){
                Toastpop("Please provide valid details !");
            }


            // ACTUAL PROCESSING :


            else {

                double height,weight,meterheight,result;

                height = Double.parseDouble(heightresTxt.getText().toString());
                weight = Double.parseDouble(weightresTxt.getText().toString());


                meterheight = height*0.3048;

                result = (weight/(meterheight*meterheight));

                Result = result;

                if (Result>25){
                    obesestrng = "You Are Overweight";
                }
                else if (Result<18.5){
                    obesestrng = "You Are Underweight";
                }
                else {
                    obesestrng = "Your weight is normal";
                }


                Alertdialogcaller();
            }


        }
    }

    public void Alertdialogcaller(){
        AlertDialog.Builder resultalert = new AlertDialog.Builder(Second_Activity.this);

        resultalert.setTitle("Your BMI is :"+" "+ (int) Result );
        resultalert.setMessage(obesestrng);
        resultalert.setNeutralButton("OK",dialoglistener);
        resultalert.show();
        resultalert.setCancelable(true);

    }

    DialogInterface.OnClickListener dialoglistener = new dialogBtnListener();

    private class dialogBtnListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_NEUTRAL){
                Snackbar act2snackbar = Snackbar.make(calculatebtn,"Exercise is key to good Health !",BaseTransientBottomBar.LENGTH_LONG);
                act2snackbar.show();

            }
        }
    }

    public void Toastpop(String x){
        Toast.makeText(Second_Activity.this,x,Toast.LENGTH_LONG).show();
    }

}
