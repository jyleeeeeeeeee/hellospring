package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public abstract class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        // 환율 가져오기
        BigDecimal exRate = getExRate(currency);

        // 금액 계산
        // 서비스 관련 로직 변경 시 변경
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        // 유효 시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        // Payment object return
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    abstract BigDecimal getExRate(String currency) throws IOException;
}
