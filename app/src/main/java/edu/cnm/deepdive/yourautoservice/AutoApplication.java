package edu.cnm.deepdive.yourautoservice;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import io.reactivex.schedulers.Schedulers;

public class AutoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    VehicleDatabase.setContext(this);
    VehicleDatabase.getInstance().getCarDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }
}
