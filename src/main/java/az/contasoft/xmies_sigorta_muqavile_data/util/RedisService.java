package az.contasoft.xmies_sigorta_muqavile_data.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }
//
//    private String getMapOfRollNames(){
//        return hazelcatMapConfigurations.getMapOfRollNames();
//    }
//
//    private String getMapOfPermissions(){
//        return hazelcatMapConfigurations.getMapOfPermissions();
//    }


    public <K,V> boolean add(K k, V v, RedisMapKey redisMapKey){
        try {
            hashOperations.put(redisMapKey.getMapName(),k,v);
//            switch (redisMapKey) {
//                case MAP_OF_ROLL_NAMES: {
//                    hashOperations.put(getMapOfRollNames(), k, v);
//                }
//                break;
//                case MAP_OF_PERMISSIONS:{
//                    hashOperations.put(getMapOfPermissions(), k, v);
//                }
//                break;
//            }
        }catch (Exception e){

            logger.error("Error putting redis : "+e,e);
            return false;
        }

        return true;
    }

    public <K,V> Map<K,V> getMap(RedisMapKey redisMapKey){
        try{
            return hashOperations.entries(redisMapKey.getMapName());

//            switch (redisMapKey){
//                case MAP_OF_ROLL_NAMES: return hashOperations.entries(getMapOfRollNames());
//                case MAP_OF_PERMISSIONS: return hashOperations.entries(getMapOfPermissions());
//                default:return null;
//            }
        }catch (Exception e){
            logger.error("Error getting map : "+e, e);
            return null;
        }
    }
    public void deleteMap(RedisMapKey redisMapKey){
        try {
            hashOperations.getOperations().delete(redisMapKey.getMapName());
        }catch (Exception e) {
            logger.error("Error deleted Map : {}", e, e);
        }
    }


//    public <K, V> V getDataByKey(K k, RedisMapKey redisMapKey){
//        try{
//            logger.info("trying to get data from redis...");
//            switch (redisMapKey){
//                case MAP_OF_ROLL_NAMES:return ((Map<K,V>)hashOperations.entries(getMapOfRollNames())).get(k);
//                case MAP_OF_PERMISSIONS: return ((Map<K,V>)hashOperations.entries(getMapOfPermissions())).get(k);
//                default:return null;
//            }
//        }catch (Exception e){
//            logger.error("Error getting data by key : "+e,e);
//            return null;
//        }
//    }



}
