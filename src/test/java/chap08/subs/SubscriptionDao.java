package test.java.chap08.subs;

public interface SubscriptionDao {
    Subscription selectByUser(String id);

    void insert(Subscription subscription);
}