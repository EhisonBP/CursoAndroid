package ve.gob.cnti.prueba;

import ve.gob.cnti.prueba.mapas.Localizacion;
import ve.gob.cnti.prueba.mapas.Mapas;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FullscreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);

		Button mapView = (Button) findViewById(R.id.Boton02);
		mapView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lanzarMapView();
			}
		});

		Button gpsView = (Button) findViewById(R.id.Boton01);
		gpsView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
				Boolean flash = manager
						.isProviderEnabled(LocationManager.GPS_PROVIDER);
				if (!flash) {
					Log.i("GPS", "El GPS se encuentra activado: " + flash);
				} else {
					lanzarGPS();
				}
			}
		});
	}

	public void lanzarMapView() {
		Intent i = new Intent(this, Mapas.class);
		startActivity(i);
	}

	public void lanzarGPS() {
		Intent i = new Intent(this, Localizacion.class);
		startActivity(i);
	}
}
