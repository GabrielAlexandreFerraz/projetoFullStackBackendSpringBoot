package io.github.vendasApi.model;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome",length = 100)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @Column(name = "sku",length = 200)
    private String sku;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    public Produto(Long id, String nome, String descricao, BigDecimal preco, String sku) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.sku = sku;
    }
    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
