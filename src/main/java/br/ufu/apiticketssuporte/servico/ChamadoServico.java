package br.ufu.apiticketssuporte.servico;

import br.ufu.apiticketssuporte.enumeracao.StatusChamado;
import br.ufu.apiticketssuporte.excecao.RecursoNaoEncontradoExcecao;
import br.ufu.apiticketssuporte.modelo.Chamado;
import br.ufu.apiticketssuporte.repositorio.ChamadoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadoServico {

    private final ChamadoRepositorio chamadoRepositorio;

    public Chamado criarChamado(Chamado chamado) {
        return chamadoRepositorio.save(chamado);
    }

    public List<Chamado> listarChamados() {
        return chamadoRepositorio.findAll();
    }

    public Chamado buscarChamadoPorId(Long id) {
        return chamadoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Chamado nao encontrado com id: " + id));
    }

    public Chamado atualizarChamado(Long id, Chamado chamadoAtualizado) {
        Chamado chamadoExistente = buscarChamadoPorId(id);

        chamadoExistente.setTitulo(chamadoAtualizado.getTitulo());
        chamadoExistente.setDescricao(chamadoAtualizado.getDescricao());
        chamadoExistente.setNomeSolicitante(chamadoAtualizado.getNomeSolicitante());
        chamadoExistente.setEmailSolicitante(chamadoAtualizado.getEmailSolicitante());
        chamadoExistente.setPrioridade(chamadoAtualizado.getPrioridade());

        return chamadoRepositorio.save(chamadoExistente);
    }

    public Chamado atualizarStatusChamado(Long id, StatusChamado status) {
        Chamado chamadoExistente = buscarChamadoPorId(id);
        chamadoExistente.setStatus(status);
        return chamadoRepositorio.save(chamadoExistente);
    }

    public List<Chamado> listarChamadosPorStatus(StatusChamado status) {
        return chamadoRepositorio.findByStatus(status);
    }
}