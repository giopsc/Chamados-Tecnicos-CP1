package br.com.fiap.springpjchamadostecnicos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ENDERECO")
    @SequenceGenerator(
            name = "SQ_ENDERECO",
            sequenceName = "SQ_ENDERECO",
            allocationSize = 1
    )
    @Column(name = "ID_ENDERECo")
    private Long id;

    @Column(name = "CEP_ENDERECO")
    private String cep;

    @Column(name = "NUM_ENDERECO")
    private String numero;

    @Column(name = "CPL_ENDERECO")
    private String complemento;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "SOLICITANTE",
            referencedColumnName = "ID_SOLICITANTE",
            foreignKey = @ForeignKey(
                    name = "FK_SOLICITANTE_ENDERECO"
            )
    )
    private Solicitante solicitante;

}
