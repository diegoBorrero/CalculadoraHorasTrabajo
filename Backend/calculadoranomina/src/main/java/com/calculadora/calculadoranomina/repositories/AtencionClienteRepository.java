package com.calculadora.calculadoranomina.repositories;

import java.util.List;

import com.calculadora.calculadoranomina.models.AtencionClienteModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AtencionClienteRepository extends JpaRepository<AtencionClienteModel, Long>
{
   
    List<AtencionClienteModel> findByIdtecnicoAndSemanadeano(String idtecnico,Integer semanadeano);
    
}