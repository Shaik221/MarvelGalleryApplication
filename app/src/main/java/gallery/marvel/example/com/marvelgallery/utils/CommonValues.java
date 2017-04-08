package gallery.marvel.example.com.marvelgallery.utils;


public class CommonValues {

    /* Server-side applications must pass two parameters in addition to the apikey parameter:
        ts - a timestamp (or other long string which can change on a request-by-request basis)
        hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
    **/
    public static String HASH_VALUE = "359e14db6b6a7bed5c31d81b2c00f36b";
    public static String TS = "1";
    public static String PUBLIC_KEY = "54306733de0f5cd1418aa05a85fa062a";
}
