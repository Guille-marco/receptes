/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.receptes.config;

/**
 *
 * @author guill
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/recepta.html").setViewName("recepta");
        registry.addViewController("/contact.html").setViewName("contact");
        registry.addViewController("/usuarihome.html").setViewName("usuarihome");
        //registry.addViewController("/single-post.html").setViewName("single-post");
        //registry.addViewController("/receipe.html").setViewName("receipe");
    }
}
