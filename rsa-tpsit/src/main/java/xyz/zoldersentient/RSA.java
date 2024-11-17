package xyz.zoldersentient;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RSA {
    private static final Logger log = LogManager.getLogger(RSA.class);
    private static BigInteger n, d, e;
    private final int BIT_LENGTH = 1024;

    public RSA() {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        log.debug("Generated 'p' prime number: {}", p);
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        log.debug("Generated 'q' prime number: {}", q);

        n = p.multiply(q);
        log.debug("Generated 'n' number: {}", n);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        log.debug("Generated 'phi': {}", phi);

        e = BigInteger.probablePrime(BIT_LENGTH / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        log.debug("Generated 'e': {}", e);

        d = e.modInverse(phi);
        log.debug("Calculated the 'd' multiplicative inverse: {}", d);
    }

    public static BigInteger encrypt(BigInteger message){
        log.info("Encrypted string");
        return message.modPow(e, n);
     }

     public static BigInteger decrypt(BigInteger encrypted) {
        log.info("Decrypted string");
        return encrypted.modPow(d, n);
     }
}
