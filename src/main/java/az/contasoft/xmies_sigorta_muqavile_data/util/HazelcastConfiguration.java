package az.contasoft.xmies_sigorta_muqavile_data.util;

import az.contasoft.xmies_paket.db.entity.Paket;
import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import az.contasoft.xmies_xidmetler.db.entity.Xidmetler;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HazelcastConfiguration {


    @Bean
    public Config config() {
        Config config = new Config();
        config.setProperty("hazelcast.logging.type","none");
        return new Config();
    }

    @Bean
    public HazelcastInstance instance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IMap<Long, SigortaMuqavile> mapOfSigortaMuqavile(HazelcastInstance instance) {
        return instance.getMap("mapOfSigortaMuqavile");
    }

    @Bean
    public IMap<Long, SigortaQurum> mapOfSigortaQurum(HazelcastInstance instance) {
        return instance.getMap("mapOfSigortaQurum");
    }

    @Bean
    public IMap<Long, Paket> mapOfPaket(HazelcastInstance instance) {
        return instance.getMap("mapOfPaket");
    }

    @Bean
    public IMap<Long, Xidmetler> mapOfXidmetler(HazelcastInstance instance) {
        return instance.getMap("mapOfXidmetler");
    }


    @Bean
    public IList<SigortaMuqavileData> ListOfSigortaMuqavileData(HazelcastInstance instance) {
        return instance.getList("ListOfSigortaMuqavileData");
    }







}