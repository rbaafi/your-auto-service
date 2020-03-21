package edu.cnm.deepdive.yourautoservice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.model.pojo.ServiceWithActions;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Header;

@Dao
public interface ServiceDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Service service);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Service> services);

  @Update
  Single<Integer> update(Service service);

  @Delete
  Single<Integer> delete(Service... services);

  @Query("SELECT * FROM Service WHERE car_id = :carId")
  LiveData<List<Service>> select(long carId);

  @Transaction
  @Query("SELECT * FROM Service WHERE car_id = :carId")
  LiveData<List<ServiceWithActions>> selectAllRelated(long carId);

  @Transaction
  @Query("SELECT * FROM Service WHERE service_id = :serviceId")
  LiveData<ServiceWithActions> selectRelated(long serviceId);

  @Transaction
  @Query("SELECT * FROM Service WHERE car_id = :carId")
  Single<List<ServiceWithActions>> selectAllCombined(long carId);

}
