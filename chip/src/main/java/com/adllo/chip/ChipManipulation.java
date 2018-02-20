package com.adllo.chip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev_three_adllo on 2/20/18.
 */

public class ChipManipulation extends LinearLayout {
    private Context mContext;
    private EditText mEditText;
    private TextView mTextView;
    private TextView mTextViewX;
    private FlexboxLayout flexLayout;
    private int indexCount = 0;
    private String chipBackgroundColor;
    private String chipTextColor;
    private String chipClearSymbolColor;
    private String chipHint;

    public ChipManipulation(Context context) {
        super(context);
    }

    public ChipManipulation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ChipManipulation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ChipManipulation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mContext = context;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.flexbox_template, this, true);

        flexLayout = (FlexboxLayout) linearLayout.findViewById(R.id.divFlex);
        mEditText = (EditText) linearLayout.findViewById(R.id.editText);
//        mEditText.setOnKeyListener(onKeyPress());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequence.toString();

                if (charSequence.length() > 0)
                {
                    String keyCode = (String) charSequence.toString().substring(charSequence.length() - 1);

                    if (keyCode.equals(" "))
                    {
                        String newString = mEditText.getText().toString().trim();

                        if (newString != null && !newString.isEmpty())
                        {
                            flexLayout.addView(createNewTextView(newString), indexCount++);
                            mEditText.setText("");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.chip_drawable);
        chipBackgroundColor = a.getString(R.styleable.chip_drawable_background_color);
        chipTextColor = a.getString(R.styleable.chip_drawable_text_color);
        chipClearSymbolColor = a.getString(R.styleable.chip_drawable_clear_symbol_color);
        chipHint = a.getString(R.styleable.chip_drawable_hint);

        if (chipHint != null)
        {
            mEditText.setHint(chipHint);
        }
    }

//    private View.OnKeyListener onKeyPress() {
//        return new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                switch (keyCode) {
//                    case KeyEvent.KEYCODE_SPACE:
//                        String newString = mEditText.getText().toString().trim();
//
//                        if (newString != null && !newString.isEmpty())
//                        {
//                            flexLayout.addView(createNewTextView(newString), indexCount++);
//                            mEditText.setText("");
//                        }
//                }
//
//                return true;
//            }
//        };
//    }

    private void setBackgroundColor(String backgroundColor)
    {
        Resources res = getResources();
        GradientDrawable drawable = (GradientDrawable) res.getDrawable(R.drawable.shape_chip_drawable);
        drawable.setColor(Color.parseColor(backgroundColor));
    }

    private void setTextColor(String textColor)
    {
        mTextView.setTextColor(Color.parseColor(textColor));
    }

    private void setChipClearSymbolColor(String clearSymbolColor)
    {
        mTextViewX.setTextColor(Color.parseColor(clearSymbolColor));
    }

    public ArrayList<String> getValue()
    {
        ArrayList<String> list = new ArrayList<>();

        for( int i = 0; i < (flexLayout.getChildCount() - 1); i++ ){
            LinearLayout temp = (LinearLayout) flexLayout.getChildAt(i);
            RelativeLayout temp2 = (RelativeLayout) temp.getChildAt(0);

            TextView tempText = (TextView) temp2.getChildAt(0);

            if (tempText.isShown())
            {
                list.add(tempText.getText().toString());
            }
        }

        return list;
    }

    public void setValue(List<String> arrTemp)
    {
        for(int i = 0; i < (arrTemp.size()); i++ )
        {

            setValue(arrTemp.get(i));
        }
    }

    public void setValue(String data)
    {
        flexLayout.addView(createNewTextView(data), indexCount++);
    }

    @SuppressLint("ResourceAsColor")
    private LinearLayout createNewTextView(String text) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.text_template, null);
        final RelativeLayout relativeLayout = (RelativeLayout) linearLayout.findViewById(R.id.relative1);

        mTextView = (TextView) relativeLayout.findViewById(R.id.textview1);
        mTextViewX = (TextView) relativeLayout.findViewById(R.id.textViewX);
        mTextView.setText("#" + text);

        if (chipBackgroundColor != null)
        {
            setBackgroundColor(chipBackgroundColor);
        }

        if (chipTextColor != null)
        {
            setTextColor(chipTextColor);
        }

        if (chipClearSymbolColor != null)
        {
            setChipClearSymbolColor(chipClearSymbolColor);
        }

        relativeLayout.setBackgroundResource(R.drawable.shape_chip_drawable);

        mTextViewX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            relativeLayout.setVisibility(View.GONE);
            }
        });

        return linearLayout;
    }
}
