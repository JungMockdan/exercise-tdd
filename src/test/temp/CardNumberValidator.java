package test.temp;

import org.apache.http.HttpRequest;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class CardNumberValidator {
    private String server;
    private String body;

    public CardNumberValidator(String server) {
        this.server = server;
    }

    public CardValidity validate(String cardNumber) {
        CardValidity rsValidity;
        String result = "";
        URL url = null;
        try {
            url = new URL(server + "/card");
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }
        HttpURLConnection conn = null;
        OutputStreamWriter writer = null;

        try {
            conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(3000); //서버에 연결되는 Timeout 시간 설정
            conn.setReadTimeout(3000); // InputStream 읽어 오는 Timeout 시간 설정
            conn.setRequestMethod("POST");
            //json으로 message를 전달하고자 할 때
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setDoInput(true);
            conn.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(cardNumber); //json 형식의 message 전달
            writer.flush();

            StringBuilder sb = new StringBuilder();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Stream을 처리해줘야 하는 귀찮음이 있음.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String response = br.readLine();
                switch (response) {
                    case "ok":
                        rsValidity = CardValidity.VALID;
                    case "bad":
                        rsValidity = CardValidity.INVALID;
                    case "expired":
                        rsValidity = CardValidity.EXPIRED;
                    case "theft":
                        rsValidity = CardValidity.THEFT;
                    default:
                        rsValidity = CardValidity.UNKNOWN;
                }
                br.close();
                result = "" + sb.toString();
                rsValidity = CardValidity.VALID;
            } else {
                rsValidity = CardValidity.ERROR;
                result = conn.getResponseMessage();
            }

        } catch (SocketTimeoutException socketTimeoutException) {
            rsValidity = CardValidity.TIMEOUT;
        } catch (IOException e) {
            rsValidity = CardValidity.ERROR;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException exception) {
                    exception.printStackTrace();

                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsValidity == null ? CardValidity.ERROR : rsValidity;


    }
}
