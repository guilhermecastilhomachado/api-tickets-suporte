package br.ufu.apiticketssuporte.controlador;

import br.ufu.apiticketssuporte.enumeracao.StatusChamado;
import br.ufu.apiticketssuporte.modelo.Chamado;
import br.ufu.apiticketssuporte.servico.ChamadoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
@RequiredArgsConstructor
public class ChamadoControlador {

    private final ChamadoServico chamadoServico;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Chamado criarChamado(@RequestBody @Valid Chamado chamado) {
        return chamadoServico.criarChamado(chamado);
    }

    @GetMapping
    public List<Chamado> listarChamados() {
        return chamadoServico.listarChamados();
    }

    @GetMapping("/{id}")
    public Chamado buscarChamadoPorId(@PathVariable Long id) {
        return chamadoServico.buscarChamadoPorId(id);
    }

    @PutMapping("/{id}")
    public Chamado atualizarChamado(@PathVariable Long id, @RequestBody @Valid Chamado chamado) {
        return chamadoServico.atualizarChamado(id, chamado);
    }

    @PatchMapping("/{id}/status")
    public Chamado atualizarStatusChamado(@PathVariable Long id, @RequestParam StatusChamado status) {
        return chamadoServico.atualizarStatusChamado(id, status);
    }

    @GetMapping("/status/{status}")
    public List<Chamado> listarChamadosPorStatus(@PathVariable StatusChamado status) {
        return chamadoServico.listarChamadosPorStatus(status);
    }
}