package br.ufu.apiticketssuporte.servico;

import br.ufu.apiticketssuporte.enumeracao.PrioridadeChamado;
import br.ufu.apiticketssuporte.enumeracao.StatusChamado;
import br.ufu.apiticketssuporte.modelo.Chamado;
import br.ufu.apiticketssuporte.modelo.Comentario;
import br.ufu.apiticketssuporte.repositorio.ComentarioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComentarioServicoTest {

    @Mock
    private ComentarioRepositorio comentarioRepositorio;

    @Mock
    private ChamadoServico chamadoServico;

    @InjectMocks
    private ComentarioServico comentarioServico;

    @Test
    void deveAdicionarComentarioAoChamado() {
        Chamado chamado = criarChamadoExemplo();
        chamado.setId(1L);

        Comentario comentario = new Comentario();
        comentario.setAutor("Equipe de Suporte");
        comentario.setTexto("Chamado recebido para analise.");

        when(chamadoServico.buscarChamadoPorId(1L)).thenReturn(chamado);
        when(comentarioRepositorio.save(any(Comentario.class))).thenAnswer(invocacao -> {
            Comentario comentarioSalvo = invocacao.getArgument(0);
            comentarioSalvo.setId(10L);
            return comentarioSalvo;
        });

        Comentario resultado = comentarioServico.adicionarComentario(1L, comentario);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("Equipe de Suporte", resultado.getAutor());
        assertNotNull(resultado.getChamado());
        assertEquals(1L, resultado.getChamado().getId());

        verify(chamadoServico, times(1)).buscarChamadoPorId(1L);
        verify(comentarioRepositorio, times(1)).save(comentario);
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