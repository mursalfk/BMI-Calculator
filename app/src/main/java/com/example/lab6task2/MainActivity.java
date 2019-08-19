package com.example.lab6task2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {
    EditText etHeightCms, etHeightFtFeet, etHeightFtInches, etWeightKgs, etWeightlbs;
    Switch swHeight, swWeight;
    TextView tvBMI, tvStatus;
    ConstraintLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etHeightCms = (EditText) findViewById(R.id.etHeightCms);
        etHeightFtFeet = (EditText) findViewById(R.id.etHeightFtFeet);
        etHeightFtInches = (EditText) findViewById(R.id.etHeightFtInches);
        etWeightKgs = (EditText) findViewById(R.id.etWeightkgs);
        etWeightlbs = (EditText) findViewById(R.id.etWeightlbs);
        tvBMI = (TextView) findViewById(R.id.tvBMI);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        swHeight = (Switch) findViewById(R.id.swHeight);
        swWeight = (Switch) findViewById(R.id.swWeight);
        main = (ConstraintLayout) findViewById(R.id.main);

        swWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (swWeight.isChecked()){
                    etWeightKgs.setVisibility(View.GONE);
                    etWeightlbs.setVisibility(View.VISIBLE);
                    swHeight.setChecked(true);
                }else{
                    etWeightKgs.setVisibility(View.VISIBLE);
                    etWeightlbs.setVisibility(View.GONE);
                    swHeight.setChecked(false);
                }
            }
        });
        swHeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (swHeight.isChecked()){
                    etHeightCms.setVisibility(View.GONE);
                    etHeightFtFeet.setVisibility(View.VISIBLE);
                    etHeightFtInches.setVisibility(View.VISIBLE);
                    swWeight.setChecked(true);
                } else{
                    etHeightCms.setVisibility(View.VISIBLE);
                    etHeightFtFeet.setVisibility(View.GONE);
                    etHeightFtInches.setVisibility(View.GONE);
                    swWeight.setChecked(false);
                }
            }
        });
    }
    public void calculateBMI(View v){
        String ftHeight = etHeightFtFeet.getText().toString();
        String inHeight = etHeightFtInches.getText().toString();
        String cmHeight = etHeightCms.getText().toString();
        String kgWeight = etWeightKgs.getText().toString();
        String lbsWeight = etWeightlbs.getText().toString();
        String heightStr = etHeightCms.getText().toString();
        String weightStr = etWeightKgs.getText().toString();
        float totalWeight;
        float totalHeight;
        Float BMI;
        DecimalFormat two = new DecimalFormat("0.00"); //Make new decimal format

        if (heightStr != null && !"".equals(heightStr) && weightStr != null  &&  !"".equals(weightStr)) {
            if (swWeight.isChecked() && swWeight.isChecked()){
                totalWeight = Float.parseFloat(lbsWeight);
                totalHeight = (Float.parseFloat(ftHeight )* 12) + Float.parseFloat(inHeight);
                BMI = (totalWeight / (totalHeight * totalHeight)) * 703;
                tvBMI.setText(two.format(BMI));
                changing(BMI);
            } else{
                totalWeight = Float.parseFloat(kgWeight);
                totalHeight = (Float.parseFloat(cmHeight )/ 100);
                BMI = totalWeight / (totalHeight * totalHeight);
                tvBMI.setText(two.format(BMI));
                changing(BMI);
            }
        }
    }
    public void changing(float value){
        if(value <= 18.5){
            tvStatus.setText("Underweight");
            main.setBackgroundColor(Color.YELLOW);
            tvStatus.setTextColor(Color.rgb(36,56,144));
            tvBMI.setTextColor(Color.rgb(36,56,144));
        } else if(value>18.5 && value <= 24.99){
            tvStatus.setText("Normal");
            main.setBackgroundColor(Color.GREEN);
            tvStatus.setTextColor(Color.rgb(36,56,144));
            tvBMI.setTextColor(Color.rgb(36,56,144));
        } else if(value>25 && value <= 29.99){
            tvStatus.setText("Overweight");
            main.setBackgroundColor(Color.BLUE);
        } else if(value>30){
            tvStatus.setText("Obese");
            main.setBackgroundColor(Color.RED);
        }
    }
}
