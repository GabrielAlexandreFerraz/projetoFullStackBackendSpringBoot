package io.github.vendasApi;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioVendasService {

        @Value("classpath:reports/relatorioVendas.jasper")
    private Resource relatorioVendasCompilado;

    @Autowired
    private DataSource dataSource;

    public byte[] gerarRelatorio(){
        try (
                Connection connection = dataSource.getConnection();
        ){
            Map<String, Object> parametros = new HashMap<>();
              JasperPrint print = JasperFillManager.fillReport(relatorioVendasCompilado.getInputStream(), parametros, connection);

              return JasperExportManager.exportReportToPdf(print);

        } catch (SQLException e){
            e.printStackTrace();
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
