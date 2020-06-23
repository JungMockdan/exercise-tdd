package test.java.chap09;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.temp.CardNumberValidator;
import test.temp.CardValidity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*
* WireMockServer를 이용한 Http서버 대역 활용 테스트
* 외부연동 코드 테스트 시에 매우 유용.(JSON/XML응답, HTTPS지원, 단독실행 등 기능 제공)
* wiremock.org 참조.
* */
public class CardNumberValidatorTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @DisplayName("wireMockServer를 이용한 연결과 유효성 테스트")
    @Test
    void valid() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo("123456789"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "text/plain")
                                .withBody("ok")
                )
        );
        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("123456789");
        assertEquals(CardValidity.VALID, validity);
    }

    @DisplayName("응답시간 지연 테스트")
    @Test
    void timeout(){
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .willReturn(aResponse().withFixedDelay(5000))
        );

        CardNumberValidator validator = new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.TIMEOUT, validity);
    }
}
