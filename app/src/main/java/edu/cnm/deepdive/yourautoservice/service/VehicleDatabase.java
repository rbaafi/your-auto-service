package edu.cnm.deepdive.yourautoservice.service;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.model.dao.ActionDao;
import edu.cnm.deepdive.yourautoservice.model.dao.AvailableCarDao;
import edu.cnm.deepdive.yourautoservice.model.dao.CarDao;
import edu.cnm.deepdive.yourautoservice.model.dao.ServiceDao;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.AvailableCar;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

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

    private static final VehicleDatabase INSTANCE =
        Room.databaseBuilder(context, VehicleDatabase.class, DB_NAME)
            .addCallback(new Callback(context))
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

  private static class Callback extends RoomDatabase.Callback {

    private final Context context;

    private Callback(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try (
          InputStream input = context.getResources().openRawResource(R.raw.vehicles);
          Reader reader = new InputStreamReader(input);
      ) {
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        List<AvailableCar> availableCars = new LinkedList<>();
        for (CSVRecord record : records) {
          Log.d(getClass().getName(), record.toString());
          AvailableCar car = new AvailableCar();
          car.setMake(record.get("make"));
          car.setModel(record.get("model"));
          car.setYear(Integer.parseInt(record.get("year")));
          availableCars.add(car);
        }
        VehicleDatabase.getInstance().getAvailableCarDao().insert(availableCars)
            .subscribeOn(Schedulers.io())
            .subscribe();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Car> cars = new LinkedList<>();
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setYear(2020);
        car.setAcquisition(format.parse("2020-01-01"));
        cars.add(car);
        car = new Car();
        car.setMake("Hyundai");
        car.setModel("Sonato");
        car.setYear(2020);
        car.setAcquisition(format.parse("2020-01-01"));
        cars.add(car);
        car = new Car();
        car.setMake("Tesla");
        car.setModel("SModel");
        car.setYear(2020);
        car.setAcquisition(format.parse("2020-01-01"));
        cars.add(car);
        VehicleDatabase.getInstance().getCarDao().insert(cars)
            .subscribeOn(Schedulers.io())
            .map(
                (ids) -> {
                  List<Service> services = new LinkedList<>();
                  for (long id : ids) {
                    Service service = new Service();
                    service.setCarId(id);
                    service.setDate(new Date());
                    service.setMileage(id * 100000);
                    services.add(service);
                  }
                  return services;
                }
            )
            .subscribe(
                (services) -> VehicleDatabase.getInstance().getServiceDao().insert(services)
                    .map((ids) -> {
                          List<Action> actions = new LinkedList<>();
                          for (long id : ids) {
                            Action action = new Action();
                            action.setServiceId(id);
                            action.setServiceType("cars");
                            action.setSummary("vehicles");
                            actions.add(action);
                          }
                          return actions;
                        }
                    )
                    .subscribe(
                        (actions) -> VehicleDatabase.getInstance().getActionDao().insert(actions)
                            .subscribe()

                    )
            );
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  }
}


