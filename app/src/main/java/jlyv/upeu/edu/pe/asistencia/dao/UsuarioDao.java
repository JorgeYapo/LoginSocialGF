package jlyv.upeu.edu.pe.asistencia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import jlyv.upeu.edu.pe.asistencia.db.DBExterno;

/**
 * Created by Docente on 6/02/2018.
 */

public class UsuarioDao extends DBExterno {
    DBExterno con;
    Context contex;
    SQLiteDatabase db;
    Cursor cur;
    String sql;

    public UsuarioDao(Context context) {
        super(context);
        this.contex=context;
    }

    public boolean validarUsuario(String usuario, String clave){
        con=new DBExterno(contex);
        db=con.getReadableDatabase();
        sql=" select * from usuario where usuario='"+usuario+"' and clave='"+clave+"'";
        cur=db.rawQuery(sql, null);
        if(cur.getCount()==1){
            return true;
        }else{
        return false;
        }
    }

    public Cursor listarUsuario(String usuario){
        con=new DBExterno(contex);
        db=con.getReadableDatabase();
        sql=" select * from usuario where usuario='"+usuario+"'";
        cur=db.rawQuery(sql, null);
        return cur;
    }


}
