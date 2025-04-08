package br.com.ms_beautique_query.services.impl;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import br.com.ms_beautique_query.repositories.AppointmentRepository;
import br.com.ms_beautique_query.services.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<FullAppointmentDTO> listAllApointments() {
        try{
            return appointmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllApointmentsByCustomerId(Long customerId) {
        try{
            return appointmentRepository.findByAppointmentByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FullAppointmentDTO> listAllApointmentsByBeautyProcedureId(Long beautyProcedureId) {
        try{
            return appointmentRepository.findByAppointmentByBeautyProcedureId(beautyProcedureId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
