//package jlyv.upeu.edu.pe.asistencia.upeuasistenciaqr;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import jlyv.upeu.edu.pe.asistencia.R;
//import jlyv.upeu.edu.pe.asistencia.dao.AsistenciaDao;
//import jlyv.upeu.edu.pe.asistencia.dao.EventoDao;
//
//
//public class MainQRActivity extends AppCompatActivity {
//
//    TextView resultado;
//    Button escaner;
//    Button btnRegAsist;
//    private ZXingScannerView Vistaescaner;
//    EventoDao eve;
//    AsistenciaDao asis;
//    int idUsuario=0;
//    Context cotex;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_qr);
//        btnRegAsist=(Button)findViewById(R.id.idBtnRegistrar);
//        escaner=(Button)findViewById(R.id.idBtnReg2);
//        this.cotex=this;
//        btnRegAsist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {mostrarCamara(); }
//        });
//        Bundle bu=getIntent().getExtras();
//        String dato=bu.getString("txtUsuarioNombre");
//        idUsuario=bu.getInt("idUsuario");
//        resultado=(TextView)findViewById(R.id.idResultado);
//        resultado.setText(dato);
//        SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed=sp.edit();
//        ed.putInt("value",idUsuario);
//        ed.putString("valNombre", dato);
//        ed.commit();
//    }
//
//    public void mostrarCamara(){
//        Intent inte=new Intent("com.google.zxing.client.android.SCAN");
//        inte.putExtra("SCAN_MODE","QR_CODE_MODE");
//        startActivityForResult(inte,0);
//    }
//
//    public void onResultado(View view){
//        asis=new AsistenciaDao(this);
//        Cursor cur=asis.listarAsistencia();
//        if(cur.moveToNext()){
//            resultado = (TextView) findViewById(R.id.idResultado);
//            resultado.setText(String.valueOf(cur.getCount()));
//        }
//    }
//
//    public void onRest(View view){
//        Intent inte=new Intent();
//        inte.setClass(this,MainRestActivity.class);
//        startActivity(inte);
//    }
//
//    public  void onListarasitencia(View view){
//        Intent inte=new Intent();
//        inte.setClass(this,ListarAsistenciaActivity.class);
//        startActivity(inte);
//    }
//
//    public void onRegistrar(View view){
//        Vistaescaner = new ZXingScannerView(this);
//        Vistaescaner.setResultHandler(new zxingscanner());
//        setContentView(Vistaescaner);
//        Log.v("DATOS------:", String.valueOf(idUsuario));
//        Vistaescaner.startCamera();
//    }
//
//    public void onActivarevento(View view){
//        Intent intent=new Intent();
//        //intent.putExtra("idUsuario", idUsuario);
//        intent.setClass(this, ActivarEventoActivity.class);
//        startActivity(intent);
//        //finish();
//    }
//
//    public void scanerQR(View view){
//        Intent intent=new Intent();
//        intent.putExtra("idUsuario", idUsuario);
//        intent.setClass(this, SimpleScannerActivity.class);
//        startActivity(intent);
//    }
//    public void onActivityResult(int requescode, int resulcode, Intent intent){
//        if(resulcode==RESULT_OK){
//        String contenido=intent.getStringExtra("SCAN_RESULT");
//        resultado=(TextView)findViewById(R.id.idResultado);
//        resultado.setText(contenido.toString());
//        super.onActivityResult(requescode,resulcode,intent);
//        mostrarCamara();
//        }
//    }
//
//    class zxingscanner implements ZXingScannerView.ResultHandler{
//        @Override
//        public void handleResult(Result result) {
//            String dato = result.getText();
//            eve=new EventoDao(cotex);
//            asis=new AsistenciaDao(cotex);
//            int idEvento=eve.eventoActivo();
//            asis.registrarAsistencia(idEvento, idUsuario,dato,dato,dato);
//            setContentView(R.layout.activity_main_qr);
//            Vistaescaner.stopCamera();
//
//
//            resultado = (TextView) findViewById(R.id.idResultado);
//            resultado.setText(dato);
//        }
//    }
//
//}
