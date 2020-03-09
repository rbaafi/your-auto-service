package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import android.media.browse.MediaBrowser;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import java.util.List;

public class CarRepository {

  private final VehicleDatabase database;
  private static Application context;
  public CarRepository() {
    if (context == null) {
      throw new IllegalStateException();
    }
    database = VehicleDatabase.getInstance();
  }
  public VehicleDatabase getDatabase() {
    return database;
  }
  public static Application getContext() {
    return context;
  }
  public static void setContext(Application context) {
    CarRepository.context = context;
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

  private static class InstanceHolder {
    private static final CarRepository INSTANCE = new CarRepository();
  }

}
