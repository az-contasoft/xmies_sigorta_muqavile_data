package az.contasoft.xmies_sigorta_muqavile_data.proxy;

import az.contasoft.xmies_xidmetler.db.entity.Xidmetler;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "xmies_xidmetler")
public interface XidmetlerProxy {

    @GetMapping("/xmies_xidmetler/searchServices/getXidmetler/{idXidmetler}")
    ResponseEntity<Xidmetler> getXidmetler(@PathVariable("idXidmetler") long idXidmetler);

}