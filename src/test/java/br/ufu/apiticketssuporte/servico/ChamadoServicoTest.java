package br.ufu.apiticketssuporte.servico;

import br.ufu.apiticketssuporte.enumeracao.PrioridadeChamado;
import br.ufu.apiticketssuporte.enumeracao.StatusChamado;
import br.ufu.apiticketssuporte.excecao.RecursoNaoEncontradoExcecao;
import br.ufu.apiticketssuporte.modelo.Chamado;
import br.ufu.apiticketssuporte.repositorio.ChamadoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChamadoServicoTest {

    @Mock
    private ChamadoRepositorio chamadoRepositorio;

    @InjectMocks
    private ChamadoServico chamadoServico;

    @Test
    void deveCriarChamadoComSucesso() {
        Chamado chamado = criarChamadoExemplo();

        when(chamadoRepositorio.save(any(Chamado.class))).thenAnswer(invocacao -> {
            Chamado chamadoSalvo = invocacao.getArgument(0);
            chamadoSalvo.setId(1L);
            return chamadoSalvo;
        });

        Chamado resultado = chamadoServico.criarChamado(chamado);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Erro no login", resultado.getTitulo());
        verify(chamadoRepositorio, times(1)).save(chamado);
    }

    @Test
    void deveLancarExcecaoQuandoChamadoNaoExistir() {
        when(chamadoRepositorio.findById(999L)).thenReturn(Optional.empty());

        RecursoNaoEncontradoExcecao excecao = assertThrows(
                RecursoNaoEncontradoExcecao.class,
                () -> chamadoServico.buscarChamadoPorId(999L)
        );

        assertEquals("Chamado nao encontrado com id: 999", excecao.getMessage());
        verify(chamadoRepositorio, times(1)).findById(999L);
    }

    @Test
    void deveAtualizarStatusDoChamado() {
        Chamado chamado = criarChamadoExemplo();
        chamado.setId(1L);
        chamado.setStatus(StatusChamado.ABERTO);

        when(chamadoRepositorio.findById(1L)).thenReturn(Optional.of(chamado));
        when(chamadoRepositorio.save(any(Chamado.class))).thenAnswer(invocacao -> invocacao.getArgument(0));

        Chamado resultado = chamadoServico.atualizarStatusChamado(1L, StatusChamado.FINALIZADO);

        assertEquals(StatusChamado.FINALIZADO, resultado.getStatus());
        verify(chamadoRepositorio, times(1)).findById(1L);
        verify(chamadoRepositorio, times(1)).save(chamado);
    }

    private Chamado criarChamadoExemplo() {
        Chamado chamado = new Chamado();
        chamado.setTitulo("Erro no login");
        chamado.setDescricao("Usuario nao consegue acessar o sistema.");
        chamado.setNomeSolicitante("Guilherme Castilho");
        chamado.setEmailSolicitante("guilherme@email.com");
        chamado.setPrioridade(PrioridadeChamado.ALTA);
        chamado.setStatus(StatusChamado.ABERTO);
        return chamado;
    }
}