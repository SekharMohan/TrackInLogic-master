package trackinlogic.trans.pss.com.trackinlogic.model.login;

import java.util.ArrayList;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class User {

    private String email;

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    private String firstName;

    public String getFirstName() { return this.firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private boolean isActive;

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    private boolean isDriver;

    public boolean getIsDriver() { return this.isDriver; }

    public void setIsDriver(boolean isDriver) { this.isDriver = isDriver; }

    private String lastName;

    public String getLastName() { return this.lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    private ArrayList<Phone> phones;

    public ArrayList<Phone> getPhones() { return this.phones; }

    public void setPhones(ArrayList<Phone> phones) { this.phones = phones; }

    private String userName;

    public String getUserName() { return this.userName; }

    public void setUserName(String userName) { this.userName = userName; }
}


