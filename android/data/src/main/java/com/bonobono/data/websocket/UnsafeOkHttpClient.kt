package com.bonobono.data.websocket

import android.R
import android.content.Context
import okhttp3.OkHttpClient
import java.io.IOException
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


class UnsafeOkHttpClient {
    /**
     * Retrofit SSL 우회 접속 통신
     */
    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }

        return builder
    }
//
//    fun getPinnedCertSslSocketFactory(context: Context): SSLSocketFactory? {
//        try {
//            val cf = CertificateFactory.getInstance("X.509")
//            val caInput = context.resources.openRawResource(R.raw.localhost)
//            var ca: Certificate? = null
//            try {
//                ca = cf.generateCertificate(caInput)
//                println("ca=" + (ca as X509Certificate?)!!.subjectDN)
//            } catch (e: CertificateException) {
//                e.printStackTrace()
//            } finally {
//                caInput.close()
//            }
//            val keyStoreType = KeyStore.getDefaultType()
//            val keyStore = KeyStore.getInstance(keyStoreType)
//            keyStore.load(null, null)
//            if (ca == null) {
//                return null
//            }
//            keyStore.setCertificateEntry("ca", ca)
//            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
//            val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
//            tmf.init(keyStore)
//            val sslContext = SSLContext.getInstance("TLS")
//            sslContext.init(null, tmf.trustManagers, null)
//            return sslContext.socketFactory
//        } catch (e: NoSuchAlgorithmException) {
//            e.printStackTrace()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } catch (e: KeyStoreException) {
//            e.printStackTrace()
//        } catch (e: KeyManagementException) {
//            e.printStackTrace()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }

}