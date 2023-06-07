package io.github.vendasApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item_venda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @Column
    private Integer quantidade;

    @Override
    public String toString() {
        return "ItemVenda{" +
                "id=" + id +
                ", venda=" + venda +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
