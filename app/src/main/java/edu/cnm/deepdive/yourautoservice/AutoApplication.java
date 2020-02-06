package edu.cnm.deepdive.yourautoservice;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class AutoApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
