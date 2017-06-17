package trackinlogic.trans.pss.com.trackinlogic.model.logbook;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class DriverTripCycle
{
    private String assignedDate;

    public String getAssignedDate() { return this.assignedDate; }

    public void setAssignedDate(String assignedDate) { this.assignedDate = assignedDate; }

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private boolean isActive;

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    private String revocationDate;

    public String getRevocationDate() { return this.revocationDate; }

    public void setRevocationDate(String revocationDate) { this.revocationDate = revocationDate; }

    private ServiceCycle serviceCycle;

    public ServiceCycle getServiceCycle() { return this.serviceCycle; }

    public void setServiceCycle(ServiceCycle serviceCycle) { this.serviceCycle = serviceCycle; }

    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }
}
