package com.bbllguru.wiki.page.util;

import com.bbllguru.wiki.page.exception.BaseException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashStore {

	protected static Logger logger = LoggerFactory.getLogger(HashStore.class);

	public static String hash(String algorithm, String title) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] hash = md.digest(title.getBytes("UTF-8"));

			StringBuilder sb = new StringBuilder(2*hash.length);
			for(byte b : hash){
				sb.append(String.format("%02x", b&0xff));
			}
			return sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			logger.error(e.toString());
		}

		throw new BaseException("Could not generate hash from algorithm: " + algorithm + ", title: " + title);
	}

}
