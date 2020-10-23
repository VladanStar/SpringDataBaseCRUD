package com.vladancupric;



import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		OffersDao offersDao = (OffersDao)context.getBean("offersDao");
		
		Offer entry = new Offer(12,"Vladimir Dobricc", "vdobic@gmail.com","MySQL Developer");
		
				
		
		offersDao.create(entry);
		
		//offersDao.delete(4);
		
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
