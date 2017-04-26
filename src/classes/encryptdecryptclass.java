
package classes;

/**
 *
 * @author Lucky Pratama
 */

//importing classess and interface needed

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import sun.misc.BASE64Encoder;

public class encryptdecryptclass {
    
    
    //Create input variable 
    private String input; 
    private KeyGenerator keygen;
    private SecretKey secretkey;
    private Cipher allCipher;
    private byte[] plaintext;
    private byte[] ciphertext;
    private byte[] decrypttext;
    private String strciphertext;
    private String strdecrypttext;
    private int len;
    private int max_len;
    
    // Create constructor
    public encryptdecryptclass(String input, String al) throws Exception{
        // Generate Key
        keygen = KeyGenerator.getInstance(al);
        secretkey = keygen.generateKey();
        
        // Parameter Setting
        allCipher = Cipher.getInstance(al+"/CBC/PKCS5Padding");
        allCipher.init(Cipher.ENCRYPT_MODE, secretkey);
        
        //Create Plaintext
        plaintext = input.getBytes();   
    }
    
    //Getter method to return encryption result
    public String encrypt() throws Exception{
        //Start encrypt
        ciphertext = allCipher.doFinal(plaintext);
        
        //Covert the encryption output to string
        strciphertext = new BASE64Encoder().encode(ciphertext);
        
    return strciphertext;
    }
    
    //Getter method to return the decryption result
    public String decrypt() throws Exception{
        //Parameter Setting
        allCipher.init(Cipher.DECRYPT_MODE, secretkey, allCipher.getParameters());
        
        //Start Decrypt
        decrypttext = allCipher.doFinal(ciphertext);
        
        //Convert the decryption result to string
        strdecrypttext = new String(decrypttext);
        
        return strdecrypttext;
    }
    
    //Getter method to return the encryption output (key) length
    public int len_key(){
        //Convert the encryption key to integer
        len = strciphertext.length();
        
        return len;
    }
    
    //Getter method to return the maximum key length of DES Algorithm
    public int max_len(String in) throws Exception{
        max_len = Cipher.getMaxAllowedKeyLength(in);
        
        return max_len;
    }
    
}
