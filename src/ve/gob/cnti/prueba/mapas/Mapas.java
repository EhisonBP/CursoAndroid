package ve.gob.cnti.prueba.mapas;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import ve.gob.cnti.prueba.R;
import android.app.Activity;
import android.os.Bundle;


public class Mapas extends Activity {

	private MapView mapView;

	// private MapController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapas);
		/**
		 * mapView = (MapView) findViewById(R.id.openmapview);
		 * mapView.setBuiltInZoomControls(true);
		 * mapView.setMultiTouchControls(true); controller =
		 * mapView.getController(); controller.setZoom(5);
		 */
		mapView = new MapView(this, 256);
		mapView.setBuiltInZoomControls(true);
		mapView.setMultiTouchControls(true);

		mapView.getController().setZoom(10);
		mapView.getController().setCenter(new GeoPoint(39.461078, 2.856445));
		setContentView(mapView);
	}

}
