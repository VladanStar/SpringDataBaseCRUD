package com.vladancupric;



import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		OffersDao offersDao = (OffersDao)context.getBean("offersDao");
		
			List<Offer> batchOffers = new ArrayList<Offer>();
			batchOffers.add(new Offer (29, "Pera Peric", "pera@engineering.com", "SQL"));
			batchOffers.add(new Offer (32, "Jovan Jovanovic", "jovan@singidunum.ar.rs", "Oracle"));
			batchOffers.add(new Offer (38, "Lazar Lazarevic", "laza@singidunum.ac.rs", "HTML CSS"));
			
			int[] returnvalues = offersDao.create(batchOffers);
			
			for (int value : returnvalues){
				System.out.println("Updated " + value + " rows.");
			}
		
		//Offer entry = new Offer(12,"Vladimir Dobricc", "vdobic@gmail.com","MySQL Developer");
		
				
	/*	try {
		offersDao.create(entry);
		}
		catch(DuplicateKeyException ex) {
			System.out.println("Pokusaj unosa vec postojeceg id u bazu podataka");
		}
		catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		//offersDao.delete(4);
		*/
		Offer o = new Offer(17,"Miroslav Markovic","mirmarko@gmail.com","JavaScript developer Senior");
		offersDao.update(o);
		List<Offer> offers = offersDao.getOffers();
		for(Offer offer: offers) {
			System.out.println(offer);
			
			
		}
		Offer off = offersDao.getOfferbyId(3);
		System.out.println("Dohvatanje pojedinacnog objekta");
		System.out.println(off);
		
		
		
		((FileSystemXmlApplicationContext)context).close();

	}

}
