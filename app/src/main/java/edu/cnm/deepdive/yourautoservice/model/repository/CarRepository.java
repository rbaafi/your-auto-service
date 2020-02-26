package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;

public class CarRepository {

  private final VehicleDatabase database;
  private static Application context;
  private CarRepository() {
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
  private static class InstanceHolder {
    private static final CarRepository INSTANCE = new CarRepository();
  }

}
