package com.example.wordapp;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText Txtwords;
    TextView Txtsize;
    Button btnLogout;
    Button btninc;
    Button btndec;
    CheckBox cbxbold;
    CheckBox cbxunderline;
    Spinner Scolor;
    Spinner Sfont;
    RadioButton RBLTR;
    RadioButton RBRTL;
    EditText Txtfilename;
    Button btnnew;
    Button btnsave;
    Button btngetfile;
    Typeface tf = Typeface.DEFAULT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupVariable();
        fillColors();
        fillFonts();
        setBtnListner(btndec, '-');
        setBtnListner(btninc, '+');


       /*
       cbxbold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               SetBold();
           }
       });
       cbxunderline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               SetUnderline();
           }
       });
       */


        cbxunderline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTextStyle("Underline");
            }
        });

        cbxbold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setTextStyle("Bold");
            }
        });
        Scolor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setColor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Sfont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setFontType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        RBLTR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlignment();
            }
        });

        btnnew.setOnClickListener(v -> newFile());
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });
        btngetfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });

    }


    private void setupVariable() {
        Txtwords = findViewById(R.id.Txtwords);
        Txtsize = findViewById(R.id.Txtsize);
        btnLogout = findViewById(R.id.btnLogout);
        btninc = findViewById(R.id.btninc);
        btndec = findViewById(R.id.btndec);
        cbxbold = findViewById(R.id.cbxbold);
        cbxunderline = findViewById(R.id.cbxunderline);
        Scolor = findViewById(R.id.Scolor);
        Sfont = findViewById(R.id.Sfont);
        RBLTR = findViewById(R.id.RBLTR);
        RBRTL = findViewById(R.id.RBRTL);
        Txtfilename = findViewById(R.id.Txtfilename);
        btnnew = findViewById(R.id.btnnew);
        btnsave = findViewById(R.id.btnsave);
        btngetfile = findViewById(R.id.btngetfile);
    }

    protected void fillColors() {
        String[] strcolors = {

                "Black",
                "Red",
                "Blue",
                "Green",
                "Yellow",
                "Orange",
                "Pink",
                "Navy",
                "Gray",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strcolors);
        Scolor.setAdapter(adapter);
    }

    protected void fillFonts() {
        String[] strfonts = {

                "DEFAULT",
                "Miss Clara [Regular] Font by 7NTypes",
                "Lova Bear Font By Dreamink",
                "Marigolds Font By Dreamink",
                "Netherland Cracker - Personal Use"

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strfonts);
        Sfont.setAdapter(adapter);


    }

    protected void setTxtSize(char incORdec) {
        int size = Integer.parseInt(Txtsize.getText().toString());

        if (incORdec == '-') size--;
        else if (incORdec == '+') size++;

        if (size > 99) size = 99;
        if (size < 11) size = 11;
        Txtwords.setTextSize(size);
        Txtsize.setText(size + "");


    }

    private void setBtnListner(Button btndec, final char c) {
        btndec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTxtSize(c);
            }
        });
    }


    /* protected void IncFontSize(){
           int size = Integer.parseInt(  Txtsize.getText().toString());
           size ++;
           Txtwords.setTextSize(size);

           Txtsize.setText(size + "");


     }

     */


    /*
    protected  void SetBold(){
        if (cbxbold.isChecked()){
            Txtwords.setTypeface(null, Typeface.BOLD);
            }
            else Txtwords.setTypeface(null,Typeface.NORMAL);

    }
    protected void SetUnderline(){
        if (cbxunderline.isChecked()) {
            Txtwords.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        }
            else Txtwords.setPaintFlags(Paint.LINEAR_TEXT_FLAG);

    }

     */
    protected void setTextStyle(String Style) {
        if (Style.equals("Bold")) {
            if (cbxbold.isChecked()) Txtwords.setTypeface(tf, Typeface.BOLD);
            else Txtwords.setTypeface(tf, Typeface.NORMAL);

        } else if (Style == "Underline") {
            if (cbxunderline.isChecked()) Txtwords.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            else Txtwords.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
        }

    }

    protected void setColor() {

        //String strcolors=Scolor.getSelectedItem().toString();
        switch (Scolor.getSelectedItem().toString()) {

            case "Black":
                Txtwords.setTextColor(getResources().getColor(R.color.Black));
                break;
            case "Red":
                Txtwords.setTextColor(getResources().getColor(R.color.Red));
                break;
            case "Blue":
                Txtwords.setTextColor(getResources().getColor(R.color.Blue));
                break;
            case "Green":
                Txtwords.setTextColor(getResources().getColor(R.color.Green));
                break;
            case "Yellow":
                Txtwords.setTextColor(getResources().getColor(R.color.Yellow));
                break;
            case "Orange":
                Txtwords.setTextColor(getResources().getColor(R.color.Orange));
                break;
            case "Pink":
                Txtwords.setTextColor(getResources().getColor(R.color.Pink));
                break;
            case "Navy":
                Txtwords.setTextColor(getResources().getColor(R.color.Navy));
                break;
            case "Gray":
                Txtwords.setTextColor(getResources().getColor(R.color.Gray));
                break;
        }

    }

    protected void setFontType() {

        switch (Sfont.getSelectedItem().toString()) {
            case "DEFAULT":
                tf = Typeface.DEFAULT;
                break;
            case "Miss Clara [Regular] Font by 7NTypes":
                tf = Typeface.createFromAsset(getAssets(), "miss clara [regular] font by 7ntypes.otf");
                break;

            case "Lova Bear Font By Dreamink":
                tf = Typeface.createFromAsset(getAssets(), "lova bear font by dreamink.otf");
                break;
            case "Marigolds Font By Dreamink":
                tf = Typeface.createFromAsset(getAssets(), "marigolds font by dreamink.otf");
                break;
            case "Netherland Cracker - Personal Use":
                tf = Typeface.createFromAsset(getAssets(), "netherland cracker - personal use.ttf");
                break;


        }
        if (cbxbold.isChecked()) {
            Txtwords.setTypeface(tf, Typeface.BOLD);
        } else Txtwords.setTypeface(tf);


    }

    protected void setAlignment() {
        if (RBLTR.isChecked()) Txtwords.setGravity(Gravity.LEFT);
        else Txtwords.setGravity(Gravity.RIGHT);


    }

    protected void newFile() {
        Txtwords.setText("");
        Txtsize.setText("14");
        Txtwords.setTextSize(14);
        cbxbold.setChecked(false);
        cbxunderline.setChecked(false);
        Scolor.setSelection(0);
        Sfont.setSelection(0);
        RBLTR.setChecked(true);
        Txtfilename.setText("");
        Txtwords.requestFocus();
    }

    protected void saveFile() {

        try {

            if ((Txtfilename.getText().toString().trim()).equals("")) {
                //Toast.makeText(this, "File Name Is Empty!! ", Toast.LENGTH_LONG).show();
                Txtfilename.requestFocus();

                throw new Exception("File Name Is Empty!! ");

            }

            String strpath = getExternalFilesDir(null) + "/MyWords";
            File f = new File(strpath);
            f.mkdir();
            File f1 = new File(strpath + "/" + Txtfilename.getText().toString() + ".txt");
            if (f1.exists()) {
                //   Toast.makeText(this, "this file allredy exists", Toast.LENGTH_SHORT).show();

                throw new Exception("This File Name Already Exists!! ");
            }
            PrintWriter pw = new PrintWriter(strpath + "/" + Txtfilename.getText().toString() + ".txt");
            pw.write(Txtwords.getText().toString());

            pw.close();

            PrintWriter pwSet = new PrintWriter(strpath + "/" + Txtfilename.getText() + "Set.txt");
            String SetText =
                    Txtsize.getText() + "\n" +
                            cbxbold.isChecked() + "\n" +
                            cbxunderline.isChecked() + "\n" +
                            Scolor.getSelectedItem() + "\n" +
                            Sfont.getSelectedItem() + "\n" +
                            RBLTR.isChecked() + "\n" +
                            RBRTL.isChecked() + "\n";
            pwSet.write(SetText);

            pwSet.close();

            Toast.makeText(this, "File Saved successfully", Toast.LENGTH_SHORT).show();

            newFile();

        } catch (Exception e) {
            try {
                e.printStackTrace(new PrintWriter("error.log"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    protected void getFile() {

        try {
            if ("".equals(Txtfilename.getText().toString().trim())) {
                Txtwords.setText("");
                Txtfilename.requestFocus();
                throw new Exception("Enter The File Name You Want!!");
            }

            String strpath = getExternalFilesDir(null) + "/MyWords";
            FileReader fr = new FileReader(strpath + "/" + Txtfilename.getText().toString() + ".txt");
            BufferedReader br = new BufferedReader(fr);

            String strFileSelect = "";
            String strAfterRead = "";

            while ((strAfterRead = br.readLine()) != null) {
                strFileSelect += strAfterRead + "\n";

            }

            fr = new FileReader(strpath + "/" + Txtfilename.getText().toString() + "Set.txt");
            br = new BufferedReader(fr);

            String[] strSet = new String[7];
            int x = 0;
            while ((strAfterRead = br.readLine()) != null) {
                strSet[x] = strAfterRead;
                x++;
            }
            fr.close();
            br.close();

            Txtsize.setText(strSet[0]);
            Txtwords.setTextSize(Integer.parseInt(strSet[0]));
            cbxbold.setChecked(Boolean.parseBoolean(strSet[1]));
            cbxunderline.setChecked(Boolean.parseBoolean(strSet[2]));
            Scolor.setSelection(((ArrayAdapter<String>) Scolor.getAdapter()).getPosition(strSet[3]));
            Sfont.setSelection(((ArrayAdapter<String>) Sfont.getAdapter()).getPosition(strSet[4]));
            RBLTR.setChecked(Boolean.parseBoolean(strSet[5]));
            RBRTL.setChecked(Boolean.parseBoolean(strSet[6]));
            Txtwords.setText(strFileSelect);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}






