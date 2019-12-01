package az.contasoft.xmies_sigorta_muqavile_data.util;

import az.contasoft.xmies_paket.db.entity.Paket;
import az.contasoft.xmies_sigorta_muqavile.db.entity.SigortaMuqavile;
import az.contasoft.xmies_sigorta_muqavile_data.api.searchServices.internal.SigortaMuqavileData;
import az.contasoft.xmies_sigortaqurum.db.entity.SigortaQurum;
import az.contasoft.xmies_xidmetler.db.entity.Xidmetler;
import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    final
    HazelcastMapConfigurations hazelcastMapConfigurations;

    public HazelcastConfiguration(HazelcastMapConfigurations hazelcastMapConfigurations) {
        this.hazelcastMapConfigurations = hazelcastMapConfigurations;
    }

    @Bean
    public Config config() {
        Config config = new Config();
        NetworkConfig network = config.getNetworkConfig();
        network.getJoin().getMulticastConfig().setEnabled(false);
        network.getJoin().getTcpIpConfig().setEnabled(true);
        network.setPortAutoIncrement(true);
        network.setPort(33001);
        network.getJoin().getTcpIpConfig().addMember("127.0.0.1");
        return config;
    }


    @Bean
    public HazelcastInstance instance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IMap<Long, SigortaMuqavile> mapOfSigortaMuqavile(HazelcastInstance instance) {
        return instance.getMap(hazelcastMapConfigurations.getMapOfSigortaMuqavile());
    }

    @Bean
    public IMap<Long, SigortaQurum> mapOfSigortaQurum(HazelcastInstance instance) {
        return instance.getMap(hazelcastMapConfigurations.getMapOfSigortaQurum());
    }

    @Bean
    public IMap<Long, Paket> mapOfPaket(HazelcastInstance instance) {
        return instance.getMap(hazelcastMapConfigurations.getMapOfPaket());
    }

    @Bean
    public IMap<Long, Xidmetler> mapOfXidmetler(HazelcastInstance instance) {
        return instance.getMap(hazelcastMapConfigurations.getMapOfXidmetler());
    }

    @Bean
    public IMap<Long, SigortaMuqavileData> mapOfSigortaMuqavileData(HazelcastInstance instance) {
        return instance.getMap(hazelcastMapConfigurations.getMapOfSigortaMuqavileData());
    }
}
