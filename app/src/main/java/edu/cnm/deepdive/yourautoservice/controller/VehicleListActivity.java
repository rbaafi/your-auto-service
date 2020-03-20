package edu.cnm.deepdive.yourautoservice.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.view.VehicleRecyclerAdapter;
import edu.cnm.deepdive.yourautoservice.viewmodel.VehicleViewModel;
import java.util.Calendar;

/**
 * An activity representing a list of Vehicles. This activity has different presentations for
 * handset and tablet-size devices. On handsets, the activity presents a list of items, which when
 * touched, lead to a {@link VehicleDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two vertical panes.
 */
public class VehicleListActivity extends AppCompatActivity {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
   */
  private boolean twoPane;
  private NavController navController;
  private RecyclerView vehicleList;
  private Calendar calendar;
  private VehicleViewModel viewModel;
  private ProgressBar loading;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vehicle_list);
    loading = findViewById(R.id.loading);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

    if (findViewById(R.id.vehicle_detail_container) != null) {
      twoPane = true;
    }

    RecyclerView recyclerView = findViewById(R.id.vehicle_list);
    VehicleViewModel viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
    viewModel.getCars().observe(this, (cars) -> {
      VehicleRecyclerAdapter adapter =
          new VehicleRecyclerAdapter(this, (v, car, pos) -> showService(car), cars);
      recyclerView.setAdapter(adapter);
    });
  }

  private void showService(Car car) {
    if (twoPane) {
      Bundle arguments = new Bundle();
      arguments.putLong(VehicleDetailFragment.ARG_ITEM_ID, car.getId());
      VehicleDetailFragment fragment = new VehicleDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.vehicle_detail_container, fragment)
          .commit();

    } else {
        Intent intent = new Intent(this, VehicleDetailActivity.class);
        intent.putExtra(VehicleDetailFragment.ARG_ITEM_ID, car.getId());

        startActivity(intent);

    }
  }
}
