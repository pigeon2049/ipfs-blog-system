package eth.moecat.ocean.config;


import io.ipfs.api.IPFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IpfsConfig {

    @Value("${ipfs.api.baseUrl}")
    private String baseUrl;

    @Bean
    public IPFS ipfs(){
        return new IPFS(baseUrl);
    }
}
