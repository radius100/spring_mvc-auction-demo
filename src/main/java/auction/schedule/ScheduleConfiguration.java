package auction.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import auction.service.ToggleItemStatusService;

@Configuration
@EnableScheduling
public class ScheduleConfiguration {

	 @Bean
	 public ToggleItemStatusService bean() {
		 return new ToggleItemStatusService();
	 }
}
