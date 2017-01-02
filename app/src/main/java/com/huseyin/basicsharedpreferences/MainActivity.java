package com.huseyin.basicsharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


  public class MainActivity extends AppCompatActivity {

    private static final String PREF_AUTO_UPDATE="PREF_AUTO_UPDATE";
    private static final String PREF_CURRENCY="PREF_CURRENCY";
    private static final String PREF_PERCENT_EXCITATION="PREF_PERCENT_EXCITATION";


    CheckBox autoUpdateCheck;
    Spinner spinnerCurreny;
    EditText percentExcitationEditText;
    Button buttonSave;
    SharedPreferences preferences;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          //Define components in activity_main.xml
          autoUpdateCheck = (CheckBox) findViewById(R.id.autoUpdateCheckBox);
          spinnerCurreny= (Spinner) findViewById(R.id.currenySpinner);
          percentExcitationEditText= (EditText) findViewById(R.id.percentExcitationtEditText);
          buttonSave= (Button) findViewById(R.id.button_save);

          //if you need just one shared preference file for your activity, you can use the getPreferences() method
          preferences=getPreferences(MODE_PRIVATE);

        //Define a string array and send it to spinner
        String[] arrayCurreny=getResources().getStringArray(R.array.curreny);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayCurreny);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurreny.setAdapter(adapter);






        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              savePreferences();

            }
        });
            readPreferences();
    }



      private void savePreferences() {
          SharedPreferences.Editor mEditor=preferences.edit();
          mEditor.putBoolean(PREF_AUTO_UPDATE, autoUpdateCheck.isChecked());
          mEditor.putInt(PREF_CURRENCY, spinnerCurreny.getSelectedItemPosition());

          Editable percentExcitation=percentExcitationEditText.getText();
          mEditor.putFloat(PREF_PERCENT_EXCITATION, Float.valueOf(percentExcitation.toString()));
          mEditor.apply();

      }

    private void readPreferences() {

        boolean autoUpdate = preferences.getBoolean(PREF_AUTO_UPDATE, false);
        autoUpdateCheck.setChecked(autoUpdate);

        int currency=preferences.getInt(PREF_CURRENCY,0);
        spinnerCurreny.setSelection(currency);

        float percentExcitation=preferences.getFloat(PREF_PERCENT_EXCITATION,5.0f);
        percentExcitationEditText.setText(String.valueOf(percentExcitation));


    }




}
