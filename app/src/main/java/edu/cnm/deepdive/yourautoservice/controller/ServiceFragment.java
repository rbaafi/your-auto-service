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
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.controller.DateTimePickerFragment.Mode;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.model.entity.Service;
import edu.cnm.deepdive.yourautoservice.viewmodel.ServiceViewModel;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ServiceFragment extends DialogFragment
    implements DateTimePickerFragment.OnChangeListener {

  private static final String SERVICE_ID_KEY = "service_id";
  private static final String CAR_ID_KEY = "car_id";

  private DateFormat dateFormat;
  private NumberFormat numberFormat;
  private Car car;
  private Calendar calendar;
  private long carId;
  private long serviceId;
  private Service service;
  private EditText mileage;
  private EditText date;
  private View dialogView;
  private ServiceViewModel viewModel;

  public static ServiceFragment newInstance(long carId, long serviceId) {
    ServiceFragment fragment = new ServiceFragment();
    Bundle args = new Bundle();
    args.putLong(SERVICE_ID_KEY, serviceId);
    args.putLong(CAR_ID_KEY, carId);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
    numberFormat = NumberFormat.getNumberInstance();
    calendar = Calendar.getInstance();
    return dialogView;
  }

  @NonNull
  @Override
  public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    if (getArguments() != null) {
      carId = getArguments().getLong(CAR_ID_KEY, 0);
      serviceId = getArguments().getLong(SERVICE_ID_KEY, 0);
    }
    dialogView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_service, null, false);
    mileage = dialogView.findViewById(R.id.mileage);
    date = dialogView.findViewById(R.id.date);
    date.setOnClickListener((v) -> {
      DateTimePickerFragment fragment = DateTimePickerFragment.createInstance(Mode.DATE, calendar);
      fragment.show(getChildFragmentManager(), fragment.getClass().getName());
    });
    return new Builder(getContext())
        .setTitle("Add Service")
        .setView(dialogView)
        .setNegativeButton("Cancel", (dialog, button) -> {})
        .setPositiveButton("Save", (dialog, button) -> save())
        .create();
  }



  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
    if (serviceId != 0) {
      viewModel.getService().observe(getViewLifecycleOwner(), (service) -> {
        this.service = service;
        calendar.setTime(service.getDate());
        date.setText(dateFormat.format(calendar.getTime()));
        mileage.setText(numberFormat.format(service.getMileage()));
      });
      viewModel.setServiceId(serviceId);
    } else {
      service = new Service();
      service.setCarId(carId);
      calendar.setTime(new Date());
      date.setText(dateFormat.format(calendar.getTime()));
      mileage.setText("0");
    }

    viewModel.setCarId(carId);

  }

  private void save() {
    try {
      service.setMileage(numberFormat.parse(mileage.getText().toString().trim()).longValue());
    } catch (ParseException e) {
      service.setMileage(Long.parseLong(mileage.getText().toString().trim()));
    }
    service.setDate(calendar.getTime());
    viewModel.save(service);
  }

  @Override
  public void onChange(Calendar calendar) {
    this.calendar = calendar;
    date.setText(dateFormat.format(calendar.getTime()));
  }
}

