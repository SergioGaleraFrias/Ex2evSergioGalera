package com.example.ex2evsergiogalera.Fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ex2evsergiogalera.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class MapaFragment extends Fragment {

    private MapView map = null;
    private IMapController mapController;
    private MyLocationNewOverlay myLocationOverlay;
    private GeoPoint geoPoint;

    public MapaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        // Configuración de osmdroid
        Context ctx = requireActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(requireActivity().getPackageName());

        map = view.findViewById(R.id.mapview);

        // Inicializar myLocationOverlay antes de usarlo
        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx), map);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableFollowLocation();
        map.getOverlays().add(myLocationOverlay);
        geoPoint = new GeoPoint( 43.201717254077124, -3.0481097240203905);

        setupMap();

        return view;
    }

    private void setupMap() {
        // Configuración del mapa
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(18.0);

        //Poner centro de las Coordenadas
        mapController.setCenter(geoPoint);

        map.setUseDataConnection(true);

        // Llamar a la función para agregar el marcador en las coordenadas especificadas
        addMarker(geoPoint.getLatitude(), geoPoint.getLongitude());

    }

    private void addMarker(double latitude, double longitude) {
        // Crear un marcador en las coordenadas especificadas
        org.osmdroid.views.overlay.Marker marker = new org.osmdroid.views.overlay.Marker(map);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);

        //Sirve para que cuando clicke sobre el Marker, lleve a "InformacionFragment"
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                // Lleva a InformacionFragment
                InformacionFragment informacionFragment = new InformacionFragment();
                FragmentTransaction transaction1 = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFragment, informacionFragment);
                transaction1.commit();

                return false;
            }
        });


        // Agregar el marcador al mapa
        map.getOverlays().add(marker);
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }
}