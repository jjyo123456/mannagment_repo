package com.example.mannagment.mannagment.sos_file.request_websocket;


import com.example.mannagment.mannagment.sos_file.ambulance_request.Request_data_class;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RequestController_websocket {

    private final SimpMessagingTemplate messagingTemplate;

    public RequestController_websocket(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAmbulanceAlert(Long hospitalId, Request_data_class dto) {
        messagingTemplate.convertAndSend("/topic/hospital/" + hospitalId, dto);
    }
}

