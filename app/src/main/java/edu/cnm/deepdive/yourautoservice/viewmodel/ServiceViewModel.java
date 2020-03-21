package edu.cnm.deepdive.yourautoservice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.History;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.model.pojo.ServiceWithActions;
import edu.cnm.deepdive.yourautoservice.model.repository.ServiceRepository;
import java.util.List;

public class ServiceViewModel extends AndroidViewModel {

  private final ServiceRepository repository;
  private final MutableLiveData<Long> carId;
  private final MutableLiveData<Long> serviceId;
  private final LiveData<ServiceWithActions> service;
  private final MutableLiveData<Long> actionId;
  private final LiveData<Action> action;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<List<History>> histories;
  private final LiveData<List<ServiceWithActions>> services;

  public ServiceViewModel(@NonNull Application application) {
    super(application);
    repository = new ServiceRepository(application);
    carId = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    services = Transformations.switchMap(carId, (id) -> repository.getAllServices(id));
    serviceId = new MutableLiveData<>();
    service = Transformations.switchMap(serviceId, (id) -> repository.getService(id));
    actionId = new MutableLiveData<>();
    action = Transformations.switchMap(actionId, (id) -> repository.getAction(id));
    histories = new MutableLiveData<>();
  }

  public void setCarId(long carId) {
    this.carId.setValue(carId);
    refreshHistories();
  }

  public void setServiceId(long serviceId) {
    this.serviceId.setValue(serviceId);
  }

  public void setActionId(long actionId) {
    this.actionId.setValue(actionId);
  }


  public void refreshHistories() {
    repository.getAllCombined(carId.getValue())
        .subscribe(
            histories::postValue,
            throwable::postValue
        );
  }

  public LiveData<ServiceWithActions> getService() {
    return service;
  }

  public LiveData<Action> getAction() {
    return action;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<History>> getHistories() {
    return histories;
  }

  public LiveData<List<ServiceWithActions>> getServices() {
    return services;
  }

  public  void save(Service service) {
    repository.save(service)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public  void save(Action action) {
    repository.save(action)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public  void remove(Service service) {
    repository.remove(service)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public  void remove(Action action) {
    repository.remove(action)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }
}
