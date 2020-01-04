package az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internalServices;

import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.RequestText;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigorta_muqavile_data.util.RedisUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ServiceREDIS {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedisUtility redisUtility;

    public ServiceREDIS(RedisUtility redisUtility) {
        this.redisUtility = redisUtility;
    }



    public ResponseEntity<Map<Long, SigortaMuqavileData>> getAllSigortaMuqavileDataMap() {
        try {
            Map<Long, SigortaMuqavileData> sigortaMuqavileDataMap = redisUtility.getMapOfSigortaMuqavileData();
            if (sigortaMuqavileDataMap == null || sigortaMuqavileDataMap.isEmpty()) {
                logger.info("MapOfSigortaMuqavileData isEmpty");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            logger.info("MapOfSigortaMuqavileData size : {}", sigortaMuqavileDataMap.size());
            return new ResponseEntity<>(sigortaMuqavileDataMap, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("\n→→→: error  e: {}, e: {}\n\n", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<SigortaMuqavileData>> getAllMuqavileByIdQurum(long idSigortaQurum) {
        try {
            List<SigortaMuqavileData> sigortaMuqavileDataList = redisUtility.getMapOfSigortaMuqavileData().values().stream().filter(smd -> smd.getSigortaMuqavile().getIdSigortaQurum() == idSigortaQurum).collect(Collectors.toList());
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

    public ResponseEntity<List<SigortaMuqavileData>> getQurumName(RequestText requestText) {
        try {
            List<SigortaMuqavileData> smdList = new ArrayList<>(redisUtility.getMapOfSigortaMuqavileData().values());
            String[] enteredTextMas = requestText.getEnteredText().split(" ");
            for (String enteredTextmas : enteredTextMas) {
                List<SigortaMuqavileData> yeniList = new ArrayList<>();
                for (SigortaMuqavileData smd : smdList) {
                    logger.info(" SigortaMuqavileData : {}",smd);
                    if (smd.getSigortaQurum().getQurumAdi().toLowerCase().contains(enteredTextmas.trim().toLowerCase())) {
                        yeniList.add(smd);
                    }
                }
                smdList = yeniList;
            }
            if (smdList == null || smdList.isEmpty()) {
                logger.info("SigortaMuqavileData tapilmadi");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            logger.info("requestText : {}, smdList : {}",requestText,smdList.size());
            return new ResponseEntity<>(smdList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("\n→→→SEARCH_SERVICE: error getQurumMuqavile e: {}, e: {}\n\n", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<SigortaMuqavileData> getSigortaMuqavileData(long idSigortaMuqavile) {
        try {
            SigortaMuqavileData sigortaMuqavileData = redisUtility.getSigortaMuqavileData(idSigortaMuqavile);
            if (sigortaMuqavileData == null) {
                logger.info("sigortaMuqavileData Null");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            logger.info("sigortaMuqavileData size : {}", sigortaMuqavileData);
            return new ResponseEntity<>(sigortaMuqavileData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("\n→→→: error  e: {}, e: {}\n\n", e, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> startCaching() {
        redisUtility.startCaching();
        return new ResponseEntity<>("Cached", HttpStatus.OK);
    }


}
