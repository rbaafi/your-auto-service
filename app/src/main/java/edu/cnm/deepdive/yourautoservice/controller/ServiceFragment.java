package edu.cnm.deepdive.yourautoservice.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.viewmodel.VehicleViewModel;
import java.io.Serializable;
import java.util.Date;

public class ServiceFragment extends DialogFragment {

  private Car car;
  private Service service;
  private EditText editMileage;
  private View dialogView;
  private VehicleViewModel viewModel;

  public static ServiceFragment newInstance(long serviceId) {
    ServiceFragment fragment = new ServiceFragment();
    Bundle args = new Bundle();
    args.putLong("service_id", serviceId);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return dialogView;
  }

  @NonNull
  @Override
  public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_service, null);
//    editMileage = dialogView.findViewById(R.id.edit_mileage);
    service = (Service) getArguments().getSerializable("service");
    if (service == null) {
      service = new Service();
    } else {
      editMileage.setText(Integer.toString((int) service.getMileage()));

    }
    return new Builder(getContext())
        .setTitle("Add Service")
        .setView(dialogView)
        .setNegativeButton("Cancel", (dialog, button) -> {})
        .setPositiveButton("Save", (dialog, button) -> saveService())
        .create();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  private void saveService() {
    Date date = new Date();
    int mileage = Integer.parseInt(editMileage.getText().toString());
    service.setMileage(mileage);
    service.setDate(date);
    ((ServiceSaver) getActivity()).save(service);
  }

  public interface ServiceSaver {

    void save(Service service);
  }
}

