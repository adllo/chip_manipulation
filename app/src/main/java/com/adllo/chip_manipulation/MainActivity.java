package com.adllo.chip_manipulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.adllo.chip.ChipManipulation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ChipManipulation chip = findViewById(R.id.layoutChip);
        ArrayList<String> arrChip = new ArrayList<>();
        arrChip.add("string1");
        arrChip.add("String2");
        arrChip.add("string3");

//        function set value to set value to chip, accept string or array
        chip.setValue(arrChip);
        chip.setValue("example");

//        function to get value from existing chips in array. ex: [#string1, #String2, #string3]
        Log.d("demo", "onCreate: "+chip.getValue());
    }
}
