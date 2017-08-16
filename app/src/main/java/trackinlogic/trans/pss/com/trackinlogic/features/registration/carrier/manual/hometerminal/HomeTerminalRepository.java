package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.hometerminal;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.service.db.HomeTerminalDBHelper;

/**
 * Created by Sekhar Madhiyazhagan on 8/15/2017.
 */

public class HomeTerminalRepository
{
    public HomeTerminalDBHelper homeTerminalDBHelper;
        private final BehaviorSubject<Boolean> publishInsertSubject = BehaviorSubject.create();
        private final BehaviorSubject<Integer> publishNoRecordSubject = BehaviorSubject.create();

        public  HomeTerminalRepository(HomeTerminalDBHelper homeTerminalDBHelper) {
            this.homeTerminalDBHelper = homeTerminalDBHelper;
        }

        public Observable<Boolean> insertData() {
            return homeTerminalDBHelper.insertHomeAddress("","","","","",0).doOnNext(
                    isInserted->publishInsertSubject.onNext(isInserted));
        }
}
