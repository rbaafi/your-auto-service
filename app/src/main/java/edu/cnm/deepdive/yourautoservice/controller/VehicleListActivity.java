package edu.cnm.deepdive.yourautoservice.controller;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import edu.cnm.deepdive.yourautoservice.R;

import edu.cnm.deepdive.yourautoservice.view.VehicleRecyclerAdapter;
import edu.cnm.deepdive.yourautoservice.viewmodel.VehicleViewModel;

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
  private boolean mTwoPane;
  private RecyclerView vehicleList;
  private VehicleViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vehicle_list);

//    Spinner spinner = findViewById(R.id.vehicle_list);


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());

//    vehicleList = findViewById(R.id.vehicle_list);
//    vehicleList.setOnClickListener((parent, view, position, id) -> {
//      long noteId = ((Note) notesList.getItemAtPosition(position)).getId();
//      showDetails(noteId);
//    });
//    notesList.setOnItemLongClickListener((parent, view, position, id) -> {
//      // TODO Pop up a context menu, to allow removal of a Note instance.
//      return true;
//    });


    if (findViewById(R.id.vehicle_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      mTwoPane = true;
    }

    RecyclerView recyclerView = findViewById(R.id.vehicle_list);
    VehicleViewModel viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
    viewModel.getCars().observe(this, (cars) -> {
      VehicleRecyclerAdapter adapter =
          new VehicleRecyclerAdapter(this, (v, car, pos) -> {
            Toast.makeText(
                this, String.format("%s clicked", car.getModel()), Toast.LENGTH_LONG).show();
          }, cars, mTwoPane);
      recyclerView.setAdapter(adapter);
    });
  }

}
