package edu.cnm.deepdive.yourautoservice.controller;


import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.controller.DateTimePickerFragment.Mode;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;
import edu.cnm.deepdive.yourautoservice.viewmodel.VehicleViewModel;
import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends DialogFragment
    implements TextWatcher, DateTimePickerFragment.OnChangeListener {

  private static final String ID_KEY = "id";

  private long id;
  private View view;
  private AutoCompleteTextView make;
  private AutoCompleteTextView model;
  private EditText year;
  private EditText acquisition;
  private VehicleViewModel viewModel;
  private Car car;
  private Date date;
  private DateFormat format;


  public static CarFragment createInstance(long id) {
    CarFragment fragment = new CarFragment();
    Bundle args = new Bundle();
    args.putLong(ID_KEY, id);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
//    return inflater.inflate(R.layout.fragment_car, container, false);
    return view;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_car, null, false);
    make = view.findViewById(R.id.make);
    make.addTextChangedListener(this);
    model = view.findViewById(R.id.model);
    year = view.findViewById(R.id.year);
    acquisition = view.findViewById(R.id.acquisition);
    acquisition.setOnClickListener((v) -> {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      DateTimePickerFragment fragment = DateTimePickerFragment.createInstance(Mode.DATE, cal);
      fragment.show(getChildFragmentManager(), fragment.getClass().getName());
    });
    format = android.text.format.DateFormat.getDateFormat(getContext());
    return new AlertDialog.Builder(getContext())
        .setTitle("Select your Car")
        .setView(view)
        .setPositiveButton("OK", (dlg, which) -> {
          car.setMake(make.getText().toString());
          car.setModel(model.getText().toString());
          car.setYear(Integer.parseInt(year.getText().toString()));
          car.setAcquisition(date);
          viewModel.save(car);
        })
        .setNegativeButton("Cancel", (dlg, which) -> { /* Do nothing */})
        .create();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(VehicleViewModel.class);
    viewModel.getCar().observe(getViewLifecycleOwner(), (car) -> {
      this.car = car;
      make.setText(car.getMake());
      model.setText(car.getModel());
      year.setText(Integer.toString(car.getYear()));
      setDate(car.getAcquisition());
    });
    viewModel.getMakes().observe(getViewLifecycleOwner(), (makes) -> {
      ArrayAdapter<String> adapter =
          new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, makes);
      make.setAdapter(adapter);
    });
    viewModel.getModels().observe(getViewLifecycleOwner(), (models) -> {
      ArrayAdapter<String> adapter =
          new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, models);
      model.setAdapter(adapter);
    });
    if (id != 0) {
      viewModel.setCarId(id);
    } else {
      car = new Car();
      setDate(new Date());
    }
  }

  @Override
  public void onChange(Calendar calendar) {
    setDate(calendar.getTime());
  }

  protected void setDate(Date date) {
    this.date = date;
    acquisition.setText(format.format(date));
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    viewModel.setMake(s.toString());
  }
}
