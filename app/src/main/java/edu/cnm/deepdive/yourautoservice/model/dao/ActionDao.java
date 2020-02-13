package edu.cnm.deepdive.yourautoservice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
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
  Single<Integer> update(Action action);

  @Delete
  Single<Integer> delete(Action... Action);

  @Query("SELECT * FROM 'Action'")
  LiveData<List<Action>> select();

}
