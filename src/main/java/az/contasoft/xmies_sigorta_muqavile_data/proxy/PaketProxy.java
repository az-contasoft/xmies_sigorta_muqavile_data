package az.contasoft.xmies_sigorta_muqavile_data.proxy;

import az.contasoft.xmies_paket.db.entity.Paket;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name = "netflix-zuul-api-gateway-server")
    @RibbonClient(name = "xmies_paket")
public interface PaketProxy {

        @GetMapping("/xmies_paket/searchServices/getPaket/{idPaket}")
        ResponseEntity<Paket> getPaket(@PathVariable("idPaket") long idPaket);
}
