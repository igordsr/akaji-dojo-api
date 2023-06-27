package br.com.akaji.dojo;

import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AkajiTest {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		System.out.println(Calendar.getInstance().getTime());
	}

}
