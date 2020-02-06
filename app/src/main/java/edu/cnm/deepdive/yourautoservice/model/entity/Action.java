package edu.cnm.deepdive.yourautoservice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Service.class,
            parentColumns = "service_id",
            childColumns = "service_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Action {

  @ColumnInfo(name = "action_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "service_id", index = true)
  private long service;

  @ColumnInfo(name = "summary")
  private String summary;

  @ColumnInfo(name = "description", index = true)
  private String description;

  @ColumnInfo(name = "service_type", index = true)
  private Enum service_type;

}
