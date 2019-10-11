package lt.keturka.annuity.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnuityConfiguration {

    @Autowired
    public void initObjectMapper(ObjectMapper objectMapper) {
        objectMapper.findAndRegisterModules();
    }

}
