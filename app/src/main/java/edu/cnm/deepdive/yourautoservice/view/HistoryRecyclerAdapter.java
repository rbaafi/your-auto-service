package edu.cnm.deepdive.yourautoservice.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.model.entity.Action;
import edu.cnm.deepdive.yourautoservice.model.History;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.model.pojo.ServiceWithActions;
import edu.cnm.deepdive.yourautoservice.view.HistoryRecyclerAdapter.HistoryHolder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryHolder> {

  private final Context context;
  private final List<History> histories;
  private final Map<Class<? extends History>, Integer> layouts;
  private final Map<Integer, Class<? extends HistoryHolder>> holders;
  private final DateFormat dateFormat;
  private final NumberFormat numberFormat;


  public HistoryRecyclerAdapter(Context context,
      List<History> histories) {
    this.context = context;
    this.histories = histories;
    layouts = new HashMap<>();
    holders = new HashMap<>();
    layouts.put(ServiceWithActions.class, R.layout.item_history_service);
    holders.put(R.layout.item_history_service, ServiceHolder.class);
    layouts.put(Action.class, R.layout.item_history_action);
    holders.put(R.layout.item_history_action, ActionHolder.class);
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    numberFormat = NumberFormat.getNumberInstance();
  }


  @Override
  public int getItemViewType(int position) {
    return layouts.get(histories.get(position).getClass());
  }

  @NonNull
  @Override
  public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    try {
      View root = LayoutInflater.from(context).inflate(viewType, parent, false);
      Class<? extends HistoryHolder> holderClass = holders.get(viewType);
      Constructor<? extends HistoryHolder> constructor =
          holderClass.getDeclaredConstructor(HistoryRecyclerAdapter.class, View.class);
      return constructor.newInstance(this, root);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
    holder.bind(position, histories.get(position));

  }

  @Override
  public int getItemCount() {
    return histories.size();
  }

  static abstract class HistoryHolder extends ViewHolder {

    public HistoryHolder(@NonNull View itemView) {
      super(itemView);
    }

    public abstract void bind(int position, History history);
  }

  private class ServiceHolder extends HistoryHolder {

    private TextView date;
    private TextView mileage;

    public ServiceHolder(@NonNull View itemView) {
      super(itemView);
      date = itemView.findViewById(R.id.date);
      mileage = itemView.findViewById(R.id.mileage);
    }

    @Override
    public void bind(int position, History history) {
      Service service = (Service) history;
      date.setText(dateFormat.format(service.getDate()));
      mileage.setText(numberFormat.format(service.getMileage()));
    }
  }

  private class ActionHolder extends HistoryHolder {

    private TextView serviceType;
    private TextView description;
    private TextView summary;


    public ActionHolder(@NonNull View itemView) {
      super(itemView);
      serviceType = itemView.findViewById(R.id.service_type);
      description = itemView.findViewById(R.id.description);
      summary = itemView.findViewById(R.id.summary);
    }

    @Override
    public void bind(int position, History history) {
      Action action = (Action) history;
      serviceType.setText(action.getServiceType());
      description.setText(action.getDescription());
      summary.setText(action.getSummary());
    }
  }



}
