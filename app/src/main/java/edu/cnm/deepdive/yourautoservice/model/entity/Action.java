package edu.cnm.deepdive.yourautoservice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import com.google.gson.annotations.SerializedName;

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

  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String summary;

  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String description;

  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String ServiceType;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getService() {
    return service;
  }

  public void setService(long service) {
    this.service = service;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getServiceType() {
    return ServiceType;
  }

  public void setServiceType(String serviceType) {
    ServiceType = serviceType;
  }
}