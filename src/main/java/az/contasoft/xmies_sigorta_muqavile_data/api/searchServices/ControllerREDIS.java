package az.contasoft.xmies_sigorta_muqavile_data.api.searchServices;

import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.RequestText;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internalServices.Service;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internalServices.ServiceREDIS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/searchServices/redis")
public class ControllerREDIS {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ServiceREDIS serviceREDIS;


    public ControllerREDIS(ServiceREDIS serviceREDIS) {
        this.serviceREDIS = serviceREDIS;
    }


    @GetMapping("/getByIdQurum/{idSigortaQurum}")
    public ResponseEntity<List<SigortaMuqavileData>> getMuqavileByIdQurum(@PathVariable("idSigortaQurum") long idSigortaQurum) {
        logger.info("\n→→→SEARCH_CONTROLLER: getMuqavileByIdQurum\n\n");
        return serviceREDIS.getAllMuqavileByIdQurum(idSigortaQurum);
    }
//
//    @GetMapping("/sigortaAdi/{name}")
//    public ResponseEntity<List<SigortaMuqavileData>> getMuqavileByQurumAdi(@PathVariable("name") String name) {
//        logger.info("\n→→→SEARCH_CONTROLLER: getMuqavileByQurumAdi\n\n");
//        return service.getAllSigortaMuqavileDataByQurumAdi(name);
//    }
@PostMapping("/sigortaAdi")
public ResponseEntity<List<SigortaMuqavileData>> getQurumName(@RequestBody RequestText requestText){
    logger.info("{}","getting QurumAdi by enteredText from hazelcast");
    return serviceREDIS.getQurumName(requestText);
}

    @GetMapping("/getSigortaMuqavileData/{idSigortaMuqavile}")
    public ResponseEntity<SigortaMuqavileData> getSigortaMuqavileData(@PathVariable("idSigortaMuqavile") long idSigortaMuqavile) {
        logger.info("\n→→→SEARCH_CONTROLLER: getSigortaMuqavileData\n\n");
        return serviceREDIS.getSigortaMuqavileData(idSigortaMuqavile);
    }

//    @GetMapping("/getAll")
//    public ResponseEntity<List<SigortaMuqavileData>> getAllSigortaMuqavileData() {
//        logger.info("\n→→→SEARCH_CONTROLLER: getSigortaMuqavileData\n\n");
//        return serviceREDIS.get();
//    }

    @GetMapping("/getAllAsMap")
    public ResponseEntity<Map<Long, SigortaMuqavileData>> getAllAsMap() {
        logger.info("\n→→→SEARCH_CONTROLLER: getSigortaMuqavileData\n\n");
        return serviceREDIS.getAllSigortaMuqavileDataMap();
    }
    @GetMapping("/cache")
    public ResponseEntity<String> startCaching() {
        new Thread(() -> serviceREDIS.startCaching()).start() ;
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}