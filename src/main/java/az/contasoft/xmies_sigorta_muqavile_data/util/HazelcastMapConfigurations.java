package az.contasoft.xmies_sigorta_muqavile_data.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Deprecated
@Configuration
public class HazelcastMapConfigurations {

    @Value("${hazelcast.map.mapOfSigortaMuqavile}")
    private String mapOfSigortaMuqavile;

    @Value("${hazelcast.map.mapOfSigortaQurum}")
    private String mapOfSigortaQurum;

    @Value("${hazelcast.map.mapOfPaket}")
    private String mapOfPaket;

    @Value("${hazelcast.map.mapOfXidmetler}")
    private String mapOfXidmetler;

    @Value("${hazelcast.map.mapOfSigortaMuqavileData}")
    private String mapOfSigortaMuqavileData;


    public HazelcastMapConfigurations() {
    }


    public String getMapOfSigortaMuqavile() {
        return mapOfSigortaMuqavile;
    }

    public void setMapOfSigortaMuqavile(String mapOfSigortaMuqavile) {
        this.mapOfSigortaMuqavile = mapOfSigortaMuqavile;
    }

    public String getMapOfSigortaQurum() {
        return mapOfSigortaQurum;
    }

    public void setMapOfSigortaQurum(String mapOfSigortaQurum) {
        this.mapOfSigortaQurum = mapOfSigortaQurum;
    }

    public String getMapOfPaket() {
        return mapOfPaket;
    }

    public void setMapOfPaket(String mapOfPaket) {
        this.mapOfPaket = mapOfPaket;
    }

    public String getMapOfXidmetler() {
        return mapOfXidmetler;
    }

    public void setMapOfXidmetler(String mapOfXidmetler) {
        this.mapOfXidmetler = mapOfXidmetler;
    }

    public String getMapOfSigortaMuqavileData() {
        return mapOfSigortaMuqavileData;
    }

    public void setMapOfSigortaMuqavileData(String mapOfSigortaMuqavileData) {
        this.mapOfSigortaMuqavileData = mapOfSigortaMuqavileData;
    }
}
