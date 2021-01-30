package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.NotificationHelper;

public class RouteTimerActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = RouteTimerActivity.class.getSimpleName();
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    private long startTimeInMillis;
    private long timeLeftInMillis;
    private boolean timerRunning;
    private boolean locationPermissionGranted = false;

    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlacesClient placesClient;
    private EditText searchText;
    private EditText timeInput;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private ProgressBar progressBar;
    private ImageView gps;
    private TextView countdownTextView;
    private TextView textQuote;
    private Button button;
    private CountDownTimer countDownTimer;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_timer);
        searchText = findViewById(R.id.searchEditText);
        timeInput = findViewById(R.id.timeEditText);
        relativeLayout1 = findViewById(R.id.relative1);
        relativeLayout2 = findViewById(R.id.relative2);
        progressBar = findViewById(R.id.progressBar);
        countdownTextView = findViewById(R.id.countdownTextView);
        textQuote = findViewById(R.id.textQuote);
        button = findViewById(R.id.startButton);

        textQuote.setText(stringArrayFromJNI()[randomNumber()]);
        new Handler().postDelayed(() -> textQuote.setVisibility(View.INVISIBLE), 5000);

        getLocationPermission();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (locationPermissionGranted) {
            getDeviceLocation();
            map.setMyLocationEnabled(true);
            map.getUiSettings().setCompassEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(false);
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                            locationPermissionGranted = false;
                            return;
                        }
                    }
                }

                locationPermissionGranted = true;
                initMap();
        }
    }

    private void getLocationPermission() {
        String[] permissions = {FINE_LOCATION, COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
                initMap();
            }

            else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }

        else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void init() {
        progressBar.setVisibility(View.INVISIBLE);
        countdownTextView.setVisibility(View.INVISIBLE);

        String apiKey = getString(R.string.google_api_key);
        gps = findViewById(R.id.ic_gps);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        button.setOnClickListener(v -> {
            if (timerRunning) {
                resetTimer();
            }
            else {
                timeLeftInMillis = Long.parseLong(timeInput.getText().toString()) * 60000;
                startTimer();
                sendOnChannel2();
            }
        });

        placesClient = Places.createClient(this);

        searchText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event.getAction() == KeyEvent.ACTION_DOWN
                    || event.getAction() == KeyEvent.KEYCODE_ENTER) {
                geoLocate();
            }

            return false;
        });

        gps.setOnClickListener(v -> getDeviceLocation());

        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void geoLocate() {
        if (isNetworkConnected()) {
            String searchString = searchText.getText().toString();
            Geocoder geocoder = new Geocoder(RouteTimerActivity.this);
            List<Address> addresses = new ArrayList<>();

            try {
                addresses = geocoder.getFromLocationName(searchString, 1);
            }
            catch (IOException e) {
                Log.d(TAG, "geoLocate: IOException " + e.getMessage());
            }

            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                zoomCamera(new LatLng(address.getLatitude(),
                        address.getLongitude()),
                        DEFAULT_ZOOM,
                        address.getAddressLine(0));
                Log.d(TAG, "geoLocate: found location " + address.toString());
            }
        }

        else {
            Toast.makeText(this, "Please activate your network first", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                button.setText(R.string.start);
            }
        }.start();

        timerRunning = true;
        relativeLayout1.setVisibility(View.INVISIBLE);
        relativeLayout2.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        countdownTextView.setVisibility(View.VISIBLE);
        button.setText(R.string.cancel);
    }

    private void resetTimer() {
        countDownTimer.cancel();
        timeLeftInMillis = startTimeInMillis;
        updateCountdownText();
        timerRunning = false;
        button.setText(R.string.start);
        relativeLayout1.setVisibility(View.VISIBLE);
        relativeLayout2.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        countdownTextView.setVisibility(View.INVISIBLE);
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        
        countdownTextView.setText(timeLeftFormatted);
    }

    private void getDeviceLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (locationPermissionGranted) {
                Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Location currentLocation = (Location) task.getResult();
                        zoomCamera(new LatLng(currentLocation.getLatitude(),
                                currentLocation.getLongitude()),
                                DEFAULT_ZOOM,
                                "Your Current Location");
                    }
                });
            }
        }

        catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException " + e.getMessage());
        }
    }

    private void zoomCamera(LatLng latLng, float zoom, String title) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if (title != "Your Current Location") {
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);
            map.clear();
            map.addMarker(markerOptions);
        }

        hideSoftKeyboard();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(RouteTimerActivity.this);
    }

    private void sendOnChannel2() {
        final int maxProgress = (int) timeLeftInMillis;
        final NotificationHelper notificationHelper = new NotificationHelper(this);
        final NotificationCompat.Builder builder = notificationHelper.getChannel2Notification();
        notificationHelper.getNotificationManager().notify(2, builder.build());

        new Thread(() -> {
            for (int progress = maxProgress; progress >= 0; progress -= 1000) {
                if (timerRunning) {
                    builder.setProgress(maxProgress, progress, false);
                    builder.setOnlyAlertOnce(true);
                    builder.setPriority(NotificationCompat.PRIORITY_LOW);
                    notificationHelper.getNotificationManager().notify(2, builder.build());
                }
                else {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.cancel(2);
                }
            }
        }).start();
    }

    public native String[] stringArrayFromJNI();
    public native int randomNumber();
}