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
        logger.info("\n→→→SEARCH_CONTROLLER: getMuqavileByIdQurum\n\n");
        return service.getAllMuqavileByIdQurum(idSigortaQurum);
    }
//
//    @GetMapping("/sigortaAdi/{name}")
//    public ResponseEntity<List<SigortaMuqavileData>> getMuqavileByQurumAdi(@PathVariable("name") String name) {
//        logger.info("\n→→→SEARCH_CONTROLLER: getMuqavileByQurumAdi\n\n");
//        return service.getAllSigortaMuqavileDataByQurumAdi(name);
//    }

    @GetMapping("/getSigortaMuqavileData/{idSigortaMuqavile}")
    public ResponseEntity<SigortaMuqavileData> getSigortaMuqavileData(@PathVariable("idSigortaMuqavile") long idSigortaMuqavile) {
        logger.info("\n→→→SEARCH_CONTROLLER: getSigortaMuqavileData\n\n");
        return service.getSigortaMuqavileData(idSigortaMuqavile);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SigortaMuqavileData>> getAllSigortaMuqavileData() {
        logger.info("\n→→→SEARCH_CONTROLLER: getSigortaMuqavileData\n\n");
        return service.getAllSigortaMuqavileData();
    }

    @GetMapping("/cache")
    public ResponseEntity<String> startCaching() {
        return service.startCaching();
    }
}