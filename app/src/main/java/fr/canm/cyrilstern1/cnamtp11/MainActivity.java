package fr.canm.cyrilstern1.cnamtp11;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private  BluetoothAdapter blueAdapt;
    private String adapterName;
    private String adapterAdress;
    private void init(){
        blueAdapt =  BluetoothAdapter.getDefaultAdapter();
        bluethoofConnection(blueAdapt);
    }
    private void bluethoofConnection(BluetoothAdapter bluetoothAdapter){
        if(!bluetoothAdapter.isEnabled()) {
            Intent enableBlue=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlue,1);
        }
        searchForDevice();
    }
    private void searchForDevice(){
        if (blueAdapt.isEnabled()){
            adapterName = blueAdapt.getName();
            adapterAdress = blueAdapt.getAddress();
            blueAdapt.startDiscovery();
            IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            //intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            //
            // intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            Log.i("device",adapterName);

            registerReceiver(broadcastReceiver,intentFilter);

        }
    }
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = getIntent().getAction();
            Log.i("device",action);

            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
                Log.i("device","popopopo");

            }
            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                Log.i("device","popopopo");

            }
            if(action.equals(BluetoothDevice.ACTION_FOUND)){
             BluetoothDevice bluetoothDevice =  intent.getParcelableExtra(BluetoothDevice.EXTRA_NAME);
                 Toast.makeText(getApplicationContext(),bluetoothDevice.getName(),Toast.LENGTH_LONG);
                Log.i("device","popopopo");
            }
        }
    };
    @Override
    protected void onPause(){
        super.onPause();
        if (broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
        }
    }
}
