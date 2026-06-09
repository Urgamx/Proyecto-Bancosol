package com.example.proyectobancosol.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AdminAccessInterceptor adminAccessInterceptor;

    public WebConfig(AdminAccessInterceptor adminAccessInterceptor) {
        this.adminAccessInterceptor = adminAccessInterceptor;
    }

    // YA EXISTÍA: protege /admin y todas sus rutas internas
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAccessInterceptor)
                .addPathPatterns("/admin", "/admin/**");
    }

    // CAMBIO: busca los JSP tanto si se ejecuta desde la raíz como desde el módulo
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> jspDocumentRootCustomizer() {
        return factory -> {
            File documentRoot = new File("src/main/webapp");

            // CAMBIO: si no existe, probamos la ruta cuando IntelliJ ejecuta desde la carpeta padre
            if (!documentRoot.exists()) {
                documentRoot = new File("proyectoBancosol/src/main/webapp");
            }

            System.out.println("DOCUMENT ROOT JSP: " + documentRoot.getAbsolutePath());
            System.out.println("EXISTE DOCUMENT ROOT JSP: " + documentRoot.exists());

            if (documentRoot.exists()) {
                factory.setDocumentRoot(documentRoot);
            }
        };
    }
}