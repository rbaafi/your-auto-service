package edu.cnm.deepdive.yourautoservice.model.entity;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public interface History {
  private static final int NETWORK_POOL_SIZE = 10;
  private static final String OAUTH_HEADER_FORMAT = "Bearer %s";

  private final Service proxy;
  private final Executor networkPool;

  private History() {
    proxy = Service.getInstance();
    networkPool = Executors.newFixedThreadPool(NETWORK_POOL_SIZE);
  }

  public static History getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public Single<Service> getAction(String token) {
    return proxy.getAction(String.format(OAUTH_HEADER_FORMAT, token))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Service>> getAllServices(String car) {
    return proxy.getAllServices(String.format(OAUTH_HEADER_FORMAT, car))
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Action>> getAllActions(String token, boolean includeNull) {
    return proxy.getAllActions(String.format(OAUTH_HEADER_FORMAT, token), includeNull)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<Service> add(String car, Service service) {
    return proxy.post(String.format(OAUTH_HEADER_FORMAT, car), service)
        .subscribeOn(Schedulers.from(networkPool));
  }

  public Single<List<Car>> getAllService(String car) {
    return getAllActions(car, true)
        .subscribeOn(Schedulers.io())
        .map((actions) -> {
          List<Action> combined = new ArrayList<>();
          for (Action action : actions) {
            combined.add(action);
            Collections.addAll(combined, action.getService());
          }
          return combined;
        });
  }

  private static class InstanceHolder {

    private static final History INSTANCE = (History) new InstanceHolder();

  }

}
