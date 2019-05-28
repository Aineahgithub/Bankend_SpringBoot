package com.itf.schulung.springboot.fullstack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static Customer getCustomer() {
		return new Customer(getRandomlastName(), getRandomfirstName(),getRandomAccounts() );
	}

	public static String getRandomlastName() {
		List<String> silben = new ArrayList<String>(); // diese Liste wird mit [ba, be, bi, bo, bu, ca, ce,...] befuellt

		for (char c = 'a'; c <= 'z'; c++) {
			if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'q' && c != 'z' && c != 'x') {
				silben.add(c + "a");
				silben.add(c + "e");
				silben.add(c + "i");
				silben.add(c + "o");
				silben.add(c + "u");
			} else if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				silben.add(c + "");
			}
		}

		String lastName = (silben.get(ThreadLocalRandom.current().nextInt(silben.size()))).substring(0, 1).toUpperCase()
				+ (silben.get(ThreadLocalRandom.current().nextInt(silben.size()))).substring(1);

		int random = ThreadLocalRandom.current().nextInt(2, 5);
		for (int i = 0; i < random; i++) {
			lastName += silben.get(ThreadLocalRandom.current().nextInt(silben.size()));
		}

		return lastName;
	}

	public static String getRandomfirstName() {
		List<String> silben = new ArrayList<String>(); // list of consonants and vowels e.g ba, be etc..

		for (char c = 'a'; c <= 'z'; c++) {
			if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'q' && c != 'z' && c != 'x') {
				silben.add(c + "a");
				silben.add(c + "e");
				silben.add(c + "i");
				silben.add(c + "o");
				silben.add(c + "u");
			} else if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				silben.add(c + "");
			}
		}

		String firstName = (silben.get(ThreadLocalRandom.current().nextInt(silben.size()))).substring(0, 1)
				.toUpperCase() + (silben.get(ThreadLocalRandom.current().nextInt(silben.size()))).substring(1);

		int random = ThreadLocalRandom.current().nextInt(1, 3);
		for (int i = 0; i < random; i++) {
			firstName += silben.get(ThreadLocalRandom.current().nextInt(silben.size()));
		}

		return firstName;
	}

	public static String getRandomAccounts() {

		Random r = new Random();
		final String accountnr = ("10000000123456789");
		final String countryCode = ("DE23");
		StringBuilder a = new StringBuilder();
		a.append(countryCode);
		for (int i = 0; i <= accountnr.length(); i++) {
			a.append(accountnr.charAt(r.nextInt(accountnr.length())));
		}
		return a.toString();

	}

}
