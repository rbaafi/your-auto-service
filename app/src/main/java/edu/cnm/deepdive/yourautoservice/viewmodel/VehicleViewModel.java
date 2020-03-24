package edu.cnm.deepdive.yourautoservice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.model.repository.AvailableCarRepository;
import edu.cnm.deepdive.yourautoservice.model.repository.CarRepository;
import edu.cnm.deepdive.yourautoservice.model.repository.ServiceRepository;
import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

  private final ServiceRepository serviceRepository;
  private final CarRepository carRepository;
  private final AvailableCarRepository acRepository;
  private final LiveData<Car> car;
  private final MutableLiveData<Long> carId;
  private final LiveData<Service> service;
  private final MutableLiveData<Long> serviceId;
  private final LiveData<Action> action;
  private final MutableLiveData<Long> actionId;
  private final MutableLiveData<Throwable> throwable;
  private final LiveData<List<String>> models;
  private final MutableLiveData<String> make;

  // TODO Declare and use a CompositeDisposable

  public VehicleViewModel(@NonNull Application application) {
    super(application);
    carRepository = new CarRepository(application);
    serviceRepository = new ServiceRepository(application);
    acRepository = new AvailableCarRepository(application);
    carId = new MutableLiveData<>();
    car = Transformations.switchMap(carId, carRepository::get);
    serviceId = new MutableLiveData<>();
    service = Transformations.switchMap(serviceId, serviceRepository::get);
    actionId = new MutableLiveData<>();
    action = Transformations.switchMap(actionId, serviceRepository::getAction);
    make = new MutableLiveData<>();
    models = Transformations.switchMap(make, acRepository::getModels);
    throwable = new MutableLiveData<Throwable>();
  }

  public LiveData<List<Car>> getAll() {
    throwable.setValue(null);
    return carRepository.getAll();
  }

  public LiveData<Car> getCar() {
    return car;
  }

  public LiveData<Service> getService() {
    return service;
  }

  public LiveData<Action> getAction() {
    return action;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setCarId(long id) {
    throwable.setValue(null);
    this.carId.setValue(id);
  }

  public void setServiceId(long id) {
    throwable.setValue(null);
    this.serviceId.setValue(id);
  }

  public void setActionId(long id) {
    throwable.setValue(null);
    this.actionId.setValue(id);
  }


  public void save(Car car) {
    throwable.setValue(null);
    carRepository.save(car)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void remove(Car car) {
    throwable.setValue(null);
    carRepository.remove(car)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void save(Service service) {
    serviceRepository.save(service)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void remove(Service service) {
    serviceRepository.remove(service)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void save(Action action) {
    serviceRepository.save(action)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void remove(Action action) {
    serviceRepository.remove(action)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }


  public LiveData<List<Car>> getCars() {
    return carRepository.getAll();
  }

  public LiveData<List<String>> getMakes() {
    return acRepository.getMakes();
  }

  public void setMake(String make) {
    this.make.setValue(make);
  }

  public LiveData<List<String>> getModels() {
    return models;
  }
}
