package az.contasoft.xmies_sigorta_muqavile_data.util;


import az.contasoft.xmies_paket_data.api.searchServices.internal.PaketData;
import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.PaketDataProxy;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.SigortaMuqavileProxy;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.SigortaQurumProxy;
import az.contasoft.xmies_sigorta_muqavile_data.proxy.XidmetlerDataProxy;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import az.contasoft.xmies_xidmetler_data.api.searchServices.internal.XidmetlerData;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HazelcastUtility {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final PaketDataProxy paketDataProxy;

    private final XidmetlerDataProxy xidmetlerDataProxy;

    private final SigortaMuqavileProxy sigortaMuqavileProxy;

    private final IMap<Long, SigortaMuqavile> mapOfSigortaMuqavile;

    private final SigortaQurumProxy sigortaQurumProxy;

    private final IMap<Long, SigortaQurum> mapOfSigortaQurum;

    public HazelcastUtility(PaketDataProxy paketDataProxy, XidmetlerDataProxy xidmetlerDataProxy, SigortaMuqavileProxy sigortaMuqavileProxy, IMap<Long, SigortaMuqavile> mapOfSigortaMuqavile, SigortaQurumProxy sigortaQurumProxy, IMap<Long, SigortaQurum> mapOfSigortaQurum) {
        this.paketDataProxy = paketDataProxy;
        this.xidmetlerDataProxy = xidmetlerDataProxy;
        this.sigortaMuqavileProxy = sigortaMuqavileProxy;
        this.mapOfSigortaMuqavile = mapOfSigortaMuqavile;
        this.sigortaQurumProxy = sigortaQurumProxy;
        this.mapOfSigortaQurum = mapOfSigortaQurum;
    }

    public XidmetlerData getXidmetlerData(long idXidmetler) {
        try {
            logger.info("XidmetlerData-ni proxy ile almaga chalishiriq");
            ResponseEntity<XidmetlerData> response = xidmetlerDataProxy.getXidmetlerData(idXidmetler);
            if (response.getStatusCodeValue() == 200) {
                return response.getBody();
            } else {
                return null;
            }
        }catch (Exception e){
            logger.error("Error getting XidmetlerData from  or getting data from XidmetlerData Proxy : "+e,e);
            return  null;
        }
    }

    public PaketData getPaketData(long idPaket) {
        try {
            logger.info("PaketData-ni proxy ile almaga chalishiriq");
            ResponseEntity<PaketData> response = paketDataProxy.getPaketData(idPaket);
            if (response.getStatusCodeValue() == 200) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Error getting PaketData from  or getting data from PaketData Proxy : " + e, e);
            return null;
        }
    }


    public SigortaQurum getSigortaQurum(long idSigortaQurum){
        try {
            logger.info("Hazelcastdan SigortaQurumu almaga chalishiriq idSigortaQurum : "+idSigortaQurum);
            SigortaQurum sigortaQurum = mapOfSigortaQurum.get(idSigortaQurum);
            if (sigortaQurum == null) {
                logger.info("Hazelcast da sigortaQurum yoxdu proxy ile almaga chalishiriq");
                ResponseEntity<SigortaQurum> responseEntity = sigortaQurumProxy.getByIdSigortaQurum(idSigortaQurum);
                if (responseEntity.getStatusCodeValue() == 200) {
                    sigortaQurum = responseEntity.getBody();
                }
            }
            return sigortaQurum;
        }catch (Exception e){
            logger.error("Error getting SigortaQurum from MapOfSigortaQurum or getting data from SigortaQurum Proxy : "+e,e);
            return null;
        }
    }

        public Map<Long,SigortaMuqavile> getSigortaMuqavile() {
            try {
                logger.info("Hazelcastdan SigortaMuqavileni almaga chalishiriq  : ");
                Map<Long,SigortaMuqavile> sigortaMuqavileMap = mapOfSigortaMuqavile;
                if (sigortaMuqavileMap== null || sigortaMuqavileMap.isEmpty()){
                    logger.info("Hazelcast da SigortaMuqavileni yoxdu proxy ile almaga chalishiriq");
                    ResponseEntity<Map<Long,SigortaMuqavile>> responseEntity = sigortaMuqavileProxy.getSigortaMuqavile();
                    if (responseEntity.getStatusCodeValue()==200){
                        sigortaMuqavileMap = responseEntity.getBody();
                    }
                }
                return sigortaMuqavileMap ;
            } catch (Exception e) {
                logger.error("Error getting PaketInfo from  or getting data from Pketinfo Proxy : "+e,e);
                return null;
            }
        }





        public List<SigortaMuqavileData> getAllSigortaMuqavileData() {
        try {
            Map<Long,SigortaMuqavile> sigortaMuqavileMap = getSigortaMuqavile();
            List<SigortaMuqavileData> sigortaMuqavileDataList = new ArrayList<>();
            for (Long idSigortaMuqavile : sigortaMuqavileMap.keySet()) {
                SigortaMuqavileData sigortaMuqavileData = new SigortaMuqavileData();
                SigortaMuqavile sigortaMuqavile = sigortaMuqavileMap.get(idSigortaMuqavile);
                if (sigortaMuqavile != null) {
                    if (sigortaMuqavile.getIdSigortaQurum() >0 ) {
                        SigortaQurum sigortaQurum = getSigortaQurum(sigortaMuqavile.getIdSigortaQurum());
                        sigortaMuqavileData.setSigortaQurum(sigortaQurum);
                    }

                    sigortaMuqavileData.setSigortaMuqavile(sigortaMuqavile);

                    if (sigortaMuqavile.getIdXidmetler() > 0) {
                        XidmetlerData xidmetler = getXidmetlerData(sigortaMuqavile.getIdXidmetler());
                        sigortaMuqavileData.setXidmetlerData(xidmetler);
                    }else {
                        if (sigortaMuqavile.getIdPaket() >0 ) {
                            PaketData paketData = getPaketData(sigortaMuqavile.getIdPaket());
                            sigortaMuqavileData.setPaketData(paketData);
                        }
                    }
                }
                sigortaMuqavileDataList.add(sigortaMuqavileData);
            }
            return sigortaMuqavileDataList;
        }catch (Exception e) {
            logger.error("Error getting all paket info data "+e,e);
            return null;
        }
        }





    }



