package trackinlogic.trans.pss.com.trackinlogic.model.logbook;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class ShortHaulOption
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

    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }
}