package edu.cnm.deepdive.yourautoservice.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.view.VehicleRecyclerAdapter.CarHolder;
import java.text.DateFormat;
import java.util.List;

public class VehicleRecyclerAdapter
    extends RecyclerView.Adapter<CarHolder> {

  private final Context context;
  private final OnClickListener listener;
  private final List<Car> cars;
  private final DateFormat dateFormat;

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

  public VehicleRecyclerAdapter(Context context,
      OnClickListener listener, List<Car> cars) {
    this.context = context;
    this.cars = cars;
    this.listener = listener;
    dateFormat = android.text.format.DateFormat.getMediumDateFormat(context);
  }

  @Override
  @NonNull
  public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.vehicle_list_content, parent, false);
    return new CarHolder(view);
  }

  @Override
  public void onBindViewHolder(final CarHolder holder, int position) {
    holder.bind(position, cars.get(position));
  }

  @Override
  public int getItemCount() {
    return cars.size();
  }

  class CarHolder extends RecyclerView.ViewHolder {

    final TextView make;
    final TextView model;
    final TextView year;
    final TextView date;

    private CarHolder(View view) {
      super(view);
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
