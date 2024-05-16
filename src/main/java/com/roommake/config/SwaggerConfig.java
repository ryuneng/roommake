package com.roommake.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfig {

    @Bean
    public OpenAPI roommakeOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("RoomMake API")          // OpenAPI 제목
                        .description("RoomMake application")    // OpenAPI 설명
                        .version("v0.0.1"));                    // OpenAPI 버전
    }
}
