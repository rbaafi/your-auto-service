package edu.cnm.deepdive.yourautoservice.content;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.yourautoservice.R;
import edu.cnm.deepdive.yourautoservice.content.CarContent.CarItem;
import edu.cnm.deepdive.yourautoservice.model.entity.Car;

/**
 * A fragment representing a single Vehicle detail screen. This fragment is either contained in a
 * {@link VehicleListActivity} in two-pane mode (on tablets) or a {@link VehicleDetailActivity} on
 * handsets.
 */
public class VehicleDetailFragment<MainViewModel> extends Fragment {

  private static final String ID_KEY = "id";

  private long id;
  private View view;
  private EditText subject;
  private EditText text;
  private Car note;
  private MainViewModel viewModel;


  public static final String ARG_ITEM_ID = "item_id";

  /**
   * The dummy content this fragment is presenting.
   */
  private CarItem mItem;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the fragment (e.g. upon
   * screen orientation changes).
   */
  public VehicleDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      // Load the dummy content specified by the fragment
      // arguments. In a real-world scenario, use a Loader
      // to load content from a content provider.
      mItem = CarContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

      Activity activity = this.getActivity();
      CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity
          .findViewById(R.id.toolbar_layout);
      if (appBarLayout != null) {
        appBarLayout.setTitle(mItem.content);
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.vehicle_detail, container, false);

    // Show the dummy content as text in a TextView.
    if (mItem != null) {
      ((TextView) rootView.findViewById(R.id.vehicle_detail)).setText(mItem.details);
    }

    return rootView;
  }
}
