package edu.cnm.deepdive.yourautoservice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import edu.cnm.deepdive.yourautoservice.model.entity.AvailableCar;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface AvailableCarDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(AvailableCar availableCar);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<AvailableCar> cars);

  @Delete
  Single<Integer> delete(AvailableCar... cars);

  @Query("SELECT * FROM AvailableCar ORDER BY make, model, year")
  LiveData<List<AvailableCar>> select();

  @Query("SELECT DISTINCT make FROM AvailableCar ORDER BY make")
  LiveData<List<String>> selectMakes();

  @Query("SELECT DISTINCT model FROM AvailableCar  WHERE make = :make ORDER BY model")
  LiveData<List<String>> selectModels(String make);

}
