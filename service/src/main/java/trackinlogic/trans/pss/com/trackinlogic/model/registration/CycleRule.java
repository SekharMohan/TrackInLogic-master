package trackinlogic.trans.pss.com.trackinlogic.model.registration;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class CycleRule {
    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private boolean isActive;

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    private String unitName;

    public String getUnitName() { return this.unitName; }

    public void setUnitName(String unitName) { this.unitName = unitName; }
}
