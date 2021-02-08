package com.calculadora.calculadoranomina.controllers;

import com.calculadora.calculadoranomina.models.AtencionClienteDto;
import com.calculadora.calculadoranomina.models.ReporteHorasDto;
import com.calculadora.calculadoranomina.services.implementation.AtencionClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/atencion")
public class AtencionClienteController {
    
    @Autowired
    AtencionClienteService atencionClienteService;
    
    
    @RequestMapping(value="/guardar", method = RequestMethod.POST,produces={"application/json"})
    public @ResponseBody Boolean guardarAtencionCliente(@RequestBody  @Valid AtencionClienteDto atencionClientedto)
    {
        if(atencionClientedto.getfechainicio().isBefore(atencionClientedto.getfechafin()))
        {
            this.atencionClienteService.guardarAtencionCliente(atencionClientedto);
            return true;
        }
        else return false;
        
    }

    @CrossOrigin
    @RequestMapping(value="/{idtecnico}&{semanaAno}", method = RequestMethod.GET,produces={"application/json"})
    public @ResponseBody ReporteHorasDto obtenerReporteHoras(@PathVariable("idtecnico") String idtecnico,@PathVariable("semanaAno") Integer semanaAno)
    {
        
        return this.atencionClienteService.obtenerReporteHoras(idtecnico,semanaAno);    
    }
}