package jlyv.upeu.edu.pe.asistencia.db;

import android.content.Context;

import pe.edu.upeu.dbexterno.ExternalSQLiteOpenHelper;

/**
 * Created by Redes on 23/04/2018.
 */

public class DBExterno extends ExternalSQLiteOpenHelper{


    public DBExterno(Context context) {
        super(context, "asistenciadb.db", null, 1);
    }
}