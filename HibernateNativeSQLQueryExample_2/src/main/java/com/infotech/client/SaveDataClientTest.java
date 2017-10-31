package com.infotech.client;

import java.util.Date;

import org.hibernate.Session;

import com.infotech.entities.Partner;
import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Person person1 = new Person();
			person1.setName("Mark Bingel");
			person1.setNickName("Mac");
			person1.setAddress("Alameda Street Los Angeles");
			person1.setCreatedOn(new Date());
			person1.setVersion(1);
			
			
			Person person2 = new Person();
			person2.setName("Sean Murphy");
			person2.setNickName("Sam");
			person2.setAddress("Bank of Canada,234 Wellington Street");
			person2.setCreatedOn(new Date());
			person2.setVersion(1);
			
			Partner partner = new Partner();
			partner.setName("Sean Murphy");
			partner.setVersion(1);
			
			session.save(person1);
			session.save(person2);
			session.save(partner);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
