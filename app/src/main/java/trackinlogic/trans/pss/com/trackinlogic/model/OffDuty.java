package trackinlogic.trans.pss.com.trackinlogic.model;

/**
 * Created by DELL on 26-03-2017.
 */

public class OffDuty {

    private String startingTime;
    private String duration;
    private String city;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
