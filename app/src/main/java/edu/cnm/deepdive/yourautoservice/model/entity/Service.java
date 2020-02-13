package edu.cnm.deepdive.yourautoservice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;


@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Car.class,
            parentColumns = "car_id",
            childColumns = "car_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Service {

  @ColumnInfo(name = "service_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "car_id", index = true)
  private long car;

  @ColumnInfo(name = "date", index = true)
  private Date date;

  @ColumnInfo(name = "mileage", index = true)
  private long mileage;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCar() {
    return car;
  }

  public void setCar(long car) {
    this.car = car;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public long getMileage() {
    return mileage;
  }

  public void setMileage(long mileage) {
    this.mileage = mileage;
  }
}

