package io.github.vendasApi.dashboard;

import io.github.vendasApi.repository.ClienteRepository;
import io.github.vendasApi.repository.ProdutoRepository;
import io.github.vendasApi.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private VendaRepository vendas;
    @Autowired
    private ClienteRepository clientes;
    @Autowired
    private ProdutoRepository produtos;


    @GetMapping
    public DashboardData getDashBoard(){
        Long vendasCount = vendas.count();
        Long clientesCount = clientes.count();
        Long produtosCount = produtos.count();

        var anoCorrente = LocalDate.now().getYear();
        var vendasPorMes = vendas.obterSomatoriaVendasPorMes(anoCorrente);

        return new DashboardData(produtosCount,clientesCount,vendasCount,vendasPorMes);

    }
}
