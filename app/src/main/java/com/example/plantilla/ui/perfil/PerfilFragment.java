package com.example.plantilla.ui.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.MainActivity;
import com.example.plantilla.R;
import com.example.plantilla.databinding.ActivityMainBinding;
import com.example.plantilla.databinding.FragmentPerfilBinding;
import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;
import com.google.android.material.navigation.NavigationView;

public class PerfilFragment extends Fragment {


    private PerfilViewModel perfilViewModel;
    private FragmentPerfilBinding binding;
    private MainActivity mainActivity;
    private Button btnEditarPerfil;
    private EditText editTextCodigo,  editTextDni, editTextNombre, editTextApellido, editTextTelefono, editTextEmail, editTextContra ;
    private TextView textViewPerfil, tvImgViewPer, getTextViewPerfil;
    private ImageView ivImagenPerfil;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        init();
        personalizar();
        editar();

        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @SuppressLint("ResourceType")
            @Override
            public void onChanged(Propietario propietario) {
                editTextCodigo.setText(String.format(String.valueOf(propietario.getId())));
                editTextDni.setText(propietario.getDni().toString());
                editTextNombre.setText(propietario.getNombre());
                editTextApellido.setText(propietario.getApellido());
                editTextTelefono.setText(propietario.getTelefono());
                editTextEmail.setText(propietario.getEmail());
                editTextContra.setText(propietario.getContraseña());
                ivImagenPerfil.setImageResource(propietario.getAvatar());
                tvImgViewPer.setText(propietario.getAvatar() + "");
            }
        });

        perfilViewModel.getTextButton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                btnEditarPerfil.setText(s);
            }
        });



        perfilViewModel.getHabilitado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                editTextNombre.setEnabled(aBoolean);
                editTextApellido.setEnabled(aBoolean);
                editTextTelefono.setEnabled(aBoolean);
                editTextEmail.setEnabled(aBoolean);
                editTextContra.setEnabled(aBoolean);
                editTextDni.setEnabled(aBoolean);
            }
        });

        perfilViewModel.cargarPropietario();
        loginHeader(root);
        return root;
    }

    public void loginHeader(View root) {
        MainActivity mainMenu = (MainActivity)  root.getContext();
        NavigationView navigationView =(NavigationView) mainMenu.findViewById(R.id.nav_view);
        final View header = navigationView.getHeaderView(0);
        ApiClient api = ApiClient.getApi();
        perfilViewModel.actPerfil(api.obtenerUsuarioActual());
        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                ImageView avatar = header.findViewById(R.id.imageViewPerfil);
                TextView nombre = header.findViewById(R.id.textViewNombre);
                TextView correo = header.findViewById(R.id.textViewCorreo);
                avatar.setImageResource(propietario.getAvatar());
                nombre.setText(propietario.getNombre()+" "+propietario.getApellido());
                correo.setText(propietario.getEmail());
            }
        });
    }

    private void personalizar(){
        editTextCodigo.setEnabled(false);
        textViewPerfil.setEnabled(false);
        editTextDni.setEnabled(false);
        editTextNombre.setEnabled(false);
        editTextApellido.setEnabled(false);
        editTextTelefono.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextContra.setEnabled(false);
        ivImagenPerfil.setEnabled(false);
        tvImgViewPer.setEnabled(false);
    }

    private void init(){
       // perfilViewModel.cargarPropietario();
        editTextCodigo = binding.editTextCodigoPer;
        textViewPerfil = binding.editTextNomPer;
        editTextDni = binding.editTextDniPer;
        editTextNombre = binding.editTextNomPer;
        editTextApellido = binding.editTextApePer;
        editTextTelefono = binding.editTextTelPer;
        editTextEmail = binding.editTextEmailPer;
        editTextContra = binding.editTextContrasePer;
        ivImagenPerfil = binding.imgViewPer;
        tvImgViewPer = binding.tvImgViewPer;
    }


    private void editar(){
        btnEditarPerfil = binding.btnEditarPerfil;
       final EditText etCodigo = binding.editTextCodigoPer;
        final EditText editTextDni = binding.editTextDniPer;
        final EditText etNombre = binding.editTextNomPer;
        final EditText etApellido = binding.editTextApePer;
        final EditText etTelefono = binding.editTextTelPer;
        final EditText etEmail = binding.editTextEmailPer;
        final EditText etContra = binding.editTextContrasePer;
        final TextView tvImgViewPer = binding.tvImgViewPer;
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = ((Button)view).getText().toString();
                Propietario p = new Propietario(
                        Integer.parseInt(etCodigo.getText().toString()),
                        Long.parseLong(editTextDni.getText().toString()),
                        etNombre.getText().toString(),
                        etApellido.getText().toString(),
                        etEmail.getText().toString(),
                        etContra.getText().toString(),
                        etTelefono.getText().toString(),
                        Integer.parseInt( tvImgViewPer.getText().toString())
                );
                perfilViewModel.cambiarHabilitado(texto, p);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}