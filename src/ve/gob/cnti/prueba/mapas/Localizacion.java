package ve.gob.cnti.prueba.mapas;

import java.util.List;

import ve.gob.cnti.prueba.R;
import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

public class Localizacion extends Activity implements LocationListener {

	private static final String[] A = { "n/d", "preciso", "Impreciso" };
	private static final String[] P = { "n/d", "bajo", "medio", "alto" };
	private static final String[] E = { "Fuera de Servicio",
			"Tempotalmente no Disponible ", "disponible" };

	private LocationManager manager;
	private TextView textView;
	private String proveedor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proveedor);
		textView = (TextView) findViewById(R.id.salida);

		// Activando metodo que permite la localizacvion de los GPS
		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		log("Provedores de la localizacion GPS: \n");
		mostrarProveedores();

		// Se realiza la ubicacion de los proveedores y se selecciona el mejor
		Criteria criteria = new Criteria();
		proveedor = manager.getBestProvider(criteria, true);
		log("El mejor proveedor es: " + proveedor + "\n\n");

		// Se busca en el dispositivo la ultioma ubicacion conocida
		log("Comenzamos con la ultima localizacion conocida");
		Location location = manager.getLastKnownLocation(proveedor);
		muestraLocalizacion(location);
	}

	@Override
	protected void onResume() {
		super.onResume();
		manager.requestLocationUpdates(proveedor, 10000, 1, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		manager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		log("Nueva lozalizacion encontrada");
		muestraLocalizacion(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		log("Proveedor GPS Habilitado: " + proveedor + "\n");
	}

	@Override
	public void onProviderEnabled(String provider) {
		log("Proveedor GPS deshabilitado: " + proveedor + "\n");
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		log("Cambia estado del proveedor: " + proveedor + ", estado= "
				+ E[Math.max(0, status)] + ", extras= " + extras + "\n");
	}

	private void log(String string) {
		textView.append(string + "\n");
	}

	private void mostrarProveedores() {
		List<String> proList = manager.getAllProviders();
		for (String proString : proList) {
			mostrarProvedor(proString);
		}
	}

	/**
	 * 
	 * @param proString
	 */
	private void mostrarProvedor(String proString) {
		LocationProvider provider = manager.getProvider(proString);
		log("LocationProvider[ Nombre = " + provider.getName()
				+ "Proveedor Activo = " + manager.isProviderEnabled(proString)
				+ "\n" + "Presicion del GPS = "
				+ A[Math.max(0, provider.getAccuracy())] + "\n"
				+ "Requisito de Potencia del GPS = "
				+ P[Math.max(0, provider.getPowerRequirement())] + "\n"
				+ "Costo Monetario del GPS = " + provider.hasMonetaryCost()
				+ "\n" + "Celda Requerida = " + provider.requiresCell() + "\n"
				+ "Red Requerida = " + provider.requiresNetwork() + "\n"
				+ "Satelite Requerido = " + provider.requiresSatellite() + "\n"
				+ "Altitud soportada = " + provider.supportsAltitude() + "\n"
				+ "Rodamiento soportado = " + provider.supportsBearing() + "\n"
				+ "Velocidad Soportada = " + provider.supportsSpeed() + "\n");

	}

	private void muestraLocalizacion(Location location) {
		if (location == null) {
			log("Localizacion desconocida \n");
		} else {
			log(location.toString() + "\n");
		}
	}
}
