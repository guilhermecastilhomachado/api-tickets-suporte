package br.ufu.apiticketssuporte.repositorio;

import br.ufu.apiticketssuporte.modelo.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

    List<Comentario> findByChamadoIdOrderByDataCriacaoAsc(Long chamadoId);
}