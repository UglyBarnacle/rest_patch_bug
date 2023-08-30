package rest.patch.bug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication(scanBasePackages = { "rest.patch.bug" })
@EnableMongoRepositories("rest.patch.bug")
public class BugApplication
{
	public static void main(final String[] args)
	{
		SpringApplication.run(BugApplication.class, args);
	}

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer()
	{
		return new RepositoryRestConfigurer()
		{
			@Override
			public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config, final CorsRegistry cors)
			{
				config.exposeIdsFor(BugModel.class);
			}
		};
	}
}
