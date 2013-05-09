package ve.gob.cnti.prueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	}

	public void lanzarMapView() {
		Intent i = new Intent(this, Mapas.class);
		startActivity(i);
	}
}
