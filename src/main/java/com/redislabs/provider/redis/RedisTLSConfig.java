package com.redislabs.provider.redis;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.*;


public class RedisTLSConfig {

  private SSLSocketFactory sslSocketFactory;
  private SSLParameters sslParameters;
  private HostnameVerifier hostnameVerifier;

  RedisTLSConfig() {
    TrustManager[] trustAllCerts = new TrustManager[]{
      new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return new X509Certificate[0];
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
      }
    };

    try {
      SSLContext sc = SSLContext.getInstance("TLS");
      sc.init(null, trustAllCerts, null);
      sslSocketFactory = sc.getSocketFactory();
      sslParameters = new SSLParameters();
      sslParameters.setEndpointIdentificationAlgorithm("");
    } catch (Exception e) {
      throw new RuntimeException(e.toString());
    }

    hostnameVerifier = new HostnameVerifier() {
        @Override
      public boolean verify(String s, SSLSession sslSession) {
        return true;
      }
    };
  }

  public SSLSocketFactory getSSLSocketFactory() {
      return this.sslSocketFactory;
  }
  public SSLParameters getSSLParameters() {
      return this.sslParameters;
  }
  public HostnameVerifier getHostnameVerifier() {
      return this.hostnameVerifier;
  }

}


