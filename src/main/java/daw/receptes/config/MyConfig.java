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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/catagory.html").setViewName("catagory");
        registry.addViewController("/catagory-post.html").setViewName("catagory-post");
        registry.addViewController("/single-post.html").setViewName("single-post");
        registry.addViewController("/receipe.html").setViewName("receipe");
        registry.addViewController("/contact.html").setViewName("contact");
    }
}
