package crudcomclasse.app.crys.crudcomclasse;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    //Definição do tempo de espera:
    private static final int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /*Executar alguma regra de negócio
        * enquanto carrega a tela Splash
        * -GPS
        * - Ler Preferências*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        },SPLASH_TIME_OUT);

    }
}
