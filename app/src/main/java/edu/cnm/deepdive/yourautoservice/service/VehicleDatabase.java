package edu.cnm.deepdive.yourautoservice.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.yourautoservice.model.dao.ActionDao;
import edu.cnm.deepdive.yourautoservice.model.dao.AvailableCarDao;
import edu.cnm.deepdive.yourautoservice.model.dao.CarDao;
import edu.cnm.deepdive.yourautoservice.model.dao.ServiceDao;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.AvailableCar;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase.Converters;
import java.util.Date;

@Database(
    entities = {Car.class, Action.class, AvailableCar.class, Service.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class VehicleDatabase extends RoomDatabase {

  private static final String DB_NAME = "car_db";

  private static Application context;

  public static void setContext(Application context) {
    VehicleDatabase.context = context;
  }

  public static VehicleDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract CarDao getCarDao();

  public abstract ServiceDao getServiceDao();

  public abstract ActionDao getActionDao();

  public abstract AvailableCarDao getAvailableCarDao();

  private static class InstanceHolder {

    private static final VehicleDatabase INSTANCE = Room.databaseBuilder(
        context, VehicleDatabase.class, DB_NAME)
        .build();

  }

  public static class Converters {

    @TypeConverter
    public static Long fromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }

    @TypeConverter
    public static Date fromLong(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }
}


