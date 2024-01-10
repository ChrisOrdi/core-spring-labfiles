package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

import javax.sql.DataSource;

/**
 * TODO-00: In this lab, you are going to exercise the following:
 * - Creating Spring configuration class
 * - Defining bean definitions within the configuration class
 * - Specifying the dependency relationships among beans
 * - Injecting dependencies through constructor injection
 * - Creating Spring application context in the test code
 *   (WITHOUT using Spring testContext framework)
 *
 */

@Configuration
public class RewardsConfig {

	// Set this by adding a constructor.
	private DataSource dataSource;

	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public RewardNetwork rewardNetwork(){
		return new RewardNetworkImpl(
				accountRepository(),
				restaurantRepository(),
				rewardRepository());
	}

	@Bean
	AccountRepository accountRepository() {

	  // Instantieer
		JdbcAccountRepository repository = new JdbcAccountRepository();
		// Defineer datasource
		repository.setDataSource(dataSource);
		// geef de repository terug
		return repository;
	}

	@Bean
	RestaurantRepository restaurantRepository() {
		// instantie van jdbc aanmaken
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
		// datasource aangeven
		repository.setDataSource(dataSource);
		// repository teruggeven
		return repository;
	}

	@Bean
	RewardRepository rewardRepository() {
		// instantieren van jdbc repo
		JdbcRewardRepository repository = new JdbcRewardRepository();
		//datasource aangeven
		repository.setDataSource(dataSource);
		// repository teruggeven
		return repository;
	}
}
