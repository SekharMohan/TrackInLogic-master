package trackinlogic.trans.pss.com.trackinlogic.model.logbook;

import java.util.Date;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class TripLogs {
        private String carrierName;

        public String getCarrierName() { return this.carrierName; }

        public void setCarrierName(String carrierName) { this.carrierName = carrierName; }

        private String coDriver;

        public String getCoDriver() { return this.coDriver; }

        public void setCoDriver(String coDriver) { this.coDriver = coDriver; }

        private int distance;

        public int getDistance() { return this.distance; }

        public void setDistance(int distance) { this.distance = distance; }

        private String driverName;

        public String getDriverName() { return this.driverName; }

        public void setDriverName(String driverName) { this.driverName = driverName; }

        private DriverTripCycle driverTripCycle;

        public DriverTripCycle getDriverTripCycle() { return this.driverTripCycle; }

        public void setDriverTripCycle(DriverTripCycle driverTripCycle) { this.driverTripCycle = driverTripCycle; }

        private String homeTerminalAddress;

        public String getHomeTerminalAddress() { return this.homeTerminalAddress; }

        public void setHomeTerminalAddress(String homeTerminalAddress) { this.homeTerminalAddress = homeTerminalAddress; }

        private int id;

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }

        private boolean isActive;

        public boolean getIsActive() { return this.isActive; }

        public void setIsActive(boolean isActive) { this.isActive = isActive; }

        private boolean isSigned;

        public boolean getIsSigned() { return this.isSigned; }

        public void setIsSigned(boolean isSigned) { this.isSigned = isSigned; }

        private Date loadDateTime;

        public Date getLoadDateTime() { return this.loadDateTime; }

        public void setLoadDateTime(Date loadDateTime) { this.loadDateTime = loadDateTime; }

        private Date logDate;

        public Date getLogDate() { return this.logDate; }

        public void setLogDate(Date logDate) { this.logDate = logDate; }

        private String mainOfficeAddress;

        public String getMainOfficeAddress() { return this.mainOfficeAddress; }

        public void setMainOfficeAddress(String mainOfficeAddress) { this.mainOfficeAddress = mainOfficeAddress; }

        private boolean signed;

        public boolean getSigned() { return this.signed; }

        public void setSigned(boolean signed) { this.signed = signed; }

        private String trailerNo;

        public String getTrailerNo() { return this.trailerNo; }

        public void setTrailerNo(String trailerNo) { this.trailerNo = trailerNo; }

        private String tripLogDate;

        public String getTripLogDate() { return this.tripLogDate; }

        public void setTripLogDate(String tripLogDate) { this.tripLogDate = tripLogDate; }

        private Date unloadDateTime;

        public Date getUnloadDateTime() { return this.unloadDateTime; }

        public void setUnloadDateTime(Date unloadDateTime) { this.unloadDateTime = unloadDateTime; }

        private String vehicleNo;

        public String getVehicleNo() { return this.vehicleNo; }

        public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }
}
