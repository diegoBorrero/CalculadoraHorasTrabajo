package com.calculadora.calculadoranomina.models;

import javax.persistence.*;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;



@Getter @Setter	
@Entity
@Table(name = "atencioncliente")
public class AtencionClienteModel {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true, nullable= false)
	private long id;
	
	@Column(nullable= false)
	private String idtecnico;
	@Column(nullable= false)
    private String idservicio;
	@Column(nullable= false)
    private LocalDateTime   fechainicio;
	@Column(nullable= false)
	private LocalDateTime   fechafin;
	@Column(nullable= false)
	private Integer semanadeano;

	AtencionClienteModel(){

	}

	public AtencionClienteModel(String idtecnico,String idservicio,LocalDateTime fechainicio,LocalDateTime fechafin){
		this.setIdTecnico(idtecnico);
		this.setIdServicio(idservicio);
		this.setFechaInicio(fechainicio);
		this.setFechaFin(fechafin);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdTecnico() {
		return this.idtecnico;
	}

	public void setIdTecnico(String idTecnico) {
		this.idtecnico = idTecnico;
	}

	public String getIdServicio() {
		return this.idservicio;
	}

	public void setIdServicio(String idServicio) {
		this.idservicio = idServicio;
	}

	public LocalDateTime   getFechaInicio() {
		return this.fechainicio;
	}

	public void setFechaInicio(LocalDateTime   fechaInicio) {
		this.fechainicio = fechaInicio;
	}

	public LocalDateTime   getFechaFin() {
		return this.fechafin;
	}

	public void setFechaFin(LocalDateTime   fechaFin) {
		this.fechafin = fechaFin;
	}

	public Integer getSemanaDeAno() {
		return this.semanadeano;
	}

	public void setSemanaDeAno(Integer semanaDeAno) {
		this.semanadeano = semanaDeAno;
	}

}