package io.github.vendasApi.restClientes;

import io.github.vendasApi.model.Cliente;
import io.github.vendasApi.model.Produto;
import io.github.vendasApi.restProdutos.ProdutoFormRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteFormRequest {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate nascimento;
    private String endereco;
    private String telefone;
    private String email;
    private LocalDate cadastro;

    public ClienteFormRequest(){
        super();
    }

    public ClienteFormRequest(Long id, String nome, String cpf, LocalDate nascimento, String endereco, String telefone, String email, LocalDate cadastro) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cadastro = cadastro;
    }


    public Cliente toModel(){
        return new Cliente(id ,nascimento,cpf,nome,endereco,telefone,email,cadastro);}

    public static ClienteFormRequest fromModel(Cliente cliente){
        return new ClienteFormRequest(cliente.getId(), cliente.getNome(),
                cliente.getCpf(), cliente.getNascimento(),
                cliente.getEndereco(), cliente.getEmail(),
                cliente.getTelefone(), cliente.getDataCadastro());
    }
}
