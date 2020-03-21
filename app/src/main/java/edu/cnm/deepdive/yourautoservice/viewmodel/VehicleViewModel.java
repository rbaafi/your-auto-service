package edu.cnm.deepdive.yourautoservice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.yourautoservice.model.entity.AvailableCar;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.repository.AvailableCarRepository;
import edu.cnm.deepdive.yourautoservice.model.repository.CarRepository;
import edu.cnm.deepdive.yourautoservice.model.repository.ServiceRepository;
import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

  private final CarRepository repository;
  private final AvailableCarRepository acRepository;
  private final MutableLiveData<Car> car;
  private final MutableLiveData<Throwable> throwable;
  private final LiveData<List<String>> models;
  private final MutableLiveData<String> make;

  // TODO Declare and use a CompositeDisposable

  public VehicleViewModel(@NonNull Application application) {
    super(application);
    repository = new CarRepository(application);
    acRepository = new AvailableCarRepository(application);
    car = new MutableLiveData<>();
    make = new MutableLiveData<>();
    models = Transformations.switchMap(make, (m) -> acRepository.getModels(m));
    throwable = new MutableLiveData<Throwable>();
  }

  public LiveData<List<Car>> getAll() {
    throwable.setValue(null);
    return repository.getAll();
  }

  public LiveData<Car> getCar() {
    LiveData<Car> car = new MutableLiveData<>();
    return car;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setCarId(long id) {
    throwable.setValue(null);
    repository.get(id);
  }

  public void save(Car car) {
    throwable.setValue(null);
    repository.save(car)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void remove(Car car) {
    throwable.setValue(null);
    repository.remove(car)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }



  public LiveData<List<Car>> getCars() {
    return repository.getAll();
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
