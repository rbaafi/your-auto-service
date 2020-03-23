package edu.cnm.deepdive.yourautoservice.model.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import java.util.List;

public class AvailableCarRepository {


  private final VehicleDatabase database;
  private final Context context;

  public AvailableCarRepository(Context context) {
     database = VehicleDatabase.getInstance();
     this.context = context;
  }

  public LiveData<List<String>> getMakes() {
    return database.getAvailableCarDao().selectMakes();
  }

  public LiveData<List<String>> getModels(String make) {
    return database.getAvailableCarDao().selectModels(make);
  }
}
