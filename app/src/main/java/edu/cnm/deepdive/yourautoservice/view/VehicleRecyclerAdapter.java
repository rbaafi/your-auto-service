package edu.cnm.deepdive.yourautoservice.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.controller.VehicleListActivity;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.view.VehicleRecyclerAdapter.MyViewHolder;
import java.text.DateFormat;
import java.util.List;

public class VehicleRecyclerAdapter
    extends RecyclerView.Adapter<MyViewHolder> {

  private final OnClickListener listener;
  private final VehicleListActivity mParentActivity;
  private final List<Car> cars;
  private final DateFormat dateFormat;
  private final boolean mTwoPane;
//  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//
//    @Override
//    public void onClick(View view) {
//      CarItem item = (CarItem) view.getTag();
//      if (mTwoPane) {
//        Bundle arguments = new Bundle();
//        arguments.putString(VehicleDetailFragment.ARG_ITEM_ID, item.id);
//        VehicleDetailFragment fragment = new VehicleDetailFragment();
//        fragment.setArguments(arguments);
//        mParentActivity.getSupportFragmentManager().beginTransaction()
//            .replace(R.id.vehicle_detail_container, fragment)
//            .commit();
//      } else {
//        Context context = view.getContext();
//        Intent intent = new Intent(context, VehicleDetailActivity.class);
//        intent.putExtra(VehicleDetailFragment.ARG_ITEM_ID, item.id);
//
//        context.startActivity(intent);
//      }
//    }
//  };

  public VehicleRecyclerAdapter(VehicleListActivity parent,
      OnClickListener listener, List<Car> cars, boolean twoPane) {
    this.cars = cars;
    mParentActivity = parent;
    this.listener = listener;
    mTwoPane = twoPane;
    dateFormat = android.text.format.DateFormat.getMediumDateFormat(parent);
  }

  @Override
  @NonNull
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.vehicle_list_content, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    holder.bind(position, cars.get(position));
  }

  @Override
  public int getItemCount() {
    return cars.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    final TextView make;
    final TextView model;
    final TextView year;
    final TextView date;

    private MyViewHolder(View view) {
      super(view);
      view.setTag(null);
      make = view.findViewById(R.id.make);
      model = view.findViewById(R.id.model);
      year = view.findViewById(R.id.year);
      date = view.findViewById(R.id.date);
    }

    private void bind(int position, Car car) {
      make.setText(car.getMake());
      model.setText(car.getModel());
      year.setText(Integer.toString(car.getYear()));
      date.setText(dateFormat.format(car.getAcquisition()));
      itemView.setOnClickListener((v) -> listener.onClick(v, car,  position));
    }
  }

  @FunctionalInterface
  public interface OnClickListener {

    void onClick(View view, Car car, int position);

  }

}
