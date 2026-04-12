package br.ufu.apiticketssuporte.modelo;

import br.ufu.apiticketssuporte.enumeracao.PrioridadeChamado;
import br.ufu.apiticketssuporte.enumeracao.StatusChamado;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chamados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo e obrigatorio.")
    @Size(max = 150, message = "O titulo deve ter no maximo 150 caracteres.")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "A descricao e obrigatoria.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "O nome do solicitante e obrigatorio.")
    @Size(max = 100, message = "O nome do solicitante deve ter no maximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nomeSolicitante;

    @NotBlank(message = "O email do solicitante e obrigatorio.")
    @Email(message = "Informe um email valido.")
    @Size(max = 120, message = "O email deve ter no maximo 120 caracteres.")
    @Column(nullable = false, length = 120)
    private String emailSolicitante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusChamado status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrioridadeChamado prioridade;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void antesDeSalvar() {
        this.dataAbertura = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();

        if (this.status == null) {
            this.status = StatusChamado.ABERTO;
        }
    }

    @PreUpdate
    public void antesDeAtualizar() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}