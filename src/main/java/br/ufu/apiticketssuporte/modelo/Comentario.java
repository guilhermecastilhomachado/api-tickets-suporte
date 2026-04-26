package br.ufu.apiticketssuporte.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O autor do comentario e obrigatorio.")
    @Size(max = 100, message = "O autor deve ter no maximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String autor;

    @NotBlank(message = "O texto do comentario e obrigatorio.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id", nullable = false)
    @JsonIgnore
    private Chamado chamado;

    @PrePersist
    public void antesDeSalvar() {
        this.dataCriacao = LocalDateTime.now();
    }
}