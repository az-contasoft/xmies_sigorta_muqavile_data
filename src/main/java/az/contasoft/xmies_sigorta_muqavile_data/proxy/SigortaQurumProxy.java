package az.contasoft.xmies_sigorta_muqavile_data.proxy;

import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "xmies_sigortaqurum")

public interface SigortaQurumProxy {

    @GetMapping("/xmies_sigortaqurum/searchServices/byIdSigortaQurum/{idSigortaQurum}")
    ResponseEntity<SigortaQurum> getByIdSigortaQurum(@PathVariable("idSigortaQurum") long idSigortaQurum);

}

