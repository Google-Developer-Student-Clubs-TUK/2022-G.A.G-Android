/*
 * Created by PangMoo on 2022/12/16
 */

package com.haeyum.schoolmate.utils

import android.util.Base64
import com.google.android.gms.common.util.Base64Utils
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class EncryptUtil {
    @Throws(Exception::class)
    fun encryptCBC(str: String, secretKey: String, secretIv: String): String {
        val key = SecretKeySpec(secretKey.toByteArray(), "AES")
        val iv = IvParameterSpec(secretIv.toByteArray())
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)

        val encrypted = cipher.doFinal(str.toByteArray())
        val encodedByte = Base64.encode(encrypted, Base64.DEFAULT)

        return String(encodedByte)
    }

    fun decryptCBC(str: String, secretKey: String, secretIv: String): String {
        val key = SecretKeySpec(secretKey.toByteArray(), "AES")
        val iv = IvParameterSpec(secretIv.toByteArray())

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, iv)

        val decodedByte = Base64.decode(str, Base64.DEFAULT)
        val byteResult = cipher.doFinal(decodedByte)

        return String(byteResult)
    }

    fun encryptRSA(input: String, key: PublicKey): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encrypt = cipher.doFinal(input.toByteArray())
        return Base64Utils.encode(encrypt)
    }

    fun encryptRSAHex(input: String, key: PublicKey): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encrypt = cipher.doFinal(input.toByteArray())
        return encrypt.joinToString("") { String.format("%02x", it) }
    }

    fun decryptRSA(input: String, key: PrivateKey): String {
        val byteEncrypt: ByteArray = Base64Utils.decode(input)
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decrypt = cipher.doFinal(byteEncrypt)
        return String(decrypt)
    }

    fun convertStringToPublicKey(publicKeyStr: String): PublicKey? {
        // convert pem public key string to PublicKey
        val publicKeyBytes = Base64.decode(publicKeyStr, Base64.DEFAULT)
        val keySpec = X509EncodedKeySpec(publicKeyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePublic(keySpec)
    }

    fun convertStringToPrivateKey(privateKeyStr: String): PrivateKey? {
        val keyBytes = Base64.decode(privateKeyStr.replace("-----BEGIN PUBLIC KEY-----", "")
            .filterNot { it == '\n' }
            .replace("-----END PUBLIC KEY-----", ""), Base64.DEFAULT)
        val spec = X509EncodedKeySpec(keyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        return keyFactory.generatePrivate(spec)
    }

    fun convertPemStringToPublicKey(pemString: String): PublicKey {
        val pemBytes = java.util.Base64.getDecoder().decode(pemString)
        val keySpec = X509EncodedKeySpec(pemBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKey = keyFactory.generatePublic(keySpec) as RSAPublicKey

        return publicKey
    }

//    fun a(): PublicKey{
//        var privateKeyContent = ""
//        var publicKeyContent = ""
//
//        privateKeyContent = privateKeyContent.replace("\\n".toRegex(), "")
//            .replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "")
//        publicKeyContent =
//            publicKeyContent.replace("\\n".toRegex(), "").replace("-----BEGIN PUBLIC KEY-----", "")
//                .replace("-----END PUBLIC KEY-----", "")
//
//        val kf = KeyFactory.getInstance("RSA")
//
//        val keySpecPKCS8 = PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent))
//        val privKey = kf.generatePrivate(keySpecPKCS8)
//
//        val keySpecX509 = X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent))
//        val pubKey = kf.generatePublic(keySpecX509)
//
//        return pubKey
//    }

//    fun generateRSAKeyPair(): KeyPair {
//        val keygen = KeyPairGenerator.getInstance("RSA")
//        keygen.initialize(2048, SecureRandom())
//
//        val keyPair = keygen.genKeyPair()
//        return keyPair
//    }
}