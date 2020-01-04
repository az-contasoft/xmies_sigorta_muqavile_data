package az.contasoft.xmies_sigorta_muqavile_data.util;


import az.contasoft.xmies_paket.db.entity.Paket;
import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.PaketProxy;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.SigortaMuqavileProxy;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.SigortaQurumProxy;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.XidmetlerProxy;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import az.contasoft.xmies_xidmetler.db.entity.Xidmetler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.xa.Xid;
import java.util.Map;

@Component
public class RedisUtility {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final PaketProxy paketProxy;
    private final XidmetlerProxy xidmetlerProxy;
    private final SigortaMuqavileProxy sigortaMuqavileProxy;
    private final SigortaQurumProxy sigortaQurumProxy;
    private final RedisService redisService;

    public RedisUtility(PaketProxy paketProxy, XidmetlerProxy xidmetlerProxy, SigortaMuqavileProxy sigortaMuqavileProxy, SigortaQurumProxy sigortaQurumProxy, RedisService redisService) {
        this.paketProxy = paketProxy;
        this.xidmetlerProxy = xidmetlerProxy;
        this.sigortaMuqavileProxy = sigortaMuqavileProxy;
        this.sigortaQurumProxy = sigortaQurumProxy;
        this.redisService = redisService;
    }

    public Map<Long, SigortaMuqavileData> getMapOfSigortaMuqavileData() {
    Map<Long, SigortaMuqavileData> mapOfSigortaMuqavileData = redisService.getMap(RedisMapKey.MAP_OF_SIGORTAMUQAVILEDATA);
        if (mapOfSigortaMuqavileData.isEmpty()) {
            logger.info("MAP_OF_SIGORTAMUQAVILEDATA IS EMPTY, startCaching :");
            startCaching();
        }
        return mapOfSigortaMuqavileData;
    }


    public Map<Long, Xidmetler> getAllXidmetler() {
        try {
            logger.info("Redisden Xidmetler almaga chalishiriq :");
            Map<Long, Xidmetler> xidmetlerMap = redisService.getMap(RedisMapKey.MAP_OF_XIDMETLER);
            if (xidmetlerMap == null || xidmetlerMap.isEmpty()) {
                logger.info("REDISDE Xidmetler yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Map<Long, Xidmetler>> response = xidmetlerProxy.getAllByIsDelete();
                if (response.getStatusCodeValue() == 200) {
                    xidmetlerMap = response.getBody();
                }
            }
            return xidmetlerMap;
        } catch (Exception e) {
            logger.error("Error getting Xidmetler from  or getting data from Xidmetler Proxy : " + e, e);
            return null;
        }
    }

    public Map<Long, Paket> getAllPaket() {
        try {
            logger.info("Redis Paketi almaga chalishiriq :");
            Map<Long, Paket> paketMap = redisService.getMap(RedisMapKey.MAP_OF_PAKET);
            if (paketMap == null || paketMap.isEmpty()) {
                logger.info("Redisde Paket yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Map<Long, Paket>> response = paketProxy.getAllByIsDelete();
                if (response.getStatusCodeValue() == 200) {
                    paketMap = response.getBody();
                }
            }
            return paketMap;
        } catch (Exception e) {
            logger.error("Error getting Paket from  or getting data from Paket Proxy : " + e, e);
            return null;
        }
    }


    public Map<Long, SigortaQurum> getAllSigortaQurum() {
        try {
            logger.info("Hazelcastdan SigortaQurumu almaga chalishiriq :");
            Map<Long, SigortaQurum> sigortaQurumMap = redisService.getMap(RedisMapKey.MAP_OF_SIGORTAQURUM);
            if (sigortaQurumMap == null || sigortaQurumMap.isEmpty()) {
                logger.info("Redis de sigortaQurum yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Map<Long, SigortaQurum>> responseEntity = sigortaQurumProxy.getAllSigortaQurumFromRedis();
                if (responseEntity.getStatusCodeValue() == 200) {
                    sigortaQurumMap = responseEntity.getBody();
                }
            }
            return sigortaQurumMap;
        } catch (Exception e) {
            logger.error("Error getting SigortaQurum from MAP_OF_SIGORTAQURUM or getting data from SigortaQurum Proxy : " + e, e);
            return null;
        }
    }

    public Map<Long, SigortaMuqavile> getSigortaMuqavile() {
        try {
            logger.info("Hazelcastdan SigortaMuqavileni almaga chalishiriq  : ");
            Map<Long, SigortaMuqavile> sigortaMuqavileMap = redisService.getMap(RedisMapKey.MAP_OF_SIGORTAMUQAVILE);
            if (sigortaMuqavileMap == null || sigortaMuqavileMap.isEmpty()) {
                logger.info("Hazelcast da SigortaMuqavileni yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Map<Long, SigortaMuqavile>> responseEntity = sigortaMuqavileProxy.getAllSigortaMuqavileFromRedis();
                if (responseEntity.getStatusCodeValue() == 200) {
                    sigortaMuqavileMap = responseEntity.getBody();
                }
            }
            return sigortaMuqavileMap;
        } catch (Exception e) {
            logger.error("Error getting SigortaMuqavile from  or getting data from SigortaMuqavile Proxy : " + e, e);
            return null;
        }
    }


    public SigortaMuqavileData getSigortaMuqavileData(long idSigortaMuqavile) {
        SigortaMuqavileData sigortaMuqavileData = getMapOfSigortaMuqavileData().values().stream().filter(smd -> smd.getSigortaMuqavile().getIdSigortaMuqavile() == idSigortaMuqavile).findAny().orElse(null);
        logger.info("sigortaMuqavileData : {}", sigortaMuqavileData);
        return sigortaMuqavileData;
    }


    private void collectSigortaMuqavileData() {
        try {
            logger.info("trying to get  SigortaMuqavile from REDIS");
            Map<Long, SigortaMuqavile> sigortaMuqavileMap = getSigortaMuqavile();
            logger.info("sigortaMuqavileMap size : {}", sigortaMuqavileMap.size());
            Map<Long, SigortaQurum> sigortaQurumMap = getAllSigortaQurum();
            logger.info("sigortaQurumMap size : {}", sigortaQurumMap.size());
            Map<Long, Xidmetler> xidmetlerMap = getAllXidmetler();
            logger.info("xidmetlerMap size : {}", xidmetlerMap.size());
            Map<Long, Paket> paketMap = getAllPaket();
            logger.info("paketMap size : {}", paketMap.size());

            for (Long idSigortaMuqavile : sigortaMuqavileMap.keySet()) {
                SigortaMuqavileData sigortaMuqavileData = new SigortaMuqavileData();
                SigortaMuqavile sigortaMuqavile = sigortaMuqavileMap.get(idSigortaMuqavile);
                sigortaMuqavileData.setSigortaMuqavile(sigortaMuqavile);
                logger.info("sigortaMuqavile-u aldig" + sigortaMuqavile.toString());
                if (sigortaMuqavile.getIdSigortaQurum() > 0) {
                    SigortaQurum sigortaQurum = getAllSigortaQurum().get(sigortaMuqavile.getIdSigortaQurum());
                    sigortaMuqavileData.setSigortaQurum(sigortaQurum);
                    logger.info("sigortaQurum-u aldig : {}", sigortaQurum);
                }

                if (sigortaMuqavile.getIdXidmetler() > 0) {
                    Xidmetler xidmetler = getAllXidmetler().get(sigortaMuqavile.getIdXidmetler());
                    sigortaMuqavileData.setXidmetler(xidmetler);
                    logger.info("xidmetler-i aldig : {}", xidmetler);
                } else {
                    if (sigortaMuqavile.getIdPaket() > 0) {
                        Paket paket = getAllPaket().get(sigortaMuqavile.getIdPaket());
                        sigortaMuqavileData.setPaket(paket);
                        logger.info("paket-i aldig : {}", paket);
                    }
                }
//                mapOfSigortaMuqavileData.put(sigortaMuqavile.getIdSigortaMuqavile(),sigortaMuqavileData);
                redisService.add(sigortaMuqavileData.getSigortaMuqavile().getIdSigortaMuqavile(), sigortaMuqavileData, RedisMapKey.MAP_OF_SIGORTAMUQAVILEDATA);
            }
            logger.info("MAP_OF_SIGORTAMUQAVILEDATA SIZE" + redisService.getMap(RedisMapKey.MAP_OF_SIGORTAMUQAVILEDATA).size());
        } catch (Exception e) {
            logger.error("Error getting all SigortaMuqavile info data " + e, e);
        }
    }

    public void startCaching() {
        redisService.deleteMap(RedisMapKey.MAP_OF_SIGORTAMUQAVILEDATA);
        logger.info(" MAP_OF_SIGORTAMUQAVILEDATA clear");
        collectSigortaMuqavileData();
    }
}