package com.calculadora.calculadoranomina.models;

public class ReporteHorasDto {
    private String idTecnico;
    private Integer semanaAno;
    private Integer horasNormales;
    private Integer horasNocturnas;
    private Integer horasDominicales;
    private Integer horasNormalesExtras;
    private Integer horasNocturnasExtras;
    private Integer horasDominicalesExtras;

   public  ReporteHorasDto(String idTecnico,Integer semanaAno,Integer horasNormales,Integer horasNocturnas,Integer horasDominicales,Integer horasNormalesExtras,Integer horasNocturnasExtras,Integer horasDominicalesExtras){
        this.setIdTecnico(idTecnico);
        this.setSemanaAno(semanaAno);
        this.setHorasNormales(horasNormales);
        this.setHorasNocturnas(horasNocturnas);
        this.setHorasDominicales(horasDominicales);
        this.setHorasNormalesExtras(horasNormalesExtras);
        this.setHorasNocturnasExtras(horasNocturnasExtras);
        this.setHorasDominicalesExtras(horasDominicalesExtras);
    }
    public String getIdTecnico() {
        return this.idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public Integer getSemanaAno() {
        return this.semanaAno;
    }

    public void setSemanaAno(Integer semanaAno) {
        this.semanaAno = semanaAno;
    }

    public Integer getHorasNormales() {
        return this.horasNormales;
    }

    public void setHorasNormales(Integer horasNormales) {
        this.horasNormales = horasNormales;
    }

    public Integer getHorasNocturnas() {
        return this.horasNocturnas;
    }

    public void setHorasNocturnas(Integer horasNocturnas) {
        this.horasNocturnas = horasNocturnas;
    }

    public Integer getHorasDominicales() {
        return this.horasDominicales;
    }

    public void setHorasDominicales(Integer horasDominicales) {
        this.horasDominicales = horasDominicales;
    }

    public Integer getHorasNormalesExtras() {
        return this.horasNormalesExtras;
    }

    public void setHorasNormalesExtras(Integer horasNormalesExtras) {
        this.horasNormalesExtras = horasNormalesExtras;
    }

    public Integer getHorasNocturnasExtras() {
        return this.horasNocturnasExtras;
    }

    public void setHorasNocturnasExtras(Integer horasNocturnasExtras) {
        this.horasNocturnasExtras = horasNocturnasExtras;
    }

    public Integer getHorasDominicalesExtras() {
        return this.horasDominicalesExtras;
    }

    public void setHorasDominicalesExtras(Integer horasDominicalesExtras) {
        this.horasDominicalesExtras = horasDominicalesExtras;
    }
    
}