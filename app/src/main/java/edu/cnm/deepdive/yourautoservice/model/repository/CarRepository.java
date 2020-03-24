package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import android.content.Context;
import android.media.browse.MediaBrowser;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class CarRepository {

  private final VehicleDatabase database;
  private final Context context;

  public CarRepository(Context context) {
    this.context = context;
    database = VehicleDatabase.getInstance();
  }

  public LiveData<List<Car>> getAll() {
    return database.getCarDao().select();
  }

  public LiveData<Car> get(long id) {
    return database.getCarDao().select(id);
  }

  public Completable save(Car car) {
    if (car.getId() == 0) {
      return Completable.fromSingle(
          database.getCarDao().insert(car)
              .subscribeOn(Schedulers.io())
      );
    } else {
      return Completable.fromSingle(
          database.getCarDao().update(car)
              .subscribeOn(Schedulers.io())
      );
    }
  }

  public Completable remove(Car car) {
    return Completable.fromSingle(
        database.getCarDao().delete(car)
            .subscribeOn(Schedulers.io())
    );
  }

}
