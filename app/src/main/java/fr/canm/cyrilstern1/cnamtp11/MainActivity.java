package fr.canm.cyrilstern1.cnamtp11;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import adapter.CustomArrayAdapter;


public class MainActivity extends AppCompatActivity  {
    private ListView lv;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        init();
    }
    private  BluetoothAdapter blueAdapt;
    private String adapterName;
    private String adapterAdress;
    private void init(){
        blueAdapt =  BluetoothAdapter.getDefaultAdapter();
        bluethoofConnection(blueAdapt);
        intentFilter = new IntentFilter();
    }
    private void bluethoofConnection(BluetoothAdapter bluetoothAdapter){
        if(!bluetoothAdapter.isEnabled()) {
            Intent enableBlue = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlue,1);
        }
    }
    public void searchForDevice(View view){
        if (blueAdapt.isEnabled()){
            adapterName = blueAdapt.getName();
            adapterAdress = blueAdapt.getAddress();
            intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
            intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            this.registerReceiver(broadcastReceiver,intentFilter);
            blueAdapt.startDiscovery();
            view.setEnabled(false);
            Button button = (Button) view;
            button.setText("pause");

        }
    }
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Log.i("device",action.toString());


            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)){
            Set<BluetoothDevice> set =  blueAdapt.getBondedDevices();
            ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>(set);
            CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(getApplicationContext(),R.layout.activity_main,list);
            lv.setAdapter(customArrayAdapter);
               // BluthoofDevice bluthoofDevice = new BluthoofDevice(bluetoothDevice.getAddress(),bluetoothDevice.getName());

            }
            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){

            }
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice bluetoothDevice =  intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.i("huihui","jknjkn");
                String string = bluetoothDevice.getName().toString();
                Log.i("device",bluetoothDevice.getAddress().toString());
                Log.i("device",string);

            }
        }
    };
    @Override
    protected void onPause(){
        super.onPause();
        if (broadcastReceiver!=null){
           // unregisterReceiver(broadcastReceiver);
        }
    }


}
