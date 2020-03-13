package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;

public class ServiceRepository {


  private final VehicleDatabase database;
  private static Application context;
  private ServiceRepository() {
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
    ServiceRepository.context = context;
  }

  private static class InstanceHolder {
    private static final ServiceRepository INSTANCE = new ServiceRepository();
  }
}
