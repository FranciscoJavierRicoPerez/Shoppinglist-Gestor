package es.franricodev.shopping_list_gestor_service.sse.controller;

import es.franricodev.shopping_list_gestor_service.sse.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://192.168.18.7:5173",
}, allowCredentials = "true")
@RequestMapping("api/v1/events")
@RestController
public class EventSseController {
    @Autowired
    private EventService eventService;

    // Conexión SSE
    @GetMapping(
            value = "",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter streamEvents() {
        return eventService.registrarCliente();
    }

    // Endpoint para enviar mensaje desde cliente
    @PostMapping("/send")
    public void enviarMensaje(@RequestBody String mensaje) {
        eventService.enviarEventoATodos(mensaje);
    }
}
