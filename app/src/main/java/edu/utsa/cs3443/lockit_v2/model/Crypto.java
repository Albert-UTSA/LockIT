package edu.utsa.cs3443.lockit_v2.model;
/**
 * Crypto
 *
 * handles the notes security the cryptologic encrypts note and uses the users password to decrypt it
 */
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Encrypts and decrypts notes after the user creates or edits a specific note.
 */
public class Crypto {

    private static final int AES_KEY_SIZE = 256;
    private static final int AES_BLOCK_SIZE = 128;
    private static final int AES_IV_SIZE = AES_BLOCK_SIZE / 8;

    /**
     * Genterates AES key to be used for encrypting and decrypting notes.
     *
     * @param uses a passphrase that is a String to generate the key.
     * @return a key will be returned from the method that will be used for encrypting and decrypting notes.
     */
    public static SecretKey generateAESKey(String passphrase) throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] salt = new byte[16];

        SecretKeySpec keySpec = new SecretKeySpec(passphrase.getBytes(), "AES");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec pbeKeySpec = new PBEKeySpec(passphrase.toCharArray(), salt, 1000, AES_KEY_SIZE);
        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
        return secretKey;

    }

    /**
     * Encrypts notes
     *
     * @param takes a string representation of the note and uses the users password to generate key
     * @return outputs encrypted string of the note
     */

    public static String encrypt(String input, String password) {
        try {
            SecretKey key = generateAESKey(password);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[AES_IV_SIZE];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encrypted = cipher.doFinal(input.getBytes());
            byte[] ciphertext = new byte[AES_IV_SIZE + encrypted.length];
            System.arraycopy(iv, 0, ciphertext, 0, AES_IV_SIZE);
            System.arraycopy(encrypted, 0, ciphertext, AES_IV_SIZE, encrypted.length);
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Exception e){
            return "ERROR";
        }

    }
    /**
     * Decrypts notes
     *
     * @param takes the chiphertext of the encrypted note and uses the users password to decrypt it
     * @return outputs decrypted string of the note
     */
    public static String decrypt(String ciphertext, String password) {
        try {
            SecretKey key = generateAESKey(password);

            byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
            byte[] iv = new byte[AES_IV_SIZE];
            System.arraycopy(ciphertextBytes, 0, iv, 0, AES_IV_SIZE);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            byte[] encrypted = new byte[ciphertextBytes.length - AES_IV_SIZE];
            System.arraycopy(ciphertextBytes, AES_IV_SIZE, encrypted, 0, encrypted.length);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted);
        } catch (Exception e){
            return "ERROR";
        }
    }

}
