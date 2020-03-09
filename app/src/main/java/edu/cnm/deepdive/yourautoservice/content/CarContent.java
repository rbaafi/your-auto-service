package edu.cnm.deepdive.yourautoservice.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by Android template
 * wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CarContent {

  /**
   * An array of sample (car) items.
   */
  public static final List<CarItem> ITEMS = new ArrayList<CarItem>();

  /**
   * A map of sample (car) items, by ID.
   */
  public static final Map<String, CarItem> ITEM_MAP = new HashMap<String, CarItem>();

  private static final int COUNT = 5;

  static {
    // Add some sample items.
    for (int i = 1; i <= COUNT; i++) {
      addItem(createCarItem(i));
    }
  }

  private static void addItem(CarItem item) {
    ITEMS.add(item);
    ITEM_MAP.put(item.id, item);
  }

  private static CarItem createCarItem(int position) {
    return new CarItem(String.valueOf(position), "Item " + position, makeDetails(position));
  }

  private static String makeDetails(int position) {
    StringBuilder builder = new StringBuilder();
    builder.append("Details about Item: ").append(position);
    for (int i = 0; i < position; i++) {
      builder.append("\nMore details information here.");
    }
    return builder.toString();
  }

  /**
   * A dummy item representing a piece of content.
   */
  public static class CarItem {

    public final String id;
    public final String content;
    public final String details;

    public CarItem(String id, String content, String details) {
      this.id = id;
      this.content = content;
      this.details = details;
    }

    @Override
    public String toString() {
      return content;
    }
  }
}
