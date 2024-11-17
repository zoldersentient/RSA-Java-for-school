package xyz.zoldersentient;

import java.math.BigInteger;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        RSA rsa = new RSA();

        System.out.println("Insert string to encrypt: ");
        String s = input.nextLine();

        BigInteger crypted = rsa.encrypt(new BigInteger(s.getBytes()));

        logger.info(new String(rsa.decrypt(crypted).toByteArray()));
    }
}