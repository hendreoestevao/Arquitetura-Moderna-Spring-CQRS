package br.com.beautique.api.services.impl;

import br.com.beautique.api.dtos.AppointmentsDTO;
import br.com.beautique.api.entities.AppointmentsEntity;
import br.com.beautique.api.entities.BeautyProceduresEntity;
import br.com.beautique.api.entities.CustomerEntity;
import br.com.beautique.api.repositories.AppointmentRepository;
import br.com.beautique.api.repositories.BeautyProcedureRepository;
import br.com.beautique.api.repositories.CustomerRepository;
import br.com.beautique.api.services.AppointmentsService;
import br.com.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private  final ConverterUtil<AppointmentsEntity, AppointmentsDTO> converterUtil = new ConverterUtil<>(AppointmentsEntity.class, AppointmentsDTO.class);
    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AppointmentsDTO create(AppointmentsDTO appointmentsDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentsDTO);
        AppointmentsEntity newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(newAppointmentsEntity);
    }

    @Override
    public AppointmentsDTO update(AppointmentsDTO appointmentsDTO) {
        Optional<AppointmentsEntity> currentAppointment = appointmentRepository.findById(appointmentsDTO.getId());
        if(currentAppointment.isEmpty()){
            throw  new RuntimeException("Appointment not found");
        }

        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentsDTO);
        appointmentsEntity.setCreatedAt(currentAppointment.get().getCreatedAt());
        AppointmentsEntity updatedAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(updatedAppointmentsEntity);
    }

    @Override
    public void delete(Long id) {
        AppointmentsEntity appointmentsEntity = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointmentsEntity);
    }

    @Override
    public AppointmentsDTO setCustomerToAppointment(AppointmentsDTO appointmentsDTO) {
        CustomerEntity customerEntity = findCustomerById(appointmentsDTO.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(appointmentsDTO.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findAppointmentById(appointmentsDTO.getId());
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedures(beautyProceduresEntity);
        appointmentsEntity.setAppointmentsOpen(false);

        AppointmentsEntity updatedAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return  buildAppointmentsDTO(updatedAppointmentsEntity);
    }

    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return  beautyProcedureRepository.findById(id).orElseThrow(() -> new RuntimeException("Beauty procedure not found"));
    }

    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private AppointmentsDTO buildAppointmentsDTO(AppointmentsEntity appointmentsEntity) {
        return AppointmentsDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedures().getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .build();
    }
}
