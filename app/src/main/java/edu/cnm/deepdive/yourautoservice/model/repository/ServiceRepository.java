package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.yourautoservice.model.dao.ActionDao;
import edu.cnm.deepdive.yourautoservice.model.dao.ServiceDao;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.History;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.model.pojo.ServiceWithActions;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceRepository {

  private final VehicleDatabase database;
  private final ServiceDao serviceDao;
  private final ActionDao actionDao;
  private final Context context;

  public ServiceRepository(Context context) {
    this.context = context;
    database = VehicleDatabase.getInstance();
    serviceDao = database.getServiceDao();
    actionDao = database.getActionDao();
  }

  public LiveData<List<ServiceWithActions>> getAllServices(long carId) {
    return serviceDao.selectAllRelated(carId);
  }

  public LiveData<ServiceWithActions> getService(long serviceId) {
    return serviceDao.selectRelated(serviceId);
  }

  public LiveData<List<Action>> getAllActions(long serviceId) {
    return actionDao.selectAll(serviceId);
  }

  public LiveData<Action> getAction(long actionId) {
    return actionDao.select(actionId);
  }

  public Completable save(Service service) {
    if (service.getId() == 0) {
      return Completable.fromSingle(
          serviceDao.insert(service)
              .subscribeOn(Schedulers.io())
      );
    } else {
      return Completable.fromSingle(
          serviceDao.update(service)
              .subscribeOn(Schedulers.io())
      );
    }
  }

  public Completable save(Action action) {
    if (action.getId() == 0) {
      return Completable.fromSingle(
          actionDao.insert(action)
              .subscribeOn(Schedulers.io())
      );
    } else {
      return Completable.fromSingle(
          actionDao.update(action)
              .subscribeOn(Schedulers.io())
      );
    }
  }

  public Completable remove(Service service) {
    return Completable.fromSingle(
      serviceDao.delete(service)
          .subscribeOn(Schedulers.io())
    );
  }

  public Completable remove(Action action) {
    return Completable.fromSingle(
        actionDao.delete(action)
            .subscribeOn(Schedulers.io())
    );
  }


  public Single<List<History>> getAllCombined(long carId) {
    return serviceDao.selectAllCombined(carId)
        .subscribeOn(Schedulers.io())
        .map((services) -> {
          List<History> histories = new ArrayList<>();
          for (ServiceWithActions service : services) {
            histories.add(service);
            histories.addAll(service.getActions());
          }
          return histories;
        });
  }

}
