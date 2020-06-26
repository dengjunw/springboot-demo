package com.dengjunwu.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 创建Http请求工具类
 *
 * @author unknown
 */
@SuppressWarnings("deprecation")
@Slf4j
public class HttpClientUtil {

    // 最大连接数
    private static final int MAX_TOTAL_CONNECTIONS = 5000;
    // 每个路由最大连接数
    private static final int MAX_ROUTE_CONNECTIONS = 40;
    private static PoolingHttpClientConnectionManager connectionManager;

    /**
     * 初始化配置
     */
    static {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.createDefault();
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }}, null);
        } catch (KeyManagementException e) {
            log.error("KeyManagementException", e);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", (new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))).build();
        connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .build();
        connectionManager.setDefaultConnectionConfig(connectionConfig);
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
    }


    /**
     * 创建 POST请求
     **/
    public static PostRequest buildPostRequest(String url) {
        return new PostRequest(getHttpClient(), url);
    }

    public static PostRequest buildPostRequest(String url, int retryCount) {
        return new PostRequest(getHttpClient(retryCount), url);
    }

    public static PostRequest buildPostRequest(String url, String charset) {
        return new PostRequest(getHttpClient(), url, charset);
    }

    public static PostRequest buildPostRequest(String url, int retryCount, String charset) {
        return new PostRequest(getHttpClient(retryCount), url, charset);
    }

    public static GetRequest buildGetRequest(String url) {
        return new GetRequest(getHttpClient(), url);
    }

    public static GetRequest buildGetRequest(String url, int retryCount) {
        return new GetRequest(getHttpClient(retryCount), url);
    }

    public static GetRequest buildGetRequest(String url, String charset) {
        return new GetRequest(getHttpClient(), url, charset);
    }

    public static GetRequest buildGetRequest(String url, int retryCount, String charset) {
        return new GetRequest(getHttpClient(retryCount), url, charset);
    }

    private static HttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    private static HttpClient getHttpClient(int retryCount) {
        return HttpClients.custom()
                .setRetryHandler(createHttpRequestRetryHandler(retryCount))
                .setServiceUnavailableRetryStrategy(createServiceUnavailableRetryStrategy(retryCount))
                .setConnectionManager(connectionManager)
                .build();
    }

    private static ServiceUnavailableRetryStrategy createServiceUnavailableRetryStrategy(final int retryCount) {
        return new ServiceUnavailableRetryStrategy() {
            @Override
            public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
                log.info("retry retryCount={},executionCount={}", retryCount, executionCount);
                int statusCode = response.getStatusLine().getStatusCode();
                return (statusCode >= 500) && executionCount <= retryCount;
            }

            @Override
            public long getRetryInterval() {
                return 1000;
            }
        };
    }

    private static HttpRequestRetryHandler createHttpRequestRetryHandler(final int retryCount) {
        return (exception, executionCount, context) -> {
            log.info("retry retryCount={},executionCount={}", retryCount, executionCount);
            if (executionCount >= retryCount) {
                return false;
            }
            log.error("request error :" + exception.getMessage());
            return true;
        };
    }


    public static PostRequest buildSSLPostRequest(String url, String keyPath, String pass) {
        try {
            return new PostRequest(getHttpClient(keyPath, pass), url);
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException | IOException | CertificateException | KeyManagementException e) {
            log.error("", e);
        }
        return null;
    }

    private static HttpClient getHttpClient(String keyPath, String password) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException,
            KeyManagementException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream inStream = new FileInputStream(new File(keyPath))) {
            keyStore.load(inStream, password.toCharArray());
        }

        // Trust own CA and all self-signed certs
        // Allow TLSv1 protocol only
        return HttpClients.custom()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(
                        SSLContexts.custom()
                                .loadKeyMaterial(keyStore, password.toCharArray())
                                .build(),
                        new String[]{"TLSv1"},
                        null,
                        new DefaultHostnameVerifier()))
//                        BrowserCompatHostnameVerifier.INSTANCE))
                .build();
    }
}


