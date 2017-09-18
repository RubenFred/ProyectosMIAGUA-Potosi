package com.info.fred.pc3.proyectosmiagua_potosi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pc3 on 14/09/2017.
 */

public class UnidadProyecto {

    @SerializedName("nomb_proy")
    private String nombreproy;
    private String departamento;
    @SerializedName("cod_mun")
    private String codmunicipio;
    private String municipio;

    @SerializedName("tipo_proy")
    private String tipoproy;

    @SerializedName("monto_fps_en_bs")
    private String montofps;
    @SerializedName("monto_contrap_en _bs")
    private String montocontraparte;


    public String getNombreproy() {
        return nombreproy;
    }

    public void setNombreproy(String nombreproy) {
        this.nombreproy = nombreproy;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodmunicipio() {
        return codmunicipio;
    }

    public void setCodmunicipio(String codmunicipio) {
        this.codmunicipio = codmunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTipoproy() {
        return tipoproy;
    }

    public void setTipoproy(String tipoproy) {
        this.tipoproy = tipoproy;
    }

    public String getMontofps() {
        return montofps;
    }

    public void setMontofps(String montofps) {
        this.montofps = montofps;
    }

    public String getMontocontraparte() {
        return montocontraparte;
    }

    public void setMontocontraparte(String montocontraparte) {
        this.montocontraparte = montocontraparte;
    }
}
