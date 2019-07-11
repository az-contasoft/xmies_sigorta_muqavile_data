package az.contasoft.xmies_sigorta_muqavile_data.proxy;

import az.contasoft.xmies_paket_data.api.searchServices.internal.PaketData;
import az.contasoft.xmies_xidmetler_data.api.searchServices.internal.XidmetlerData;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name = "netflix-zuul-api-gateway-server")
    @RibbonClient(name = "xmies_paket_data")
public interface PaketDataProxy {

        @GetMapping("/xmies_paket_data/searchServices/getPaketData/{idPaket}")
        ResponseEntity<PaketData> getPaketData(@PathVariable("idPaket") long idPaket);


}
