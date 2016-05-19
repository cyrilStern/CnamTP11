package fr.canm.cyrilstern1.cnamtp11;

import android.net.Uri;

/**
 * Created by cyrilstern1 on 18/05/2016.
 */
public class BluthoofDevice {
    private String adressMAc;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setAdressMAc(String adressMAc) {

        this.adressMAc = adressMAc;
    }

    public String getName() {

        return name;
    }

    public String getAdressMAc() {

        return adressMAc;
    }

    public BluthoofDevice(String adressMac, String name) {

        this.adressMAc = adressMac;

        this.name = name;
    }

}
