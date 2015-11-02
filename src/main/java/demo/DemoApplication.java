package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import com.google.common.base.Predicate;

@SpringBootApplication
// @Import(value = { SecurityConfig.class })
// @EnableSwagger2
@EnableTransactionManagement
public class DemoApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Docket docket() {
		Predicate<String> paths = PathSelectors.ant("/**");

		ApiInfo apiInfo = new ApiInfoBuilder().title("Welcome")
				.description("The Spring Boot sample project")
				.contact("guharoytamajit@gmail.com").version("1.0.0").build();

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo).select().paths(paths).build();

		return docket;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}
	// public void configureDefaultServletHandling(
	// DefaultServletHandlerConfigurer configurer) {
	// configurer.enable();
	// }
}
