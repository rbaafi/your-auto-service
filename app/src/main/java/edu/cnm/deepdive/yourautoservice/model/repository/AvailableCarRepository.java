package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import android.renderscript.Sampler.Value;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import java.security.Key;

public class AvailableCarRepository {


  private final VehicleDatabase database;
  private static Application context;

  private AvailableCarRepository() {
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
    AvailableCarRepository.context = context;
  }



  private static class InstanceHolder {

    private static final AvailableCarRepository INSTANCE = new AvailableCarRepository();
  }


// TODO Needs to provided list of  makes, models and years.


}
