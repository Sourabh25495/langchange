package com.example.sourabhkulkarni.langchange;



        import java.util.Locale;

        import android.os.Bundle;
        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
    //Declare components from activity main
    private TextView txt_hello, txt_helloworld;
    private Button btn_en, btn_ru, btn_gu, btn_hi;
    private Locale myLocale;
    //initialize everything and set on click listner
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txt_hello = (TextView)findViewById(R.id.txt_hello);
        this.txt_helloworld = (TextView)findViewById(R.id.txt_hello1);
        this.btn_en = (Button)findViewById(R.id.btn_en);
        this.btn_ru = (Button)findViewById(R.id.btn_ru);
        this.btn_gu = (Button)findViewById(R.id.btn_gu);
        this.btn_hi = (Button)findViewById(R.id.btn_hi);

        this.btn_en.setOnClickListener(this);
        this.btn_ru.setOnClickListener(this);
        this.btn_gu.setOnClickListener(this);
        this.btn_hi.setOnClickListener(this);

        loadLocale();
    }
    // Method to set language preference
    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        Log.d("Language loaded ",language);
        changeLang(language);
    }

    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }
    // method to save locale
    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        updateTexts();
    }

    private void updateTexts()
    {
        txt_hello.setText(R.string.hello_world);
        txt_helloworld.setText(getString(R.string.helloworld));
        btn_en.setText(R.string.btn_en);
        btn_ru.setText(R.string.btn_ru);
        btn_gu.setText(R.string.btn_gu);
        btn_hi.setText(R.string.btn_hi);
    }

    // method to change language
    @Override
    public void onClick(View v) {
        String lang = "en";
        switch (v.getId()) {
            case R.id.btn_en:
                lang = "en";
                break;
            case R.id.btn_ru:
                lang = "ru";
                break;
            case R.id.btn_gu:
                lang = "gu";
                break;
            case R.id.btn_hi:
                lang = "hi";
                break;
            default:
                break;
        }
        changeLang(lang);
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (myLocale != null){
            newConfig.locale = myLocale;
            Locale.setDefault(myLocale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }
}

