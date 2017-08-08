package trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import trackinlogic.trans.pss.com.trackinlogic.model.login.Phone;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Address;

/**
 * Created by Sekhar Madhiyazhagan on 6/26/2017.
 */

public class CarrierDetails  implements Parcelable{
        private int id;


        protected CarrierDetails(Parcel in) {
                id = in.readInt();
                name = in.readString();
                groupId = in.readString();
                description = in.readString();
                isActive = in.readByte() != 0;
                dotId = in.readString();
                status = in.readString();
        }

        public static final Creator<CarrierDetails> CREATOR = new Creator<CarrierDetails>() {
                @Override
                public CarrierDetails createFromParcel(Parcel in) {
                        return new CarrierDetails(in);
                }

                @Override
                public CarrierDetails[] newArray(int size) {
                        return new CarrierDetails[size];
                }
        };

        public int getId() { return this.id; }

        public void setId(int id) { this.id = id; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        private String groupId;

        public String getGroupId() { return this.groupId; }

        public void setGroupId(String groupId) { this.groupId = groupId; }

        private String description;

        public String getDescription() { return this.description; }

        public void setDescription(String description) { this.description = description; }

        private boolean isActive;

        public boolean getIsActive() { return this.isActive; }

        public void setIsActive(boolean isActive) { this.isActive = isActive; }

        private String dotId;

        public String getDotId() { return this.dotId; }

        public void setDotId(String dotId) { this.dotId = dotId; }

        private String status;

        public String getStatus() { return this.status; }

        public void setStatus(String status) { this.status = status; }

        private MemberShipType memberShipType;

        public MemberShipType getMemberShipType() { return this.memberShipType; }

        public void setMemberShipType(MemberShipType memberShipType) { this.memberShipType = memberShipType; }

        private ArrayList<Address> addresses;

        public ArrayList<Address> getAddresses() { return this.addresses; }

        public void setAddresses(ArrayList<Address> addresses) { this.addresses = addresses; }

        private ArrayList<Phone> phones;

        public ArrayList<Phone> getPhones() { return this.phones; }

        public void setPhones(ArrayList<Phone> phones) { this.phones = phones; }

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(name);
                dest.writeString(groupId);
                dest.writeString(description);
                dest.writeByte((byte) (isActive ? 1 : 0));
                dest.writeString(dotId);
                dest.writeString(status);
        }
}

