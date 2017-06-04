package com.chang.crypto;

import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 * @author Nagaraju
 *
 */
public class CryptoUtil {
	public static final String USAGE_ENCRYPTION = "ENCRYPTION";
	public static final String USAGE_CERTIFICATION = "CERTIFICATION";
	public static final String RSA_ALG = "RSA";
	public static final int RSA_KEY_LENGTH = 1024;
	private static Provider provider = null;
	//private static final String ALGORITHM = "SHA1PRNG";
	private static final String KEY_PASSWORD = "myp@$$w0rd";
	
	private static Provider getProvider(){
		if (provider == null) {
			provider =  new BouncyCastleProvider();
		}
		return provider;
	}
	
	public static CryptoKey generateSecurityKeys()throws Exception {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALG);
		keyGen.initialize(RSA_KEY_LENGTH);
		KeyPair keyPair = keyGen.genKeyPair();
		CryptoKey secretkeys = new CryptoKey();
		secretkeys.setKeyPair(keyPair);
		secretkeys.setKeyUsage(USAGE_ENCRYPTION);
		secretkeys.setPrivateKey(new String(Base64.encode(keyPair.getPrivate().getEncoded())));
		secretkeys.setPublicKey(new String(Base64.encode(keyPair.getPublic().getEncoded())));
		return secretkeys;
	}	
		
	/**
	 * @param orgName
	 * @return
	 * @throws Exception
	 */
	public static CryptoKey generateOrgPKI(String orgName)throws Exception {
		
		//KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALG, "SunRsaSign");
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA_ALG, getProvider());
		keyGen.initialize(RSA_KEY_LENGTH);
		KeyPair keyPair = keyGen.genKeyPair();
		
		CryptoKey orgPKIKey = new CryptoKey();
		orgPKIKey.setKeyPair(keyPair);
		orgPKIKey.setOrgName(orgName.toUpperCase());
		orgPKIKey.setKeyUsage(USAGE_ENCRYPTION);
		orgPKIKey.setPrivateKey(new String(Base64.encode(keyPair.getPrivate().getEncoded())));
		orgPKIKey.setPublicKey(new String(Base64.encode(keyPair.getPublic().getEncoded())));
		return orgPKIKey;
	}
	
	/**
	 * Encrypt a text using public key. 
	 * @param plainData
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] plainData, String b64PublicKey) throws Exception {
		PublicKey key = getPublicKeyFromString(b64PublicKey);
		Cipher ecipher = Cipher.getInstance(key.getAlgorithm(), getProvider());
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encBytes = ecipher.doFinal(plainData);
		return encBytes;
	}
	
	/**
	 * Encrypt a text using public key. 
	 * @param plainData
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptUsingSunProvider(byte[] plainData, String b64PublicKey) throws Exception {
		PublicKey key = getPublicKeyFromString(b64PublicKey);
		Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encBytes = ecipher.doFinal(plainData);
		return encBytes;
	}
	
	/**
	 * Decrypt text using private key
	 * @param encData
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] encData, String b64PrivateKey) throws Exception {
		PrivateKey key = getPrivateKeyFromString(b64PrivateKey);
		Cipher dcipher = Cipher.getInstance(key.getAlgorithm(), getProvider());
		dcipher.init(Cipher.DECRYPT_MODE, key);
		byte[] encBytes = dcipher.doFinal(encData);
		return encBytes;
	}
	
	
	 /**
     * Generates Private Key from BASE64 encoded string
     * @param key BASE64 encoded string which represents the key
     * @return The PrivateKey
     * @throws java.lang.Exception
     */
    public static PrivateKey getPrivateKeyFromString(String key) throws Exception
    {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALG);
//        BASE64Decoder base64Decoder = new BASE64Decoder();
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(key));
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        return privateKey;
    }

    /**
     * Generates Public Key from BASE64 encoded string
     * @param key BASE64 encoded string which represents the key
     * @return The PublicKey
     * @throws java.lang.Exception
     */
    public static PublicKey getPublicKeyFromString(String key) throws Exception
    {
//        BASE64Decoder base64Decoder = new BASE64Decoder();
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALG);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decode(key));
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return publicKey;
    }
    
    /**
     * 
     * @param passPhrase
     * @param salt
     * @return
     */
    public static String encryptPassword(String passPhrase, byte[] salt){
    	Encrypter lyrisEncrypter = new Encrypter(KEY_PASSWORD, salt);
    	synchronized (lyrisEncrypter) {
			return lyrisEncrypter.encrypt(passPhrase);
		}
    }
    
    /**
     * 
     * @param cipherText
     * @param salt
     * @return
     */
    public static String decryptPassword(String cipherText, byte[] salt){
    	Encrypter lyrisEncrypter = new Encrypter(KEY_PASSWORD, salt);
    	synchronized (lyrisEncrypter) {
			return lyrisEncrypter.decrypt(cipherText);
		}
    }
    	
    //FIXME: Remove following code
    public static void main(String[] args) {
		try {
			
			/* SALT CREATION START */
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // Salt generation 64 bits long
            byte[] bSalt = new byte[8];
            random.nextBytes(bSalt);
            String salt = new String(Base64.encode(bSalt));
            /* SALT CREATION END */
            
            
            String ss = "6tnF0yuiLn4=";
            byte[] updatedSalt = Base64.decode(ss);
            String enPass = CryptoUtil.encryptPassword("admin", updatedSalt);
            System.out.println("CryptoUtil.main()  EnCrypt PASS "+enPass);
           
           
            byte[] newSalt = Base64.decode(ss);
            System.out.println("CryptoUtil.main() password  "+CryptoUtil.decryptPassword(enPass, newSalt));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
