package com.calculadora.calculadoranomina.models;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtencionClienteDto {
    
   
    @NotEmpty(message = "El Codigo idTecnico no debe ser vacio")
    private String idtecnico;

    @NotEmpty(message = "El idServicio no debe ser vacio")
    private String idservicio;

    @NotNull(message = "la fechainicio no debe ser vacio")
    private LocalDateTime fechainicio;
    
    @NotNull(message = "la  fechafin no debe ser vacio")
    private LocalDateTime fechafin;


    public String getidtecnico() {
        return this.idtecnico;
    }

    public void setidtecnico(String idTecnico) {
        this.idtecnico = idTecnico;
    }

    public String getidservicio() {
        return this.idservicio;
    }

    public void setidservicio(String idServicio) {
        this.idservicio = idServicio;
    }

    public LocalDateTime getfechainicio() {
		return this.fechainicio;
	}

    public void setfechainicio(LocalDateTime fechaInicio) {
		this.fechainicio = fechaInicio;
    }
    public LocalDateTime getfechafin() {
		return this.fechafin;
	}

    public void setfechafin(LocalDateTime fechaFin) {
		this.fechafin = fechaFin;
	}
    
}