package trackinlogic.trans.pss.com.trackinlogic.model.registration;

/**
 * Created by Sekhar Madhiyazhagan on 7/23/2017.
 */

public class TripCycle {

    public int id;
    public String name;
    public String descripton;
    public boolean isActive;
    public String restartOption;
    public String odometerUnit;
    public String breakOption;
    public String wellSiteOption;
    public String shortHaulOption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRestartOption() {
        return restartOption;
    }

    public void setRestartOption(String restartOption) {
        this.restartOption = restartOption;
    }

    public String getOdometerUnit() {
        return odometerUnit;
    }

    public void setOdometerUnit(String odometerUnit) {
        this.odometerUnit = odometerUnit;
    }

    public String getBreakOption() {
        return breakOption;
    }

    public void setBreakOption(String breakOption) {
        this.breakOption = breakOption;
    }

    public String getWellSiteOption() {
        return wellSiteOption;
    }

    public void setWellSiteOption(String wellSiteOption) {
        this.wellSiteOption = wellSiteOption;
    }

    public String getShortHaulOption() {
        return shortHaulOption;
    }

    public void setShortHaulOption(String shortHaulOption) {
        this.shortHaulOption = shortHaulOption;
    }
}
