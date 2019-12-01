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
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
public class HazelcastUtility {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //private final IList<SigortaMuqavileData> listOfSigortaMuqavileData;
    private final PaketProxy paketProxy;
    private final IMap<Long, Paket> mapOfPaket;

    private final XidmetlerProxy xidmetlerProxy;
    private final IMap<Long, Xidmetler> mapOfXidmetler;

    private final SigortaMuqavileProxy sigortaMuqavileProxy;

    private final IMap<Long, SigortaMuqavile> mapOfSigortaMuqavile;

    private final SigortaQurumProxy sigortaQurumProxy;

    private final IMap<Long, SigortaQurum> mapOfSigortaQurum;
    private  IMap<Long, SigortaMuqavileData> mapOfSigortaMuqavileData;

    public HazelcastUtility(PaketProxy paketProxy, IMap<Long, Paket> mapOfPaket, XidmetlerProxy xidmetlerProxy, IMap<Long, Xidmetler> mapOfXidmetler, SigortaMuqavileProxy sigortaMuqavileProxy, IMap<Long, SigortaMuqavile> mapOfSigortaMuqavile, SigortaQurumProxy sigortaQurumProxy, IMap<Long, SigortaQurum> mapOfSigortaQurum, IMap<Long, SigortaMuqavileData> mapOfSigortaMuqavileData) {
        this.paketProxy = paketProxy;
        this.mapOfPaket = mapOfPaket;
        this.xidmetlerProxy = xidmetlerProxy;
        this.mapOfXidmetler = mapOfXidmetler;
        this.sigortaMuqavileProxy = sigortaMuqavileProxy;
        this.mapOfSigortaMuqavile = mapOfSigortaMuqavile;
        this.sigortaQurumProxy = sigortaQurumProxy;
        this.mapOfSigortaQurum = mapOfSigortaQurum;
        this.mapOfSigortaMuqavileData = mapOfSigortaMuqavileData;
    }



    public IMap<Long, SigortaMuqavileData> getMapOfSigortaMuqavileData() {
        if (mapOfSigortaMuqavileData.isEmpty()){
            startCaching();
        }
        return mapOfSigortaMuqavileData;
    }



    public Xidmetler getXidmetler(long idXidmetler) {
        try {
            logger.info("Hazelcastdan Xidmetler almaga chalishiriq idXidmetler : " + idXidmetler);
            Xidmetler xidmetler = mapOfXidmetler.get(idXidmetler);
            if (xidmetler == null) {
                logger.info("HazelCastda Xidmetler yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Xidmetler> response = xidmetlerProxy.getXidmetler(idXidmetler);
                if (response.getStatusCodeValue() == 200) {
                    xidmetler = response.getBody();
                }
            }
            return xidmetler;
        } catch (Exception e) {
            logger.error("Error getting Xidmetler from  or getting data from Xidmetler Proxy : " + e, e);
            return null;
        }
    }

    public Paket getPaket(long idPaket) {
        try {
            logger.info("Hazelcastdan Paketi almaga chalishiriq idPaket : " + idPaket);
            Paket paket = mapOfPaket.get(idPaket);
            if (paket == null) {
                logger.info("Hazelcastda Paket yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Paket> response = paketProxy.getPaket(idPaket);
                if (response.getStatusCodeValue() == 200) {
                    paket = response.getBody();
                }
            }
            return paket;
        } catch (Exception e) {
            logger.error("Error getting Paket from  or getting data from Paket Proxy : " + e, e);
            return null;
        }
    }


    public SigortaQurum getSigortaQurum(long idSigortaQurum) {
        try {
            logger.info("Hazelcastdan SigortaQurumu almaga chalishiriq idSigortaQurum : " + idSigortaQurum);
            SigortaQurum sigortaQurum = mapOfSigortaQurum.get(idSigortaQurum);
            if (sigortaQurum == null) {
                logger.info("Hazelcast da sigortaQurum yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<SigortaQurum> responseEntity = sigortaQurumProxy.getByIdSigortaQurum(idSigortaQurum);
                if (responseEntity.getStatusCodeValue() == 200) {
                    sigortaQurum = responseEntity.getBody();
                }
            }
            return sigortaQurum;
        } catch (Exception e) {
            logger.error("Error getting SigortaQurum from mapOfSigortaQurum or getting data from SigortaQurum Proxy : " + e, e);
            return null;
        }
    }

    public Map<Long, SigortaMuqavile> getSigortaMuqavile() {
        try {
            logger.info("Hazelcastdan SigortaMuqavileni almaga chalishiriq  : ");
            Map<Long, SigortaMuqavile> sigortaMuqavileMap = mapOfSigortaMuqavile;
            if (sigortaMuqavileMap == null || sigortaMuqavileMap.isEmpty()) {
                logger.info("Hazelcast da SigortaMuqavileni yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<Map<Long, SigortaMuqavile>> responseEntity = sigortaMuqavileProxy.getSigortaMuqavile();
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
        SigortaMuqavileData sigortaMuqavileData = getMapOfSigortaMuqavileData().values().stream().filter(smd -> smd.getSigortaMuqavile().getIdSigortaMuqavile()==idSigortaMuqavile).findAny().orElse(null);
        logger.info("sigortaMuqavileData : {}", sigortaMuqavileData);
        return sigortaMuqavileData;
    }


    private void collectSigortaMuqavileData() {
        try {
            logger.info("trying to get  SigortaMuqavile from hazelcast");
            Map<Long, SigortaMuqavile> sigortaMuqavileMap = getSigortaMuqavile();
            for (Long idSigortaMuqavile : sigortaMuqavileMap.keySet()) {
                SigortaMuqavileData sigortaMuqavileData = new SigortaMuqavileData();
                SigortaMuqavile sigortaMuqavile = sigortaMuqavileMap.get(idSigortaMuqavile);
                sigortaMuqavileData.setSigortaMuqavile(sigortaMuqavile);
                logger.info("sigortaMuqavile-u aldig" + sigortaMuqavile.toString());
                if (sigortaMuqavile.getIdSigortaQurum() > 0) {
                    SigortaQurum sigortaQurum = getSigortaQurum(sigortaMuqavile.getIdSigortaQurum());
                    sigortaMuqavileData.setSigortaQurum(sigortaQurum);
                    logger.info("sigortaQurum-u aldig : {}", sigortaQurum);
                }

                if (sigortaMuqavile.getIdXidmetler() > 0) {
                    Xidmetler xidmetler = getXidmetler(sigortaMuqavile.getIdXidmetler());
                    sigortaMuqavileData.setXidmetler(xidmetler);
                    logger.info("xidmetler-i aldig : {}", xidmetler);
                } else {
                    if (sigortaMuqavile.getIdPaket() > 0) {
                        Paket paket = getPaket(sigortaMuqavile.getIdPaket());
                        sigortaMuqavileData.setPaket(paket);
                        logger.info("paket-i aldig : {}", paket);
                    }
                }
                mapOfSigortaMuqavileData.put(sigortaMuqavile.getIdSigortaMuqavile(),sigortaMuqavileData);
            }
            logger.info("listOfSigortaMuqavileData" + mapOfSigortaMuqavileData.size());
        } catch (Exception e) {
            logger.error("Error getting all SigortaMuqavile info data " + e, e);
        }
    }

    public void startCaching() {
        mapOfSigortaMuqavileData.clear();
        mapOfSigortaMuqavileData.destroy();
        logger.info(" listOfSigortaMuqavileData clear"+mapOfSigortaMuqavileData.size());
        collectSigortaMuqavileData();
    }
}