package br.com.ms_beautique_query.services;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;

import java.util.List;

public interface AppointmentService {

    List<FullAppointmentDTO> listAllApointments();
    List<FullAppointmentDTO> listAllApointmentsByCustomerId(Long customerId);
    List<FullAppointmentDTO> listAllApointmentsByBeautyProcedureId(Long beautyProcedureId);

}
