package edu.cnm.deepdive.yourautoservice.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.controller.CarContent.CarItem;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.viewmodel.VehicleViewModel;

/**
 * A fragment representing a single Vehicle detail screen. This fragment is either contained in a
 * {@link VehicleListActivity} in two-pane mode (on tablets) or a {@link VehicleDetailActivity} on
 * handsets.
 */
public class VehicleDetailFragment extends DialogFragment {

  private static final String ID_KEY = "id";

  private long id;
  private View view;
  private EditText subject;
  private EditText text;
  private Car car;
  private VehicleViewModel viewModel;


  public static final String ARG_ITEM_ID = "item_id";

  public static VehicleDetailFragment newInstance(long id) {
    VehicleDetailFragment fragment = new VehicleDetailFragment();
    Bundle args = new Bundle();
    args.putLong(ID_KEY, id);
    fragment.setArguments(args);
    return fragment;
  }

  private CarItem mItem;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
   * screen orientation changes).
   */
  public VehicleDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      id = getArguments().getLong(ID_KEY, 0);
      mItem = CarContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

//      Activity activity = this.getActivity();
//      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity
//          .findViewById(R.id.toolbar_layout);
//      if (appBarLayout != null) {
//        appBarLayout.setTitle(mItem.content);
//      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
//    View rootView = inflater.inflate(R.layout.vehicle_detail, container, false);

    // Show the dummy content as text in a TextView.
//    if (mItem != null) {
//      ((TextView) rootView.findViewById(R.id.vehicle_detail)).setText(mItem.details);
//    }

    return view;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    view = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_detail, null, false);
    subject = view.findViewById(R.id.subject);
    text = view.findViewById(R.id.text);
    return new AlertDialog.Builder(requireContext())
        .setTitle("Type of Vehicle")
        .setView(view)
        .setPositiveButton("DONE", (dlg, which) -> {
          car.setMake(subject.getText().toString());
          car.setModel(text.getText().toString());
          viewModel.save(car);
        })
        .setNegativeButton("UPDATE", (dlg, which) -> { /* Do nothing */})
        .create();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(
        edu.cnm.deepdive.yourautoservice.viewmodel.VehicleViewModel.class);
    viewModel.getCar().observe(getViewLifecycleOwner(), (car) -> {
      this.car = car;
      subject.setText(car.getMake());
      text.setText(car.getModel());
    });
    if (id != 0) {
      viewModel.setCarId(id);
    } else {
      car = new Car();
    }
  }


}
