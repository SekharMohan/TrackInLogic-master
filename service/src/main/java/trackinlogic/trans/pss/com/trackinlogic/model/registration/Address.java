package trackinlogic.trans.pss.com.trackinlogic.model.registration;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class Address {
        private String city;

        public String getCity() { return this.city; }

        public void setCity(String city) { this.city = city; }

        private int id;

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }

        private boolean isActive;

        public boolean getIsActive() { return this.isActive; }

        public void setIsActive(boolean isActive) { this.isActive = isActive; }

        private String line1;

        public String getLine1() { return this.line1; }

        public void setLine1(String line1) { this.line1 = line1; }

        private String line2;

        public String getLine2() { return this.line2; }

        public void setLine2(String line2) { this.line2 = line2; }

        private String postalCode;

        public String getPostalCode() { return this.postalCode; }

        public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

        private String stateOrProvience;

        public String getStateOrProvience() { return this.stateOrProvience; }

        public void setStateOrProvience(String stateOrProvience) { this.stateOrProvience = stateOrProvience; }

        private String zipCode;

        public String getZipCode() { return this.zipCode; }

        public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}

