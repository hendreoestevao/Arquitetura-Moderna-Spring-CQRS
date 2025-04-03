package br.com.beautique.api.services;

import br.com.beautique.api.dtos.AppointmentsDTO;

public interface AppointmentsService {

    AppointmentsDTO create(AppointmentsDTO appointmentsDTO);
    AppointmentsDTO update(AppointmentsDTO appointmentsDTO);
    void delete(Long id);
    AppointmentsDTO setCustomerToAppointment(AppointmentsDTO appointmentsDTO);
}
