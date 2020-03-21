package edu.cnm.deepdive.yourautoservice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ActionDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Action action);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Action> actions);

  @Update
  Single<Integer> update(Action... actions);

  @Delete
  Single<Integer> delete(Action... actions);

  @Query("SELECT * FROM `Action` WHERE service_id = :serviceId ORDER BY summary ASC")
  LiveData<List<Action>> selectAll(long serviceId);

  @Query("SELECT * FROM `Action` WHERE action_id = :actionId")
  LiveData<Action> select(long actionId);

}
