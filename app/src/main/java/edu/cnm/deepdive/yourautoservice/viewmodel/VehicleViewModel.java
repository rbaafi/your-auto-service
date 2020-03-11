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
  private MutableLiveData<Car> note;
  private MutableLiveData<Car> throwable;
  // TODO Declare and use a CompositeDisposable

  public VehicleViewModel(@NonNull Application application) {
    super(application);
    repository = new CarRepository(application);
    LiveData<Object> car = new MutableLiveData<>();
    throwable = new MutableLiveData<Car>();
  }

  public LiveData<List<Car>> getAll() {
    throwable.setValue(null);
    return repository.getAll();
  }

  public LiveData<Car> getNote() {
    LiveData<Car> car = new MutableLiveData<>();
    return car;
  }

  public LiveData<Car> getThrowable() {
    return throwable;
  }

  public void setNoteId(long id) {
    throwable.setValue(null);
    repository.get(id);
  }

  public void save(Car car) {
    throwable.setValue(car);
    repository.save(car);
  }

  public void remove(Car car) {
    throwable.setValue(car);
    repository.remove(car);
  }


  public LiveData<List<Car>> getCars() {
    return repository.getAll();
  }
}
