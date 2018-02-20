# chip_manipulation
Manipulation function for chip

## xml example
```
<com.adllo.chip.ChipManipulation
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:chip_attribute="http://schemas.android.com/apk/res-auto"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/layoutChip"
    chip_attribute:background_color="#F2A81A"
    chip_attribute:text_color="#6B6861"
    chip_attribute:clear_symbol_color="#000000"
    chip_attribute:hint="input chip here!!!"
/>
```

## attribute
```
background_color : change chip color. only accepts hex value with #
text_color : change text color inside chip. only accepts hex value with #
clear_symbol_color : change clear_symbol_color inside chip. only accepts hex value with #
hint : set hint text. default: input chip here!
```

## method
##### setValue

method to set value into chip, accepts string or array list

ex:
```
chip.setValue(exampleArray);
chip.setValue("exampleString");
```

##### getValue

method to get value from chip, return to array of string

ex:
```
chip.getValue();
```
return `[#example1, #example2, #example3]`