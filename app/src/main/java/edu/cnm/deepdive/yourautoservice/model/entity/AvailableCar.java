package edu.cnm.deepdive.yourautoservice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AvailableCar {

  @ColumnInfo(name = "available_car_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "make_id", index = true)
  private String make;

  @ColumnInfo(name = "model_id", index = true)
  private String model;

  @ColumnInfo(name = "year_id", index = true)
  private int year;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
