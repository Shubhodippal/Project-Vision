package infoaryan.in.hc05_bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class LedControl extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btnDis;
    String address = null;
    TextView lumn;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_control2);


        Intent intent = getIntent();
        address = intent.getStringExtra(MainActivity.EXTRA_ADDRESS);

        btn1 =  findViewById(R.id.button2);
        btn2 =  findViewById(R.id.button3);
        //For additional actions to be performed
        btn3 =  findViewById(R.id.button5);
        btn4 =  findViewById(R.id.button6);
        btn6 = findViewById(R.id.button8);
        btn7 = findViewById(R.id.button9);
        btn5 =  findViewById(R.id.button7);
        btnDis = findViewById(R.id.button4);
        lumn =  findViewById(R.id.textView2);
        final boolean[] buttonPressed = {false};
        new LedControl.ConnectBT().execute();

        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("1");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("2");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });

        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("3");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });
        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("4");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });

        btn5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("5");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });

        btn6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("6");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });

        btn7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        if (!buttonPressed[0]) {
                            sendSignal("7");
                            buttonPressed[0] = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (buttonPressed[0]) {
                            sendSignal("0");
                            buttonPressed[0] = false;
                        }
                        break;
                }
                return true;
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Disconnect();
            }
        });
    }

    private void sendSignal ( String number ) {
        if ( btSocket != null ) {
            try {
                btSocket.getOutputStream().write(number.toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void Disconnect () {
        if ( btSocket!=null ) {
            try {
                btSocket.close();
            } catch(IOException e) {
                msg("Error");
            }
        }

        finish();
    }

    private void msg (String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected  void onPreExecute () {
            progress = ProgressDialog.show(LedControl.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground (Void... devices) {
            try {
                if ( btSocket==null || !isBtConnected ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute (Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected to the robot \nContinue to project VISION ");
                isBtConnected = true;
            }

            progress.dismiss();
        }
    }
}
