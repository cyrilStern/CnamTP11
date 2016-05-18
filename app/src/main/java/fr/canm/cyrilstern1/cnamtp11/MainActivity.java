package fr.canm.cyrilstern1.cnamtp11;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        BluetoothAdapter blueAdapt =  BluetoothAdapter.getDefaultAdapter();
        bluethoofConnection(blueAdapt);
    }
    private void bluethoofConnection(BluetoothAdapter bluetoothAdapter){
        if(!bluetoothAdapter.isEnabled()) {
            Intent enableBlue=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlue,1);
        }
    }
}
