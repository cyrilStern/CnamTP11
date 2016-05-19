package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.canm.cyrilstern1.cnamtp11.BluthoofDevice;
import fr.canm.cyrilstern1.cnamtp11.R;

/**
 * Created by cyrilstern1 on 18/05/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter <BluthoofDevice>{

    public CustomArrayAdapter(Context context, int resource) {
        super(context, resource);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BluthoofDevice bluthoofDevice = getItem(position);;
        View row = convertView;
        if(row == null)
        {

            FrameLayout frameLayout = new FrameLayout(getContext());
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            frameLayout.addView(linearLayout);
            ImageView imageView = new ImageView(getContext());
            TextView textViewAdressMac = new TextView(getContext());
            TextView textViewNameDevice = new TextView(getContext());
            LinearLayout linearLayout1 = new LinearLayout(getContext());
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(imageView);
            linearLayout.addView(linearLayout1);
            linearLayout1.addView(textViewAdressMac);
            linearLayout1.addView(textViewNameDevice);


            imageView.setImageResource(R.drawable.logo_bluethoof);
            textViewAdressMac.setText(bluthoofDevice.getAdressMAc());
            textViewNameDevice.setText(bluthoofDevice.getName());
            row = frameLayout;
        }


        return row;
    }

}
