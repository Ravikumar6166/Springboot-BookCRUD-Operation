package com.fsg.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.SecureRandom;
import java.math.BigInteger;

public class PasswordHash {
	
	private static final Logger logger = LoggerFactory.getLogger(PasswordHash.class);
	public static final int SALT_BYTE_SIZE = 32;

	public static final String ALGORITHM = "SHA-256";
	public static final String SALT_HASH_SEPERATOR = ":";
	
	/**
	 * @param pwd the password to hash
	 * @return hashed password
	 * @throws NoSuchAlgorithmException
	 */
	public static String createHash(String pwd){
		if(pwd != null)
		return createHash(pwd.getBytes());
		
		return null;
	}

	/**
	 * @param pwdBytes the password to hash
	 * @return hashed password
	 * @throws NoSuchAlgorithmException
	 */
	public static String createHash(byte[] pwdBytes){		
		String hashedPwd = null;
		if(pwdBytes.length != 0){
			try{
				byte[] salt = createSalt();
				
				byte[] finalBytes = new byte[pwdBytes.length + salt.length];		
				System.arraycopy(salt, 0, finalBytes, 0, salt.length);
				System.arraycopy(pwdBytes, 0, finalBytes, salt.length, pwdBytes.length);
				
				MessageDigest md = MessageDigest.getInstance(ALGORITHM);
				md.update(finalBytes);
				byte[] hash = md.digest();
				
				hashedPwd =  toHex(salt)+SALT_HASH_SEPERATOR+toHex(hash);
			}catch(NoSuchAlgorithmException nsae){
				//nsae.printStackTrace();
				logger.error("No such algorithm error: ", nsae);
			}
		}
		return hashedPwd;
	}
	/**
	 * @param pwd the password to validate
	 * @param correctPwdHash hash of the correct password
	 * @return true if password matches, false if not
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean validatePassword(String pwd, String correctPwdHash){
		return validatePassword(pwd.getBytes(),correctPwdHash);
	}

	/**
	 * @param pwdBytes the password to validate
	 * @param correctPwdHash hash of the correct password
	 * @return true if password matches, false if not
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean validatePassword(byte[] pwdBytes, String correctPwdHash){		
		boolean result = false;
		if(null != correctPwdHash && pwdBytes.length != 0){
			try{
				String[] params = correctPwdHash.split(SALT_HASH_SEPERATOR);
				
				byte[] coorectSalt = fromHex(params[0]);
				byte[] correctHash = fromHex(params[1]);
				
				byte[] finalBytes = new byte[pwdBytes.length + coorectSalt.length];		
				System.arraycopy(coorectSalt, 0, finalBytes, 0, coorectSalt.length);
				System.arraycopy(pwdBytes, 0, finalBytes, coorectSalt.length, pwdBytes.length);
				
				MessageDigest md = MessageDigest.getInstance(ALGORITHM);
				md.update(finalBytes);
				byte[] computedHash = md.digest();
				
				result = MessageDigest.isEqual(computedHash, correctHash);
				//return slowEquals(correctHash, computedHash);
			}catch(NoSuchAlgorithmException nsae){
				//nsae.printStackTrace();
				logger.error("No such algorithm error: ", nsae);
			}
		}
		return result;
	}
	
	private static byte[] createSalt(){
		SecureRandom rand = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		rand.nextBytes(salt);
		return salt;
	}
	
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++){
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
	}

	private static String toHex(byte[] bytes) {
		BigInteger bi = new BigInteger(1, bytes);
        String hex = bi.toString(16);
        
        int paddingLength = (bytes.length * 2) - hex.length();
        if(paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
	}
	
	public static void main(String[] args){
		String hash = PasswordHash.createHash("welcome1");
		System.out.println(hash);
		//System.out.println("validate: "+PasswordHash.validatePassword("null", hash));
		System.out.println("validate: "+PasswordHash.validatePassword("welcome1", "971cbb862b051c97f9d411c83892d43ff629863c559176d8a8049248ce70681f:717ba73c73692d4f376a99478efc03b69386c0f90ff2da6a0a95a6cb5417cf31"));
	}

}
