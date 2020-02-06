package edu.cnm.deepdive.yourautoservice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.yourautoservice.model.entity.AvailableCar;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

public interface AvailableCarDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(AvailableCar availableCar);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Car> cars);

  @Update
  Single<Integer> update(AvailableCar availableCar);

  @Delete
  Single<Integer> delete(Car... cars);

  @Query("SELECT * FROM AvailableCar ORDER BY make_id, model_id, year_id")
  LiveData<List<Car>> select();

}
