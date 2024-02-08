package com.example.ex2evsergiogalera.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ex2evsergiogalera.R;


//Fragment donde se muestra toda la información del lugar que ha sido seleccionado previamente
public class InformacionFragment extends Fragment {

    public InformacionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_informacion, container, false);

        Button buttonVolver = view.findViewById(R.id.buttonVolver);

        //Boton para volver al mapa
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(requireContext(), "Parece que no pero ya estoy mejor, Lo cuento por el chisme jajajja", Toast.LENGTH_SHORT).show();
                
                // Lleva a InformacionFragment
                MapaFragment mapaFragment = new MapaFragment();
                FragmentTransaction transaction1 = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedorFragment, mapaFragment);
                transaction1.commit();

            }
        });

        return view;
    }
}