package com.lou.auth_okhttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.CertificatePinner;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private static OkHttpClient client;

    private static String response;

    private static LoginInfo obj1;

    public static String finalUser;

    private static String url = "https://app.dev.it.si/alchemy/api/1.0/login";

    private static final String ITSIPEM = "-----BEGIN CERTIFICATE-----\n" +
            "MIIEXzCCA0egAwIBAgIJAMKQOgdGwN9LMA0GCSqGSIb3DQEBCwUAMIHFMQswCQYD\n" +
            "VQQGEwJaQTEQMA4GA1UECAwHR2F1dGVuZzERMA8GA1UEBwwIUHJldG9yaWExHTAb\n" +
            "BgNVBAoMFElUIFNjaG9vbCBJbm5vdmF0aW9uMRkwFwYDVQQLDBBJVFNJIERldmVs\n" +
            "b3BtZW50MS8wLQYDVQQDDCZJVFNJIERldmVsb3BtZW50IENlcnRpZmljYXRlIEF1\n" +
            "dGhvcml0eTEmMCQGCSqGSIb3DQEJARYXc3VwcG9ydEBpdHNjaG9vbHMuY28uemEw\n" +
            "HhcNMTQwOTEwMDczOTEwWhcNMjQwOTA3MDczOTEwWjCBxTELMAkGA1UEBhMCWkEx\n" +
            "EDAOBgNVBAgMB0dhdXRlbmcxETAPBgNVBAcMCFByZXRvcmlhMR0wGwYDVQQKDBRJ\n" +
            "VCBTY2hvb2wgSW5ub3ZhdGlvbjEZMBcGA1UECwwQSVRTSSBEZXZlbG9wbWVudDEv\n" +
            "MC0GA1UEAwwmSVRTSSBEZXZlbG9wbWVudCBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkx\n" +
            "JjAkBgkqhkiG9w0BCQEWF3N1cHBvcnRAaXRzY2hvb2xzLmNvLnphMIIBIjANBgkq\n" +
            "hkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0BfpGWpIUMmGfFgYdn95UH+XbZkcTEYy\n" +
            "45W6KqiJpW2eygnr9O2mbi1NLYGllvIAkR0/jUrI4m7MNnzGLz5NfLKRtog356V8\n" +
            "LIeb5zIUu+L1Zuw0YuKEcbRzAV4txB5NA5zKLiYg88rLi7uXVlmVIDp28RO6HQLJ\n" +
            "8r5D9SbOFiTXG0BZ1oQkdQ2gZtJKr0S04UAYv2HWIy8i+M5Xz8xRB88tBqLD3SPr\n" +
            "MoBedrHTKuqPtCWiq1ArmfPdIFFf4rBQalsba5u2G2HPfBkVYyXTACe0K2FLtpiX\n" +
            "vo7nuaiMh9cXU45wKLI/nxtLjjR8ehysVw2ICsfkg5Mk8s/GL1xF+QIDAQABo1Aw\n" +
            "TjAdBgNVHQ4EFgQUA7vcSm79IvSQVopFevZX1KFIzAUwHwYDVR0jBBgwFoAUA7vc\n" +
            "Sm79IvSQVopFevZX1KFIzAUwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQsFAAOC\n" +
            "AQEAMm9jcBymtSUI/05A+GRtQMQlfmGdqU8lpgQJ1/Km64U3oeNWGsAJfC4EQWs0\n" +
            "9PCbL2Tzso2DtfmYoYYIRZYJuzZ3GcLPBNCg5ut6ihaNBIFSnyTMJ2n1KpHMqGIZ\n" +
            "g/vZ8JKzIafT0qObwdImZi9lqwo5XLl8OXzHCJHesSuGTHSHcX0jjThKhL3ERVgc\n" +
            "jGwIQm7Uz4UicrMhOfJLpVP0E/UG3rqVH4YTcjbfFbC4lKkjY75BAV8GQ/xN9mvk\n" +
            "GzJ9DaugccvYof0WnL+83SJm8mQXhFIIzjWodDocAgc/Xo511TSRZRugITfythM6\n" +
            "1coLc8so8GjOjLb71Xovlnps/w==\n" +
            "-----END CERTIFICATE-----";


    public MainActivity() throws NoSuchAlgorithmException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().setLogo(R.drawable.itsi);

        final EditText etUname = (EditText) findViewById(R.id.et_uname);
        final EditText etPassw = (EditText) findViewById(R.id.et_passw);
        final Button bLogin = (Button) findViewById(R.id.btn_login);

        try {
            client = pinnedClient();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = etUname.getText().toString();
                String pass = etPassw.getText().toString();

                attemptLogin(url, uname, pass);
            }
        });
    }

    public OkHttpClient getClient() {
        return this.client;
    }

    public String getResponse() {
        return this.response;
    }

    public LoginInfo getObj1() {
        return this.obj1;
    }

    public void loadContent(final String username) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildUrl(username));
                    Log.d("Response:LoadContent", response);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void attemptLogin(String url, final String username, final String password) {

        new AsyncTask<String, Void, Void>() {
            protected Void doInBackground(String... params) {
                assert (params[0] != null);
                try {
                    response = ApiCall.POST(
                            client,
                            params[0],
                            RequestBuilder.LoginBody(username, password));

                    Log.d("Response", response);

                    Log.d("###LOGIN", "###BEFORE IF###");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();

                            obj1 = gson.fromJson(response, LoginInfo.class);

                            if (!obj1.success) {
                                Log.d("###LOGIN", "WRONG USER OR PASSWORD");
                                Toast.makeText(MainActivity.this, "Wrong User or Password", Toast.LENGTH_LONG).show();
                            }
                            else {
                                finalUser = obj1.user.username;
                                Intent intent = new Intent(MainActivity.this, UserAreaAct.class);
                                MainActivity.this.startActivity(intent);
                            }

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
    }


    private static SSLContext sslContextForTrustedCertificates(InputStream in) throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            Collection certificates = certFactory.generateCertificates(in);
            char[] password = "password".toCharArray();

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, password);
            for (int i = 0; i < certificates.size(); i++) {
                keyStore.setCertificateEntry(new Integer(i).toString(), (Certificate) new ArrayList(certificates).get(i));
            }
            // Wrap in SSL context
            KeyManagerFactory kmFactory = KeyManagerFactory
                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmFactory.init(keyStore, password);
            TrustManagerFactory tmFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmFactory.getKeyManagers(), tmFactory.getTrustManagers(),
                    new SecureRandom());
            return sslContext;
        }

    private static OkHttpClient pinnedClient() throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

        SSLContext sslContext = sslContextForTrustedCertificates(
                new ByteArrayInputStream(ITSIPEM.getBytes("UTF-8")));

        CertificatePinner pinner = new okhttp3.CertificatePinner.Builder()
                .add(url, "sha1/3gMBWEcd/5uQKWBUio2xcJnLCrk=")
                .add(url, "sha1/iXvcdOUn+STyrY9ra+EyHq8un1Q=")
                .build();



        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .certificatePinner(pinner)
                .cookieJar(new CookieJar() {
                    private List<Cookie> cookies = new ArrayList();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        this.cookies = cookies;
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return cookies;
                    }
                })
                .build();

        return okHttpClient;
    }

}