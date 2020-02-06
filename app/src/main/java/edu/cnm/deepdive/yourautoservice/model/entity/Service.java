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
}

