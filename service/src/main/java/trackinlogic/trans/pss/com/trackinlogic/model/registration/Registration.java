package trackinlogic.trans.pss.com.trackinlogic.model.registration;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class Registration {

    private String firstName;
    private String lastName;
    private String userName;
    private boolean isActive;
    private boolean isAdmin;
    private boolean isDispatcher;
    private boolean isDriver;
    private boolean isFinancialPlanner;
    private boolean isOperationPlanner;
    private boolean isUser;
    private String email;
    private String carrierName;
    private String carrierStatus;
    private String password;
    private DeviceType deviceType = new DeviceType() ;
    private String phoneType = "Mobile";
    private String phoneNumber;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public boolean getIsDispatcher() {
        return this.isDispatcher;
    }

    public void setIsDispatcher(boolean isDispatcher) {
        this.isDispatcher = isDispatcher;
    }


    public boolean getIsDriver() {
        return this.isDriver;
    }

    public void setIsDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }


    public boolean getIsFinancialPlanner() {
        return this.isFinancialPlanner;
    }

    public void setIsFinancialPlanner(boolean isFinancialPlanner) {
        this.isFinancialPlanner = isFinancialPlanner;
    }


    public boolean getIsOperationPlanner() {
        return this.isOperationPlanner;
    }

    public void setIsOperationPlanner(boolean isOperationPlanner) {
        this.isOperationPlanner = isOperationPlanner;
    }


    public boolean getIsUser() {
        return this.isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCarrierName() {
        return this.carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }


    public String getCarrierStatus() {
        return this.carrierStatus;
    }

    public void setCarrierStatus(String carrierStatus) {
        this.carrierStatus = carrierStatus;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }


    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }


    public class DeviceType {
        private String name = "individual";

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
