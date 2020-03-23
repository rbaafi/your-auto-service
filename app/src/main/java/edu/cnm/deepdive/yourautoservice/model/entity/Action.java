package edu.cnm.deepdive.yourautoservice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import edu.cnm.deepdive.yourautoservice.model.History;

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
  private long serviceId;

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

  public long getServiceId() {
    return serviceId;
  }

  public void setServiceId(long serviceId) {
    this.serviceId = serviceId;
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


  @Override
  public int compareTo(Action other) {
    return summary.compareToIgnoreCase(other.summary);
  }
}
