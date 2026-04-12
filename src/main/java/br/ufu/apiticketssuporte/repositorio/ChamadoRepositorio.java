package br.ufu.apiticketssuporte.repositorio;

import br.ufu.apiticketssuporte.enumeracao.StatusChamado;
import br.ufu.apiticketssuporte.modelo.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepositorio extends JpaRepository<Chamado, Long> {
    List<Chamado> findByStatus(StatusChamado status);
}