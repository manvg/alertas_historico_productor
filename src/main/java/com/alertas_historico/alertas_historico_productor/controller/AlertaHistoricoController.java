package com.alertas_historico.alertas_historico_productor.controller;

import com.alertas_historico.alertas_historico_productor.model.SignosVitales;
import com.alertas_historico.alertas_historico_productor.service.RabbitMQService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historial")
public class AlertaHistoricoController {

    private final RabbitMQService rabbitMQService;

    public AlertaHistoricoController(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarSignosVitales(@RequestBody SignosVitales signosVitales) {
        rabbitMQService.enviarHistorial(signosVitales);
        return ResponseEntity.ok("Registro histórico enviado: " + signosVitales);
    }
}

