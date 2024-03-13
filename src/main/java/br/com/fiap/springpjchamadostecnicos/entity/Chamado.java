package br.com.fiap.springpjchamadostecnicos.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_CHAMADO")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CHAMADO")
    @SequenceGenerator(
            name = "SQ_CHAMADO",
            sequenceName = "SQ_CHAMADO",
            allocationSize = 1
    )
    @Column(name = "ID_CHAMADO")
    private Long id;

    @Column(name = "TL_CHAMADO")
    private String titulo;

    @Column(name = "DS_CHAMADO")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ESPECIALIDADE",
            referencedColumnName = "ID_ESPECIALIDADE",
            foreignKey = @ForeignKey(
                    name = "FK_CHAMADO_ESPECIALIDADE"
            )
    )
    private Especialidade especialidade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "TECNICO",
            referencedColumnName = "ID_TECNICO",
            foreignKey = @ForeignKey(
                    name = "FK_TECNICO_CHAMADO"
            )
    )
    private Tecnico tecnico;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "SOLICITANTE",
            referencedColumnName = "ID_SOLICITANTE",
            foreignKey = @ForeignKey(
                    name = "FK_SOLICITANTE_CHAMADO"
            )
    )
    private Solicitante solicitante;

    @Column(name = "DT_ABERTURA_CHAMADO")
    private LocalDateTime dataAbertura;

    @Column(name = "DT_PRIMEIRO_ATENDIMENTO_CHAMADO")
    private LocalDateTime dataPrimeiroAtendimento;

    @Column(name = "DT_ENCERRAMENTO_CHAMADO")
    private LocalDateTime dataEncerramento;

}
