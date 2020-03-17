package edu.cnm.deepdive.yourautoservice.model.repository;

import android.app.Application;
import android.content.Context;
import android.renderscript.Sampler.Value;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import io.reactivex.Single;
import java.security.Key;
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



// TODO Needs to provided list of  makes, models and years.
  // https://www.fueleconomy.gov/feg/EPAGreenGuide/Smartway/xls/SmartWay%20Vehicle%20List%20for%20MY%202020.xlsx


}
