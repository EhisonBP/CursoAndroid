package ve.gob.cnti.prueba;

import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.os.Bundle;

public class Mapas extends Activity {

	private MapView mapView;
	private MapController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapas);
		mapView = (MapView) findViewById(R.id.openmapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setMultiTouchControls(true);
		controller = mapView.getController();
		controller.setZoom(4);
	}

}
