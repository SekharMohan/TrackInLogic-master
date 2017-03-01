package trackinlogic.trans.pss.com.trackinlogic.core.interfaces;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
public abstract class BasePresenter<T> {

    private final CompositeSubscription attachedSubscriptions = new CompositeSubscription();

    protected abstract void onViewAttached(T view);

    public void onViewDetached() {
        this.attachedSubscriptions.clear();
    }

    protected void addAttachedSubscription(Subscription subscribe) {
        this.attachedSubscriptions.add(subscribe);
    }
}
