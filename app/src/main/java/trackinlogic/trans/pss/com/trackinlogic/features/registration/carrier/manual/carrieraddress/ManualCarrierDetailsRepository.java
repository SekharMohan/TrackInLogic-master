package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.carrieraddress;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.service.db.CarrierAddressDBHelper;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class ManualCarrierDetailsRepository {
   public CarrierAddressDBHelper carrierAddressDbHelper;
    private final BehaviorSubject<Boolean> publishInsertSubject = BehaviorSubject.create();
    private final BehaviorSubject<Integer> publishNoRecordSubject = BehaviorSubject.create();

    public  ManualCarrierDetailsRepository(CarrierAddressDBHelper carrierAddressDbHelper) {
        this.carrierAddressDbHelper = carrierAddressDbHelper;
    }

    public Observable<Boolean> insertData() {
        return carrierAddressDbHelper.insertContact("","","","","",0).doOnNext(
                isInserted->publishInsertSubject.onNext(isInserted));
    }
}
