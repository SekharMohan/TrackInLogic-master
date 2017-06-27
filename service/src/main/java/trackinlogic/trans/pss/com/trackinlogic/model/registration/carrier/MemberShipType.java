package trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier;

/**
 * Created by Sekhar Madhiyazhagan on 6/26/2017.
 */

public class MemberShipType
{
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private boolean isActive;

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}

