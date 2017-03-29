package trackinlogic.trans.pss.com.trackinlogic.model;

/**
 * Created by DELL on 26-03-2017.
 */

public class LogBookHeader {

    private String day;
    private String date;
    private String duration;
    private String defectName;
    private String reasonForAlert;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    public String getReasonForAlert() {
        return reasonForAlert;
    }

    public void setReasonForAlert(String reasonForAlert) {
        this.reasonForAlert = reasonForAlert;
    }
}
