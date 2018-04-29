package jlyv.upeu.edu.pe.asistencia.upeuasistenciaqr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jlyv.upeu.edu.pe.asistencia.R;
import jlyv.upeu.edu.pe.asistencia.dao.EventoDao;
import jlyv.upeu.edu.pe.asistencia.to.EventoTO;
import jlyv.upeu.edu.pe.asistencia.utils.EventoRecyclerAdapter;

public class ReportFragment extends Fragment {

    public EventoDao dao;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<EventoViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewMyFragment =inflater.inflate(R.layout.fragment_report, container, false);

        this.recyclerView=(RecyclerView)viewMyFragment.findViewById(R.id.recyclerview);

        this.layoutManager=new LinearLayoutManager(this.getContext());
        dao=new EventoDao(this.getContext());
        List<EventoTO> lista=dao.ListarEvento();
        this.adapter=new EventoRecyclerAdapter(lista);

        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);

        return viewMyFragment;



    }
}
