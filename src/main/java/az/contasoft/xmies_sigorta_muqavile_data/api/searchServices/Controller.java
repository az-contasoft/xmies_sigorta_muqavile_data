package az.contasoft.xmies_sigorta_muqavile_data.api.searchServices;

import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internalServices.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/searchServices")
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Service service;


    public Controller(Service service) {
        this.service = service;
    }


    @GetMapping("/getByIdQurum/{idSigortaQurum}")
    public ResponseEntity<List<SigortaMuqavileData>> getMuqavileByIdQurum(@PathVariable("idSigortaQurum") long idSigortaQurum) {
        logger.info("\n→→→SEARCH_CONTROLLER: getPaketInfoDataByIdPaket\n\n");
        return service.getAllMuqavileByIdQurum(idSigortaQurum);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SigortaMuqavileData>> getSigortaMuqavileData() {
        logger.info("\n→→→SEARCH_CONTROLLER: getSigortaMuqavileData\n\n");
        return service.getAllSigortaMuqavileData();
    }


}