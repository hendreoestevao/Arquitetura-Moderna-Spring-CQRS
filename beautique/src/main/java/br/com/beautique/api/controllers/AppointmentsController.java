package br.com.beautique.api.controllers;


import br.com.beautique.api.dtos.AppointmentsDTO;
import br.com.beautique.api.services.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentsService appointmentsService;

    @PostMapping
    ResponseEntity<AppointmentsDTO> create(@RequestBody AppointmentsDTO appointmentsDTO) {
        return ResponseEntity.ok(appointmentsService.create(appointmentsDTO));
    }

    @PatchMapping
    ResponseEntity<AppointmentsDTO> update(@RequestBody AppointmentsDTO appointmentsDTO) {
        return ResponseEntity.ok(appointmentsService.update(appointmentsDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id) {
        appointmentsService.delete(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    ResponseEntity<AppointmentsDTO> setCustomerToAppointments(@RequestBody AppointmentsDTO appointmentsDTO) {
        return ResponseEntity.ok(appointmentsService.setCustomerToAppointment(appointmentsDTO));
    }
}
