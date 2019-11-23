package com.mkaszynski.tdd.mock.tools;

import com.mkaszynski.tdd.mock.ResponseVisitor;
import com.mkaszynski.tdd.mock.RestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicRestClient implements RestClient {
    @Override
    public <T> T executeGet(String stringUrl, ResponseVisitor<T> visitor) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(stringUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            return visitor.processResponse(new HttpResponse(conn.getResponseCode(), readContent(conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    private String readContent(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }
}
