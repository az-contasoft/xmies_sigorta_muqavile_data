package az.contasoft.xmies_sigorta_muqavile_data.util;

import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HazelcastConfiguration {


    @Bean
    public Config config() {
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


}
