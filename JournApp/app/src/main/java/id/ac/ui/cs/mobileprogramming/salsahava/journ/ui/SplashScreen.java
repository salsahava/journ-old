package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = SplashScreen.class.getSimpleName();
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        openGLView = findViewById(R.id.openGLView);

        if (isServiceOK()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2500);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        openGLView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        openGLView.onPause();
    }

    public boolean isServiceOK() {
        Log.d(TAG, "isServiceOK: checking");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(SplashScreen.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServiceOK: Google Play service is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServiceOK: an error occured");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(SplashScreen.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "You can't make mao request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
