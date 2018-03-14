package me.andrescampo.abc;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private int[][][] prices = new int[2][2][3];

    private Spinner spinnerMaterial;
    private Spinner spinnerDije;
    private Spinner spinnerType;
    private Spinner spinnerMoney;
    private EditText editTextQuantity;
    private TextView textViewResult;
    private Resources resources;

    private void calculateQuantity(int selectedMaterial, int selectedDije, int selectedType, int selectedMoney, int quantity) {
        String prefix;
        int result = prices[selectedMaterial][selectedDije][selectedType] * quantity;
        if (selectedMoney == 0) {
            prefix = "COP $";
            result = result * 3200;
        } else {
            prefix = "USD $";
        }
        Log.d("Result", "calculateQuantity: " + result);
        textViewResult.setText(prefix + String.valueOf(result));
    }

    private void getValuesAndCalculate() {
        String _quantity = editTextQuantity.getText().toString();
        int quantity = _quantity.length() == 0 ? 0 : Integer.parseInt(_quantity);
        if (quantity == 0) {
            editTextQuantity.setError(resources.getString(R.string.not_empty));
        }
        calculateQuantity(spinnerMaterial.getSelectedItemPosition(), spinnerDije.getSelectedItemPosition(), spinnerType.getSelectedItemPosition(), spinnerMoney.getSelectedItemPosition(), quantity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prices[0][0][0] = 100;
        prices[0][0][1] = 80;
        prices[0][0][2] = 70;

        prices[0][1][0] = 120;
        prices[0][1][1] = 100;
        prices[0][1][2] = 90;

        prices[1][0][0] = 90;
        prices[1][0][1] = 70;
        prices[1][0][2] = 50;

        prices[1][1][0] = 110;
        prices[1][1][1] = 90;
        prices[1][1][2] = 80;

        spinnerMaterial = (Spinner) findViewById(R.id.spinnerMaterial);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerDije = (Spinner) findViewById(R.id.spinnerDije);
        spinnerMoney = (Spinner) findViewById(R.id.spinnerMoney);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);
        editTextQuantity.setText("0");

        resources = this.getResources();

        editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);
        editTextQuantity.setText("0");
        editTextQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getValuesAndCalculate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        String[] materials = resources.getStringArray(R.array.materials);
        ArrayAdapter<String> gendersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, materials);
        spinnerMaterial.setAdapter(gendersAdapter);
        spinnerMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getValuesAndCalculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] types = resources.getStringArray(R.array.types);
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        spinnerType.setAdapter(typesAdapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getValuesAndCalculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] dijes = resources.getStringArray(R.array.dijes);
        ArrayAdapter<String> dijesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dijes);
        spinnerDije.setAdapter(dijesAdapter);
        spinnerDije.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getValuesAndCalculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] moneys = resources.getStringArray(R.array.moneys);
        ArrayAdapter<String> moneysAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, moneys);
        spinnerMoney.setAdapter(moneysAdapter);
        spinnerMoney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getValuesAndCalculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
