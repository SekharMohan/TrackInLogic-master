package trackinlogic.trans.pss.com.trackinlogic.model.login;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class Phone
{
    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private boolean isActive;

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    private String phoneNumber;

    public String getPhoneNumber() { return this.phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    private String phoneType;

    public String getPhoneType() { return this.phoneType; }

    public void setPhoneType(String phoneType) { this.phoneType = phoneType; }
}
