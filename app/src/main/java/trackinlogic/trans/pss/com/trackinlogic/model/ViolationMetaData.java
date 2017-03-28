package trackinlogic.trans.pss.com.trackinlogic.model;

public class ViolationMetaData {
    public int description_id;
    public String type;

    public ViolationMetaData(String type, int description_id) {
        this.type = type;
        this.description_id = description_id;
    }
}
