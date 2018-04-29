package jlyv.upeu.edu.pe.asistencia.upeuasistenciaqr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import jlyv.upeu.edu.pe.asistencia.R;
import jlyv.upeu.edu.pe.asistencia.to.EventoTO;

public class EventoViewHolder extends RecyclerView.ViewHolder {

    private TextView txtNombre,txtFecha,txtHora;

    private EventoTO eventoTO;

    public EventoViewHolder(View itemView) {
        super(itemView);

        this.txtNombre=(TextView)itemView.findViewById(R.id.txtNombre);
        this.txtFecha=(TextView)itemView.findViewById(R.id.txtFecha);
        this.txtHora=(TextView)itemView.findViewById(R.id.txtHora);
    }

    public void setEvento(EventoTO eventoy){

        this.eventoTO=eventoy;
        this.txtNombre.setText(eventoTO.getNombreevento());
        this.txtFecha.setText(eventoTO.getFecha());
        this.txtHora.setText(eventoTO.getHorainicio());
    }
}
