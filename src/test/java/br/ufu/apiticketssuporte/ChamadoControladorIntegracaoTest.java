package br.ufu.apiticketssuporte;

import br.ufu.apiticketssuporte.enumeracao.PrioridadeChamado;
import br.ufu.apiticketssuporte.modelo.Chamado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
class ChamadoControladorIntegracaoTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;

    @BeforeEach
    void configurarMockMvc() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void deveCadastrarEListarChamados() throws Exception {
        Chamado chamado = new Chamado();
        chamado.setTitulo("Erro de acesso");
        chamado.setDescricao("Usuario nao consegue acessar o sistema.");
        chamado.setNomeSolicitante("Guilherme Castilho");
        chamado.setEmailSolicitante("guilherme@email.com");
        chamado.setPrioridade(PrioridadeChamado.ALTA);

        mockMvc.perform(post("/chamados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chamado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.titulo").value("Erro de acesso"))
                .andExpect(jsonPath("$.status").value("ABERTO"));

        mockMvc.perform(get("/chamados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Erro de acesso"));
    }

    @Test
    void deveRetornarErroDeValidacaoAoCadastrarChamadoInvalido() throws Exception {
        String jsonInvalido = """
                {
                  "titulo": "",
                  "descricao": "",
                  "nomeSolicitante": "",
                  "emailSolicitante": "email-invalido",
                  "prioridade": "ALTA"
                }
                """;

        mockMvc.perform(post("/chamados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erro").value("Erro de validacao"))
                .andExpect(jsonPath("$.campos.titulo").exists())
                .andExpect(jsonPath("$.campos.descricao").exists())
                .andExpect(jsonPath("$.campos.nomeSolicitante").exists())
                .andExpect(jsonPath("$.campos.emailSolicitante").exists());
    }
}