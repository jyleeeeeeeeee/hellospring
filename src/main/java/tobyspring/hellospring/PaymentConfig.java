package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.ErApiExRateExtractor;
import tobyspring.hellospring.api.HttpClientApiExecutor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.exrate.WebApiExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

import java.time.Clock;

import static java.math.BigDecimal.valueOf;

@Configuration
public class PaymentConfig {
    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new WebApiExRateProvider(apiTemplate());
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }


}
