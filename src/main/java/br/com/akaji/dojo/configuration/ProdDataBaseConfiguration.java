package br.com.akaji.dojo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("prod")
public class ProdDataBaseConfiguration {
	
	@Value("${akaji.datasource.url}")
	private String url;
	
	@Value("${akaji.datasource.username}")
	private String username;
	
	@Value("${akaji.datasource.password}")
	private String password;
	
	@Value("${akaji.datasource.driver}")
	private String driver;
	
	@Value("${akaji.datasource.type}")
	private String type;
	
	@Value("${akaji.datasource.dialect}")
	private String dialect;
	
	@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setSchema("update");
        return dataSource;
    }
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		Database database = Database.valueOf(type);
		adapter.setDatabase(database);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform(dialect);
		adapter.setPrepareConnection(true);
		return adapter;
	}
}
