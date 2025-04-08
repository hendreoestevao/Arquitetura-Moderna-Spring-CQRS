package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import br.com.ms_beautique_query.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    ResponseEntity<List<FullAppointmentDTO>> listAppointments() {
        return ResponseEntity.ok(appointmentService.listAllApointments());
    }

    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<FullAppointmentDTO>> listAppointmentsByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(appointmentService.listAllApointmentsByCustomerId(customerId));
    }

    @GetMapping("/beauty-procedure/{beautyProcedureId}")
    ResponseEntity<List<FullAppointmentDTO>> lsitAppointmentsByBeautyProcedure(@PathVariable("beautyProcedureId") Long beautyProcedureId) {
        return ResponseEntity.ok(appointmentService.listAllApointmentsByBeautyProcedureId(beautyProcedureId));
    }
}
