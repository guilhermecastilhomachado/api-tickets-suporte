package br.ufu.apiticketssuporte.servico;

import br.ufu.apiticketssuporte.modelo.Chamado;
import br.ufu.apiticketssuporte.modelo.Comentario;
import br.ufu.apiticketssuporte.repositorio.ComentarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioServico {

    private final ComentarioRepositorio comentarioRepositorio;
    private final ChamadoServico chamadoServico;

    public Comentario adicionarComentario(Long chamadoId, Comentario comentario) {
        Chamado chamado = chamadoServico.buscarChamadoPorId(chamadoId);

        comentario.setId(null);
        comentario.setChamado(chamado);

        return comentarioRepositorio.save(comentario);
    }

    public List<Comentario> listarComentariosPorChamado(Long chamadoId) {
        chamadoServico.buscarChamadoPorId(chamadoId);
        return comentarioRepositorio.findByChamadoIdOrderByDataCriacaoAsc(chamadoId);
    }
}