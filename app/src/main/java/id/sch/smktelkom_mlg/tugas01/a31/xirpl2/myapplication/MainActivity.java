package id.sch.smktelkom_mlg.tugas01.a31.xirpl2.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama;
    Button bDaftar;
    TextView tvHasil, tvHasil2, tvEkstra, tvHasil3, tvHasil4;
    RadioButton rbLK, rbPR;
    CheckBox cb1, cb2, cb3, cb4;
    int nEkstra;
    Spinner spKelas, spJurusan;
    String[][] arJurusan = {{"RPL 1", "RPL 2", "RPL 3", "TKJ 1", "TKJ 2", "TKJ 3"},
            {"RPL 1", "RPL 2", "RPL 3", "TKJ 1", "TKJ 2"},
            {"RPL 1", "RPL 2", "TKJ 1", "TKJ 2"}};
    ArrayList<String> listJurusan = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        bDaftar = (Button) findViewById(R.id.buttonDaftar);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvHasil2 = (TextView) findViewById(R.id.textViewHasil2);
        tvHasil3 = (TextView) findViewById(R.id.textViewHasil3);
        tvHasil4 = (TextView) findViewById(R.id.textViewHasil4);
        rbLK = (RadioButton) findViewById(R.id.radioButtonLk);
        rbPR = (RadioButton) findViewById(R.id.radioButtonPr);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        tvEkstra = (TextView) findViewById(R.id.textViewEkstra);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        spJurusan = (Spinner) findViewById(R.id.spinnerJurusan);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listJurusan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(adapter);

        spKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listJurusan.clear();
                listJurusan.addAll(Arrays.asList(arJurusan[pos]));
                adapter.notifyDataSetChanged();
                spJurusan.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        bDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
            }
        });
    }

    private void doProcess() {

        if (isValid()) {
            String nama = etNama.getText().toString();
            tvHasil.setText("Atas nama " + nama);
        }

        String gender = null;

        if (rbLK.isChecked()) {
            gender = rbLK.getText().toString();
        } else if (rbPR.isChecked()) {
            gender = rbPR.getText().toString();
        }

        if (gender == null) {
            tvHasil2.setText("Anda belum memilih Gender");
        } else {
            tvHasil2.setText("Gender Anda :" + gender);
        }

        String ekstra = "Anda Memilih Kelas Tambahan :\n";
        int startlen = ekstra.length();
        if (cb1.isChecked()) ekstra += cb1.getText() + " \n";
        if (cb2.isChecked()) ekstra += cb2.getText() + " \n";
        if (cb3.isChecked()) ekstra += cb3.getText() + " \n";
        if (cb4.isChecked()) ekstra += cb4.getText() + " \n";

        if (ekstra.length() == startlen) ekstra += "Anda Belum memilih Kelas Pada Visionet!";

        tvHasil3.setText(ekstra);

        tvHasil4.setText("Kelas " + spKelas.getSelectedItem().toString()
                + "  " + spJurusan.getSelectedItem().toString());
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundbutton, boolean isChecked) {
        if (isChecked) nEkstra += 1;
        else nEkstra -= 1;

        tvEkstra.setText("Kelas yang diambil (" + nEkstra + "adalah)");
    }
}
