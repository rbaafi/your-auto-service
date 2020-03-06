package edu.cnm.deepdive.yourautoservice.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.repository.CarRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private CarRepository repository;
  private MutableLiveData<Car> note;
  private MutableLiveData<Throwable> throwable;
  // TODO Declare and use a CompositeDisposable

  public MainViewModel(@NonNull Application application) {
    super(application);
    repository = new CarRepository();
    car = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
  }

  public LiveData<List<Car>> getAll() {
    throwable.setValue(null);
    return repository.getAll();
  }

  public LiveData<Car> getNote() {
    return car;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setNoteId(long id) {
    throwable.setValue(null);
    repository.get(id)
        .subscribe(
            note::postValue,
            throwable::postValue
        );
  }

  public void save(Note note) {
    throwable.setValue(null);
    repository.save(note)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public void remove(Note note) {
    throwable.setValue(null);
    repository.remove(note)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }


}
