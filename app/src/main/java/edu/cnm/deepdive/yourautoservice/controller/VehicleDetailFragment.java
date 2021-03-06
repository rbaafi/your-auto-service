package edu.cnm.deepdive.yourautoservice.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.view.HistoryRecyclerAdapter;
import edu.cnm.deepdive.yourautoservice.viewmodel.ServiceViewModel;

public class VehicleDetailFragment extends Fragment {

  public static final String ID_KEY = "id";

  private RecyclerView historyList;
  private ServiceViewModel viewModel;
  private long carId;
  private FloatingActionButton addService;

  public static VehicleDetailFragment newInstance(long id) {
    VehicleDetailFragment fragment = new VehicleDetailFragment();
    Bundle args = new Bundle();
    args.putLong(ID_KEY, id);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      carId = getArguments().getLong(ID_KEY, 0);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.vehicle_detail, container, false);
    historyList = root.findViewById(R.id.history_list);
    addService = root.findViewById(R.id.add_service);
    addService.setOnClickListener((v) -> {
      ServiceFragment fragment = ServiceFragment.newInstance(carId, 0);
      fragment.show(getParentFragmentManager(), fragment.getClass().getName());
    });
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
    viewModel.setCarId(carId);
    viewModel.getHistories().observe(getViewLifecycleOwner(), (histories) -> {
      HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(getContext(), histories);
      historyList.setAdapter(adapter);
    });
  }
}
