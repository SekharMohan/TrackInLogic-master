package trackinlogic.trans.pss.com.trackinlogic.model.registration.homeTerminals;

import java.util.ArrayList;

import trackinlogic.trans.pss.com.trackinlogic.model.login.Phone;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Address;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceType;

/**
 * Created by Sekhar Madhiyazhagan on 8/15/2017.
 */

public class HomeTerminals extends DeviceType {

    private ArrayList<Address> addresses;
    private ArrayList<Phone> phones;

    public ArrayList<Address> getAddresses() { return this.addresses; }

    public void setAddresses(ArrayList<Address> addresses) { this.addresses = addresses; }

    public ArrayList<Phone> getPhones() { return this.phones; }

    public void setPhones(ArrayList<Phone> phones) { this.phones = phones; }
}
