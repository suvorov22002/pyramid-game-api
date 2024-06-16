package com.pyramid.game.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Suvorov Vassilievitch
 * Date: 07/05/2024
 * Time: 09:54
 * Project Name: pyramid-game-api
 */

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /*
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/
    @Bean
    Pari loadPari() {

        Map<String, Map<String, Object>> resultMap = null;
        Pari pari = new Pari();

        try {
            File jsonFile = new ClassPathResource("pari.json").getFile();
            // Parse JSON data from file
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> dataList = null;
            dataList = objectMapper.readValue(jsonFile, List.class);
            // Insert into a map
            resultMap = new HashMap<>();
            Map<String, Object> codeMap;
            for (Map<String, Object> data : dataList) {
                String game = (String) data.get("game");
                List<Map<String, Object>> values = (List<Map<String, Object>>) data.get("values");
                codeMap = new HashMap<>();
                for(Map<String, Object> v : values) {
                    var code = (String) v.get("name");
                    var valeur = v.get("value");
                    codeMap.put(code, valeur);
                }

                resultMap.put(game, codeMap);
            }

            pari.setListPari(resultMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pari;
    }
}
