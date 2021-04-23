package com.github.fwidder.smartgarden;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UITests {

    @LocalServerPort
    private int port;

    @Test
    public void checkControlPageRenders() throws IOException {
        URL url = new URL("http://localhost:" + port + "/th-ui");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();

        int responseCode = huc.getResponseCode();

        assertThat(responseCode, is(equalTo(HttpStatus.OK.value())));
    }
}
