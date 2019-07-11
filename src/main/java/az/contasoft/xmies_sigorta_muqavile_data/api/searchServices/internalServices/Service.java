package az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internalServices;

import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigorta_muqavile_data.util.HazelcastUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Service {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HazelcastUtility hazelcastUtility;


    public Service(HazelcastUtility hazelcastUtility) {
        this.hazelcastUtility = hazelcastUtility;
    }


    public ResponseEntity<List<SigortaMuqavileData>> getAllSigortaMuqavileData() {
        try {
            List<SigortaMuqavileData> sigortaMuqavileDataList = hazelcastUtility.getAllSigortaMuqavileData();
            if (sigortaMuqavileDataList == null || sigortaMuqavileDataList.isEmpty()) {
                logger.info("sigortaMuqavileDataList isEmpty");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            logger.info("sigortaMuqavileDataList size : {}", sigortaMuqavileDataList.size());
            return new ResponseEntity<>(sigortaMuqavileDataList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("\n→→→: error  e: {}, e: {}\n\n", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SigortaMuqavileData>> getAllMuqavileByIdQurum(long idSigortaQurum) {
        try {
            List<SigortaMuqavileData> sigortaMuqavileDataList = hazelcastUtility.getAllSigortaMuqavileData().stream().filter(smd -> smd.getSigortaMuqavile().getIdSigortaQurum() == idSigortaQurum).collect(Collectors.toList());
            if (sigortaMuqavileDataList.isEmpty()) {
                logger.info("sigortaQurumList isEmpty");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            logger.info("sigortaQurumList size : {}", sigortaMuqavileDataList.size());
            return new ResponseEntity<>(sigortaMuqavileDataList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("\n→→→: error  e: {}, e: {}\n\n", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
