package app.canone.infrastructure.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/api/documents")
class DocumentsController {

    @GetMapping
    fun listar(): Map<String, Any> =
        mapOf(
            "status" to "ok",
            "mensagem" to "Documentos endpoint ativo",
            "timestamp" to Instant.now().toString(),
        )
}