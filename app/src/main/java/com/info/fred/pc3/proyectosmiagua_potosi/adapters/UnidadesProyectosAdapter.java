package com.info.fred.pc3.proyectosmiagua_potosi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info.fred.pc3.proyectosmiagua_potosi.R;
import com.info.fred.pc3.proyectosmiagua_potosi.model.UnidadProyecto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc3 on 14/09/2017.
 */

public class UnidadesProyectosAdapter extends RecyclerView.Adapter<UnidadesProyectosAdapter.DeviceViewHolder> {

    private Context context;
    private List<UnidadProyecto> dataset;
    private OnUnidadProyectoSelectedListener onUnidadProyectoSelectedListener;

    public UnidadesProyectosAdapter(Context context, OnUnidadProyectoSelectedListener listener){
        this.dataset = new ArrayList<>();
        this.context = context;
        this.onUnidadProyectoSelectedListener = listener;
    }
    

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unidad_proyecto,parent,false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        UnidadProyecto u = dataset.get(position);
        
        holder.nombreTextview.setText(u.getNombreproy());
        holder.tipoProyTextview.setText("Proyecto de: "+u.getTipoproy());
        holder.lugarTextview.setText("Municipio: "+u.getMunicipio());
        holder.setDeviceSelectedListener(u, onUnidadProyectoSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder{
        View cardView;

        TextView nombreTextview;
        TextView tipoProyTextview;
        TextView lugarTextview;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview);

            nombreTextview = (TextView)itemView.findViewById(R.id.nombreTextView);
            tipoProyTextview = (TextView)itemView.findViewById(R.id.tipo_proyTextView);
            lugarTextview = (TextView)itemView.findViewById(R.id.lugarTextView);
        }
        
        public void setDeviceSelectedListener(final UnidadProyecto departamento, final OnUnidadProyectoSelectedListener onUnidadProyectoSelectedListener){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUnidadProyectoSelectedListener.onUnidadProyectoSelected(departamento);
                }
            });    
        }
        
    }

    public  void add(UnidadProyecto unidadProyecto){
        dataset.add(unidadProyecto);
        notifyDataSetChanged();
    }

    public  void setDataset(List<UnidadProyecto> unidadProyectos){
        if(unidadProyectos == null){
            dataset = new ArrayList<>();
        }else{
            dataset = unidadProyectos;
        }
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }

    public interface OnUnidadProyectoSelectedListener{
        void onUnidadProyectoSelected(UnidadProyecto departamento);
        //void OnUnidadProyectoSelected(UnidadProyecto departamento);
        //void onUnidadProyectoSelectedListener(UnidadProyecto departamento);
    }
}
