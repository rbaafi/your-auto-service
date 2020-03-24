package edu.cnm.deepdive.yourautoservice.model.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.yourautoservice.model.dao.ActionDao;
import edu.cnm.deepdive.yourautoservice.model.dao.ServiceDao;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.model.pojo.ServiceWithActions;
import edu.cnm.deepdive.yourautoservice.service.VehicleDatabase;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ActionRepository {

  private final VehicleDatabase database;
  private final Context context;
  private final ActionDao actionDao;
  private final ServiceDao serviceDao;

  public ActionRepository(Context context) {
     database = VehicleDatabase.getInstance();
     this.context = context;
     actionDao = database.getActionDao();
     serviceDao = database.getServiceDao();
  }


  public Completable save(Action action) {
    if (action.getId() == 0) {
      return Completable.fromSingle(
          actionDao.insert(action)
              .subscribeOn(Schedulers.io())
      );
    } else {
      return Completable.fromSingle(
          actionDao.update(action)
              .subscribeOn(Schedulers.io())
      );
    }
  }

//  public Completable save(Service service) {
//    if (action.getId() == 0) {
//      return Completable.fromSingle(
//          actionDao.insert(action)
//              .subscribeOn(Schedulers.io())
//      );
//    } else {
//      return Completable.fromSingle(
//          actionDao.update(action)
//              .subscribeOn(Schedulers.io())
//      );
//    }
//  }

  public Completable remove(Service service) {
    return Completable.fromSingle(
        serviceDao.delete(service)
            .subscribeOn(Schedulers.io())
    );
  }

  public Completable remove(Action action) {
    return Completable.fromSingle(
        actionDao.delete(action)
            .subscribeOn(Schedulers.io())
    );
  }


}
