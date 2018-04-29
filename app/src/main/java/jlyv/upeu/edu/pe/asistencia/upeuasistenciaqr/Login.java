package jlyv.upeu.edu.pe.asistencia.upeuasistenciaqr;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import jlyv.upeu.edu.pe.asistencia.R;
import jlyv.upeu.edu.pe.asistencia.dao.UsuarioDao;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private UsuarioDao usu;
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE = 777;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
//        signInButton.setSize(SignInButton.SIZE_WIDE);
//        signInButton.setColorScheme(SignInButton.COLOR_DARK);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });


        final EditText txtUsuario =(EditText) findViewById(R.id.idUsuario);
        final EditText txtClave =(EditText) findViewById(R.id.idClave);
//        final Button btnCancelar =(Button)findViewById(R.id.idCancelar);
        final Button btnIngresar=(Button)findViewById(R.id.btnLogin);
//        msg=(TextView)findViewById(R.id.idMsg);
        usu=new UsuarioDao(this);

        btnIngresar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usu.validarUsuario(txtUsuario.getText().toString(), txtClave.getText().toString())){
                    ingresar();
                }else{
//                    msg.setText("Intente Nuevamente...!");
                    Toast.makeText(Login.this, "Intente Nuevamente", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void ingresar(){
        final EditText txtUsuario =(EditText) findViewById(R.id.idUsuario);
        usu=new UsuarioDao(this);
        Cursor curx=usu.listarUsuario(txtUsuario.getText().toString());
        String nombre="";
        int idUsuario=0;
        if(curx.moveToNext()){
            nombre=curx.getString(3)+" "+curx.getString(4);
            idUsuario=curx.getInt(0);
        }
        Intent intent=new Intent();
        intent.putExtra("txtUsuarioNombre", nombre);
        intent.putExtra("idUsuario", idUsuario);
        intent.setClass(this, ActivityPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();
        } else {
            Toast.makeText(this, "No puede ingresar", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, ActivityPrincipal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
