package edu.cnm.deepdive.yourautoservice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.repository.CarRepository;
import java.util.List;

public class VehicleViewModel extends AndroidViewModel {

  private CarRepository repository;
  private MutableLiveData<Car> car;
  private MutableLiveData<Throwable> throwable;
  // TODO Declare and use a CompositeDisposable

  public VehicleViewModel(@NonNull Application application) {
    super(application);
    repository = new CarRepository(application);
    LiveData<Object> car = new MutableLiveData<>();
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
    repository.save(car);
  }

  public void remove(Car car) {
    throwable.setValue(null);
    repository.remove(car);
  }


  public LiveData<List<Car>> getCars() {
    return repository.getAll();
  }
}
