package com.lou.auth_okhttp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private static OkHttpClient client, client2;

    private static String response;

    private static CheckLogin obj2;
    private static LoginInfo obj1;
    private static Schools objSchools;
    private static School[] arr;
    private static String[] nameArr, dnsArr;
    private static final String[] names = new String[] {
            "Willie", "Pieter", "Kosie", "Gerhard", "Jannie", "Wilhelm"
    };
    final Context context = this;

    //public static String finalUser;

    private static String url = "https://app.dev.it.si/alchemy/api/1.0/login";

    private static String schools = "https://schools.cdn.it.si/schools.json";
    //a248.e.akamai.net

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


    public MainActivity() throws NoSuchAlgorithmException, IOException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.miebooks);*/

        final EditText etUname = (EditText) findViewById(R.id.et_uname);
        final EditText etPassw = (EditText) findViewById(R.id.et_passw);
        final Button bLogin = (Button) findViewById(R.id.btn_login);
        final AutoCompleteTextView autoTxt = (AutoCompleteTextView) findViewById(R.id.auto_txt);

        try {
            client = pinnedClient(ITSIPEM);
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

        Log.d("CHECK CHECK", "START OF ON CREATE");

        client2 = getUnsafeOkHttpClient();

        Log.d("CHECK CHECK", "BEFORE GET SCHOOLS");

        getSchools(schools, client2);


        Log.d("CHECK CHECK", "AFTER GET SCHOOLS");

        /*for (int i = 0; i < objSchools.schools.size(); i++) {
            //arr[i] = objSchools.schools.get(i);
            //Log.d("###SCHOOLS", arr[i].name);
            //nameArr[i] = arr[i].name;
            Log.d("ON_CREATE", nameArr[i]);
        }*/


        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nameArr);
        AutoCompleteTextView autoTxt = (AutoCompleteTextView) findViewById(R.id.auto_txt);
        autoTxt.setThreshold(1);
        autoTxt.setAdapter(adapter);*/


        /*for (int i = 0; i < objSchools.schools.size(); i++) {
            arr[i] = objSchools.schools.get(i);
            Log.d("###SCHOOLS", arr[i].name);
        }*/

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = etUname.getText().toString();
                String pass = etPassw.getText().toString();

                attemptLogin(client, url, uname, pass);
            }
        });
    }

    public OkHttpClient getClient() {
        return this.client;
    }

    public LoginInfo getObj1() {
        return this.obj1;
    }

    public void getSchools(final String schools, final OkHttpClient client) {
        new AsyncTask<Void, Void, Schools>() {
            @Override
            protected Schools doInBackground(Void... params) {
                Log.d("###WIELE###", "!@#$%^&*()(*&^%$#@#$%^&**&^%$#@#$%^*%*&%*%^&^%&^%&%&^%&%&^%");
                try {
                    response = ApiCall.GET(client, schools);
                    Log.d("Response:LoadContent", response);

                    Gson gson = new Gson();
                    objSchools = gson.fromJson(response, Schools.class);

                    arr = objSchools.schools.toArray(new School[objSchools.schools.size()]);

                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ERROR TERROR", e.toString());
                }
                return objSchools;
            }

            @Override
            protected void onPostExecute(Schools schools1) {
                super.onPostExecute(schools1);
                updateSchools(schools1);

            }


        }.execute();

    }

    private void autoComplete() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, nameArr);
        AutoCompleteTextView autoTxt = (AutoCompleteTextView) findViewById(R.id.auto_txt);
        autoTxt.setThreshold(1);
        autoTxt.setAdapter(adapter);
    }

    private void updateSchools(final Schools schools) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //arr = schools.schools.toArray(new School[schools.schools.size()]);

                nameArr = new String[schools.schools.size()];
                dnsArr = new String[schools.schools.size()];

                for (int i = 0; i < schools.schools.size(); i++) {
                    arr[i] = schools.schools.get(i);
                    Log.d("###SCHOOLS", arr[i].name);
                    nameArr[i] = arr[i].name;
                    dnsArr[i] = arr[i].dns;
                    Log.d("NAME_ARR", dnsArr[i]);
                }
                autoComplete();
            }
        });
    }

    private void attemptLogin(final OkHttpClient client, String url, final String username, final String password) {

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

                            /*AlertDialog.Builder alert = new AlertDialog.Builder(context);
                            alert.setTitle("Result").setCancelable(true);*/

                            obj2 = gson.fromJson(response, CheckLogin.class);

                            ProgressDialog progress = new ProgressDialog(MainActivity.this);
                            progress.setTitle("Checking");
                            progress.setMessage("Attempting Login");
                            progress.setCancelable(true);
                            progress.show();

                            if (!obj2.success) {
                                Log.d("###LOGIN", "WRONG USER");
                                progress.dismiss();
                                showAlert(obj2.info);
                            }
                            else {

                                obj1 = gson.fromJson(response, LoginInfo.class);
                                progress.dismiss();

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

    public void showAlert(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Result").setCancelable(true);
        //Log.wtf("CHECK CHECK", "CHECK_AUTH");
        alert.setMessage(msg);
        alert.create();
        alert.show();
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

    private static OkHttpClient pinnedClient(final String cert) throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

        SSLContext sslContext = sslContextForTrustedCertificates(
                new ByteArrayInputStream(cert.getBytes("UTF-8")));

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

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}