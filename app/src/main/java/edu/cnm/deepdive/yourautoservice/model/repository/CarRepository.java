package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import android.content.Context;
import android.media.browse.MediaBrowser;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
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

  public MediaBrowser get(long id) {
    return null;
  }

  public MediaBrowser save(Car car) {
    return null;
  }

  public MediaBrowser remove(Car car) {
    return null;
  }

}
