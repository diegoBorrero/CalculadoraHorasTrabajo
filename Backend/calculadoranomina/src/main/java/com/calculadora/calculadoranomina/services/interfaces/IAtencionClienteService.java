package com.calculadora.calculadoranomina.services.interfaces;

import com.calculadora.calculadoranomina.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAtencionClienteService {

    void guardarAtencionCliente(AtencionClienteDto atencionCliente);
    ReporteHorasDto obtenerReporteHoras(String IdTecnico,Integer semanaAno);
    
}