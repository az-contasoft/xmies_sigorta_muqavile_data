package az.contasoft.xmies_sigorta_muqavile_data.proxy;

import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


@FeignClient(name = "netflix-zuul-api-gateway-server")
    @RibbonClient(name = "xmies_sigorta_muqavile")

public interface SigortaMuqavileProxy {

        @GetMapping("/xmies_sigorta_muqavile/searchServices/list")
        ResponseEntity<Map<Long,SigortaMuqavile>> getSigortaMuqavile();
    }


