package edu.cnm.deepdive.yourautoservice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface CarDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Car car);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Car> cars);

  @Update
  Single<Integer> update(Car cars);

  @Delete
  Single<Integer> delete(Car... cars);

  @Query("SELECT * FROM Car ORDER BY make, model, year")
  LiveData<List<Car>> select();
}
