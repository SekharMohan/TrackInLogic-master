package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.service.db.DBHelper;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class ManualCarrierDetailsRepository {
    DBHelper dbHelper;
    private final BehaviorSubject<Boolean> publishInsertSubject = BehaviorSubject.create();
    private final BehaviorSubject<Integer> publishNoRecordSubject = BehaviorSubject.create();

    ManualCarrierDetailsRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public Observable<Boolean> insertData() {
        return dbHelper.insertContact(0,"","","","","",0).doOnNext(isInserted->publishInsertSubject.onNext(isInserted));
    }
}
