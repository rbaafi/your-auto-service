package edu.cnm.deepdive.yourautoservice.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import java.util.List;

public class ServiceWithActions extends Service {

  @Relation(entity = Action.class, parentColumn = "service_id", entityColumn = "service_id")
  private List<Action> actions;

  public List<Action> getActions() {
    return actions;
  }

  public void setActions(List<Action> actions) {
    this.actions = actions;
  }
}
