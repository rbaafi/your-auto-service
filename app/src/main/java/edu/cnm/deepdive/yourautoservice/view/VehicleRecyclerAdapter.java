package edu.cnm.deepdive.yourautoservice.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.content.CarContent.CarItem;
import edu.cnm.deepdive.yourautoservice.content.VehicleDetailActivity;
import edu.cnm.deepdive.yourautoservice.content.VehicleDetailFragment;
import edu.cnm.deepdive.yourautoservice.content.VehicleListActivity;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import java.text.DateFormat;
import java.util.List;

public class VehicleRecyclerAdapter
    extends RecyclerView.Adapter<VehicleRecyclerAdapter.ViewHolder> {

  private final VehicleListActivity mParentActivity;
  private final List<Car> cars;
  private final DateFormat dateFormat;
  private final boolean mTwoPane;
  private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      CarItem item = (CarItem) view.getTag();
      if (mTwoPane) {
        Bundle arguments = new Bundle();
        arguments.putString(VehicleDetailFragment.ARG_ITEM_ID, item.id);
        VehicleDetailFragment fragment = new VehicleDetailFragment();
        fragment.setArguments(arguments);
        mParentActivity.getSupportFragmentManager().beginTransaction()
            .replace(R.id.vehicle_detail_container, fragment)
            .commit();
      } else {
        Context context = view.getContext();
        Intent intent = new Intent(context, VehicleDetailActivity.class);
        intent.putExtra(VehicleDetailFragment.ARG_ITEM_ID, item.id);

        context.startActivity(intent);
      }
    }
  };

  public VehicleRecyclerAdapter(VehicleListActivity parent, List<Car> items, boolean twoPane) {
    cars = items;
    mParentActivity = parent;
    mTwoPane = twoPane;
    dateFormat = android.text.format.DateFormat.getMediumDateFormat(parent);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.vehicle_list_content, parent, false);
    return new ViewHolder(view, dateFormat);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.bind(position, cars.get(position));
  }

  @Override
  public int getItemCount() {
    return cars.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final TextView make;
    final TextView model;
    final TextView year;
    final TextView date;
    final DateFormat format;

    private ViewHolder(View view, DateFormat format) {
      super(view);
      make = (TextView) view.findViewById(R.id.make);
      model = (TextView) view.findViewById(R.id.model);
      year = (TextView) view.findViewById(R.id.year);
      date = (TextView) view.findViewById(R.id.date);
      this.format = format;
    }

    private void bind(int position, Car car) {
      make.setText(car.getMake());
      model.setText(car.getModel());
      year.setText(Integer.toString(car.getYear()));
      date.setText(format.format(car.getAcquisition()));
    }

  }
}
