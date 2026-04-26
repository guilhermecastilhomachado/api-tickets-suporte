package br.ufu.apiticketssuporte.controlador;

import br.ufu.apiticketssuporte.modelo.Comentario;
import br.ufu.apiticketssuporte.servico.ComentarioServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados/{chamadoId}/comentarios")
@RequiredArgsConstructor
public class ComentarioControlador {

    private final ComentarioServico comentarioServico;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comentario adicionarComentario(@PathVariable Long chamadoId,
                                          @RequestBody @Valid Comentario comentario) {
        return comentarioServico.adicionarComentario(chamadoId, comentario);
    }

    @GetMapping
    public List<Comentario> listarComentariosPorChamado(@PathVariable Long chamadoId) {
        return comentarioServico.listarComentariosPorChamado(chamadoId);
    }
}