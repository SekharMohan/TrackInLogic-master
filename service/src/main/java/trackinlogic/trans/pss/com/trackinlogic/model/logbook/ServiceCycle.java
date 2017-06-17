package trackinlogic.trans.pss.com.trackinlogic.model.logbook;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class ServiceCycle
{
    private BreakOption breakOption;

    public BreakOption getBreakOption() { return this.breakOption; }

    public void setBreakOption(BreakOption breakOption) { this.breakOption = breakOption; }

    private String descripton;

    public String getDescripton() { return this.descripton; }

    public void setDescripton(String descripton) { this.descripton = descripton; }

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private boolean isActive;

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private OdometerUnit odometerUnit;

    public OdometerUnit getOdometerUnit() { return this.odometerUnit; }

    public void setOdometerUnit(OdometerUnit odometerUnit) { this.odometerUnit = odometerUnit; }

    private RestartOption restartOption;

    public RestartOption getRestartOption() { return this.restartOption; }

    public void setRestartOption(RestartOption restartOption) { this.restartOption = restartOption; }

    private ShortHaulOption shortHaulOption;

    public ShortHaulOption getShortHaulOption() { return this.shortHaulOption; }

    public void setShortHaulOption(ShortHaulOption shortHaulOption) { this.shortHaulOption = shortHaulOption; }

    private WellSiteOption wellSiteOption;

    public WellSiteOption getWellSiteOption() { return this.wellSiteOption; }

    public void setWellSiteOption(WellSiteOption wellSiteOption) { this.wellSiteOption = wellSiteOption; }
}
