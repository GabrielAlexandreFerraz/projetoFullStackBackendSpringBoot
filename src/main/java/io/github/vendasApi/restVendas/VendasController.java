package io.github.vendasApi.restVendas;

import io.github.vendasApi.model.Venda;
import io.github.vendasApi.repository.ItemVendaRepository;
import io.github.vendasApi.repository.VendaRepository;
import io.github.vendasApi.RelatorioVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class VendasController {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private RelatorioVendasService relatorioVendasService;

    @PostMapping
    @Transactional
    public void realizarVenda(@RequestBody Venda venda){
            repository.save(venda);
            venda.getItens().stream().forEach(iv -> iv.setVenda(venda));
            itemVendaRepository.saveAll(venda.getItens());
    }

    @GetMapping("relatorio-vendas")
    public ResponseEntity<byte[]> relatorioVendas(){
        byte[] relatorioGerado= relatorioVendasService.gerarRelatorio();
        HttpHeaders headers = new HttpHeaders();
        var fileName = "relatorioVendas.pdf";

        headers.setContentDispositionFormData("inline; filename=\"" + fileName + "\"",fileName);
       headers.setCacheControl("must-revalidade, post-check=0, pre-check=0");
       var responseEntity = new ResponseEntity<>(relatorioGerado,headers, HttpStatus.OK);
       return responseEntity;
    }

}
