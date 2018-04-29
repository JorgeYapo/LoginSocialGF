//package jlyv.upeu.edu.pe.asistencia.upeuasistenciaqr;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ListView;
//
//import upeu.edu.pe.dao.AsistenciaDao;
//import upeu.edu.pe.utils.ListaAsisAdapter;
//
//public class ListarAsistenciaActivity extends AppCompatActivity {
//
//    AsistenciaDao dao;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_listar_asistencia);
//        ListView lista=(ListView)findViewById(R.id.idListaAsis);
//        dao=new AsistenciaDao(this);
//
//        ListaAsisAdapter adapter=new ListaAsisAdapter(this,dao.listarAsistenciaArray());
//        lista.setAdapter(adapter);
//    }
//}
