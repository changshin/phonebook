package com.chang.crypto;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import javax.crypto.SecretKey;

/**
 * @author Nagaraju
 *
 */
public class CryptoKey {

	private String orgName ;
	private String keyUsage ; 
	private String privateKey ;
	private String publicKey ;
	private byte[] cert ;
	private SecretKey secretKey ;
	private KeyPair keyPair ;
	private X509Certificate x509Cert;
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	/**
	 * @return the keyUsage
	 */
	public String getKeyUsage() {
		return keyUsage;
	}
	/**
	 * @param keyUsage the keyUsage to set
	 */
	public void setKeyUsage(String keyUsage) {
		this.keyUsage = keyUsage;
	}
	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}
	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	/**
	 * @return the cert
	 */
	public byte[] getCert() {
		return cert;
	}
	/**
	 * @param cert the cert to set
	 */
	public void setCert(byte[] cert) {
		this.cert = cert;
	}
	/**
	 * @return the secretKey
	 */
	public SecretKey getSecretKey() {
		return secretKey;
	}
	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}
	/**
	 * @return the keyPair
	 */
	public KeyPair getKeyPair() {
		return keyPair;
	}
	/**
	 * @param keyPair the keyPair to set
	 */
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
	/**
	 * @return the x509Cert
	 */
	public X509Certificate getX509Cert() {
		return x509Cert;
	}
	/**
	 * @param x509Cert the x509Cert to set
	 */
	public void setX509Cert(X509Certificate x509Cert) {
		this.x509Cert = x509Cert;
	}
}