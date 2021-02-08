package com.calculadora.calculadoranomina.services.implementation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import com.calculadora.calculadoranomina.models.*;
import com.calculadora.calculadoranomina.repositories.*;
import com.calculadora.calculadoranomina.services.interfaces.IAtencionClienteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AtencionClienteService implements IAtencionClienteService{
    @Autowired
    AtencionClienteRepository atencionClienteRepository;

    @Override
    public void guardarAtencionCliente(AtencionClienteDto atencionCliente){
        
        AtencionClienteModel atencionClienteModel = new AtencionClienteModel(atencionCliente.getidtecnico(),atencionCliente.getidservicio(),atencionCliente.getfechainicio(),atencionCliente.getfechafin());
        
            if(this.calcularDias(atencionClienteModel.getFechaInicio(),atencionClienteModel.getFechaFin())==0)
            {

                Integer semanaAno = this.calcularSemanaAno(atencionClienteModel.getFechaInicio());
                atencionClienteModel.setSemanaDeAno(semanaAno);
                this.atencionClienteRepository.save(atencionClienteModel);
            }
            else{
                AtencionClienteModel atencionClienteModelNew= new AtencionClienteModel(
                    atencionClienteModel.getIdTecnico(),atencionClienteModel.getIdServicio(),
                    atencionClienteModel.getFechaInicio(),atencionClienteModel.getFechaFin());

                Integer semanaAno = this.calcularSemanaAno(atencionClienteModel.getFechaInicio());
                atencionClienteModelNew.setSemanaDeAno(semanaAno);
                LocalDateTime fechaInicial = atencionClienteModel.getFechaInicio();
                LocalDateTime fechaFinDia =  LocalDateTime.of(fechaInicial.getYear(), fechaInicial.getMonthValue(), fechaInicial.getDayOfMonth(),
                23, 59, 59);
                LocalDateTime fechaDiaSiguiente = LocalDateTime.of(fechaInicial.getYear(), fechaInicial.getMonthValue(), fechaInicial.getDayOfMonth(),
                0, 0, 0);
                fechaDiaSiguiente=fechaDiaSiguiente.plusDays(1);
                
                atencionClienteModelNew.setFechaFin(fechaFinDia);
                this.atencionClienteRepository.save(atencionClienteModelNew);
                AtencionClienteDto atencionClienteDiaSiguiente = new AtencionClienteDto();
                atencionClienteDiaSiguiente.setidtecnico(atencionCliente.getidtecnico());
                atencionClienteDiaSiguiente.setidservicio(atencionCliente.getidservicio());
                atencionClienteDiaSiguiente.setfechainicio(fechaDiaSiguiente);
                atencionClienteDiaSiguiente.setfechafin(atencionCliente.getfechafin());
                this.guardarAtencionCliente(atencionClienteDiaSiguiente);
                
            
            }
         

    }

    public ReporteHorasDto obtenerReporteHoras(String idTecnico,Integer semanaAno){
        
        return this.calcularHorasTrabajadas(this.obtenerHorasTrabajadas(idTecnico,semanaAno),idTecnico,semanaAno);
    }


    public ReporteHorasDto calcularHorasTrabajadas(List<AtencionClienteModel> diasTrabajados,String idTecnico,Integer semanaAno)
    {
        double horasTotales=0;
        double horasTotalesNormales=0;
        double horasNocturnas=0;
        double horasDominicales=0;
        double totalHorasNoDominicales=0;
        double horasLegalesTrabajo=48.00;
        double horasNormalesExtra=0;
        double horasNocturnasExtra=0;
        double horasDominicalesExtra=0;
     
        
        ListIterator iter = diasTrabajados.listIterator(diasTrabajados.size());
        while (iter.hasPrevious()){
            AtencionClienteModel atencionCliente= (AtencionClienteModel)iter.previous();
            if(!calcularDiaSemanaDomingo(atencionCliente.getFechaInicio()))
            {
                horasNocturnas=horasNocturnas+this.calcularHorasNocturnas(atencionCliente.getFechaInicio(),atencionCliente.getFechaFin());
                totalHorasNoDominicales = totalHorasNoDominicales+this.calcularTotalHorasNoDominicales(atencionCliente.getFechaInicio(),atencionCliente.getFechaFin());
            }
            else{
                horasDominicales=horasDominicales+this.calcularHorasDominicales(atencionCliente.getFechaInicio(),atencionCliente.getFechaFin());
            }
        }
        horasTotalesNormales=totalHorasNoDominicales-horasNocturnas;
        horasTotales = totalHorasNoDominicales+horasDominicales;

        if(horasTotales>=horasLegalesTrabajo)
        {
            if(horasTotalesNormales>horasLegalesTrabajo)
            {
                horasNormalesExtra=horasTotalesNormales-horasLegalesTrabajo;
                horasLegalesTrabajo=0;
            }
            else {
                horasLegalesTrabajo=horasLegalesTrabajo-horasNormalesExtra;
            }
            if(horasNocturnas>horasLegalesTrabajo)
            {
                horasNocturnasExtra= horasNocturnas-horasLegalesTrabajo;
                horasLegalesTrabajo=0;
            }
            else{
                horasLegalesTrabajo=horasLegalesTrabajo-horasNocturnas;
            }
            if(horasDominicales>horasLegalesTrabajo)
            {
                horasDominicalesExtra=horasDominicales-horasLegalesTrabajo;
                horasLegalesTrabajo=0;
            }
            else{
                horasLegalesTrabajo=horasLegalesTrabajo-horasDominicales;
            }
            
        }
        ReporteHorasDto reporteHoras= new ReporteHorasDto(idTecnico,semanaAno,(int)horasTotalesNormales,(int)horasNocturnas,(int)horasDominicales,(int)horasNormalesExtra,(int)horasNocturnasExtra,(int)horasDominicalesExtra);
        return reporteHoras;
    }

    public double calcularHorasNocturnas(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        double horasNoturnas=0;
        Long minutos;
        
        LocalDateTime fechaInicioHoraNormal =  LocalDateTime.of(fechaInicial.getYear(), fechaInicial.getMonthValue(), fechaInicial.getDayOfMonth(),
                07, 00, 00);
        LocalDateTime fechaFinHoraNormal =  LocalDateTime.of(fechaInicial.getYear(), fechaInicial.getMonthValue(), fechaInicial.getDayOfMonth(),
                20, 00, 00);
        
        if(fechaInicial.isBefore(fechaInicioHoraNormal))
        {
            Duration duration = Duration.between(fechaInicial, fechaInicioHoraNormal);
 
            minutos = duration.toMinutes();
            horasNoturnas= ((double)minutos/60);
        }
        if(fechaFinHoraNormal.isBefore(fechaFinal))
        {
            Duration duration = Duration.between(fechaFinHoraNormal, fechaFinal);
 
            minutos = duration.toMinutes();
            horasNoturnas= horasNoturnas+ ((double)minutos/60);
        }
        
        return horasNoturnas;
    }

    public double calcularHorasDominicales(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
  
        Long minutos;

        Duration duration = Duration.between(fechaInicial, fechaFinal);
        minutos = duration.toMinutes();
        
        return ((double)minutos/60);
    }

    public double calcularTotalHorasNoDominicales(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        Long minutos;

        Duration duration = Duration.between(fechaInicial, fechaFinal);
        minutos = duration.toMinutes();
      
     
        return ((double)minutos/60);
        
    }

    public List<AtencionClienteModel>obtenerHorasTrabajadas(String idtecnico,Integer semanadeano){
        return atencionClienteRepository.findByIdtecnicoAndSemanadeano(idtecnico, semanadeano);
    }

    public Long calcularDias(LocalDateTime fechaInicial, LocalDateTime fechaFinal){
        Duration duration = Duration.between(fechaInicial, fechaFinal);
        return duration.toDays();
    }

    public boolean calcularDiaSemanaDomingo(LocalDateTime fecha) {
        String diaSemana= LocalDate.of( fecha.getYear() , fecha.getMonth() , fecha.getDayOfMonth() ).getDayOfWeek()                       
        .getDisplayName(                      
            TextStyle.SHORT_STANDALONE , 
            Locale.US);
        switch(diaSemana)
        {
            case "Sun" :
            return true;
            
            default :
            return false;
        }
    }

    public Integer calcularSemanaAno(LocalDateTime fecha)
    {
        int ano = fecha.getYear();
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        return LocalDate.of( ano , mes , dia ).get ( IsoFields.WEEK_OF_WEEK_BASED_YEAR );
    }
}