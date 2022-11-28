package com.example.Doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class DoctorController {
    @KafkaListener(topics = "Telemetry",groupId = "group_id")
    public void ObservationConsumer(String message) {

        System.out.println("message = " + message);}
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    private static final String TOPIC3 = "Prescription";
    @GetMapping("Prescription")
    public String PrescriptionProducer(@RequestBody String message) {
        kafkaTemplate.send("Prescription", message);

        return message+" : physical exam completed";
    }



}
