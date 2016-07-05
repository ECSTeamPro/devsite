package vn.ecs.team.devsite.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfiguration {

	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(ThymeleafConfiguration.class);

	// @Bean
	// @Description("Thymeleaf template resolver serving HTML 5 emails")
	// public ClassLoaderTemplateResolver templateResolver() {
	// ClassLoaderTemplateResolver templateResolver = new
	// ClassLoaderTemplateResolver();
	// templateResolver.setPrefix("views/");
	// templateResolver.setSuffix(".html");
	// templateResolver.setTemplateMode("HTML5");
	// templateResolver.setCharacterEncoding("UTF-8");
	// templateResolver.setOrder(1);
	// return templateResolver;
	// }

	@Bean
	public ViewResolver viewResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(engine);
		return viewResolver;
	}
}
