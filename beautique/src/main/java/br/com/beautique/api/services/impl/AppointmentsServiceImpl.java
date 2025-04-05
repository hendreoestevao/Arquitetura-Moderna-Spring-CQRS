package br.com.beautique.api.services.impl;

import br.com.beautique.api.dtos.AppointmentsDTO;
import br.com.beautique.api.dtos.BeautyProcedureDTO;
import br.com.beautique.api.dtos.CustomerDTO;
import br.com.beautique.api.dtos.FullAppointmentDTO;
import br.com.beautique.api.entities.AppointmentsEntity;
import br.com.beautique.api.entities.BeautyProceduresEntity;
import br.com.beautique.api.entities.CustomerEntity;
import br.com.beautique.api.repositories.AppointmentRepository;
import br.com.beautique.api.repositories.BeautyProcedureRepository;
import br.com.beautique.api.repositories.CustomerRepository;
import br.com.beautique.api.services.AppointmentsService;
import br.com.beautique.api.services.BrokerService;
import br.com.beautique.api.utils.ConverterUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BrokerService  brokerService;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private  final ConverterUtil<AppointmentsEntity, AppointmentsDTO> converterUtil = new ConverterUtil<>(AppointmentsEntity.class, AppointmentsDTO.class);

    @Override
    public AppointmentsDTO create(AppointmentsDTO appointmentsDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentsDTO);
        AppointmentsEntity newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        sendAppointmentQueue(newAppointmentsEntity);
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
        sendAppointmentQueue(updatedAppointmentsEntity);
        return converterUtil.convertToTarget(updatedAppointmentsEntity);
    }

    private void sendAppointmentQueue(AppointmentsEntity appointmentsEntity) {
        CustomerDTO customerDTO = appointmentsEntity.getCustomer() != null ? modelMapper.map(appointmentsEntity.getCustomer(), CustomerDTO.class) : null;
        BeautyProcedureDTO beautyProcedureDTO = appointmentsEntity.getBeautyProcedures() != null ? modelMapper.map(appointmentsEntity.getBeautyProcedures(), BeautyProcedureDTO.class) : null;
        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDTO)
                .build();
                brokerService.send("appointments", fullAppointmentDTO);
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
        sendAppointmentQueue(updatedAppointmentsEntity);
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
