package edu.cnm.deepdive.yourautoservice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import com.google.gson.annotations.Expose;
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
public class Action implements History, Comparable<Action> {

  @ColumnInfo(name = "action_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "service_id", index = true)
  private long service;

  @ColumnInfo(collate = ColumnInfo.NOCASE, index = true)
  private String summary;

  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String description;

  @ColumnInfo(collate = ColumnInfo.NOCASE, index = true)
  private String serviceType;


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
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Service getServices() {
    return null;
  }

  @Override
  public int compareTo(Action other) {
    return summary.compareToIgnoreCase(other.summary);
  }
}
