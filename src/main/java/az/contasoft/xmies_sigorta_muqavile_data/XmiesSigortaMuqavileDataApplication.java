package az.contasoft.xmies_sigorta_muqavile_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableDiscoveryClient
@EnableFeignClients("az.contasoft.xmies_sigorta_muqavile_data.proxy")
@SpringBootApplication
public class XmiesSigortaMuqavileDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmiesSigortaMuqavileDataApplication.class, args);
    }

}
