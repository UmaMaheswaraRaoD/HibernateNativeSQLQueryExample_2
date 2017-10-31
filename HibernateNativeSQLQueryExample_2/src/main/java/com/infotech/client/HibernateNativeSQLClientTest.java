package com.infotech.client;

import java.util.List;

import org.hibernate.Session;

import com.infotech.entities.Partner;
import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class HibernateNativeSQLClientTest {

	public static void main(String[] args) {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			//selectEntitiesWithSameColumnNamesV1(session);
			selectEntitiesWithSameColumnNamesV2(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private static void selectEntitiesWithSameColumnNamesV1(Session session) {
		@SuppressWarnings({ "unused", "unchecked" })
		List<Object> list = session
				.createNativeQuery("SELECT * " + "FROM Person pr, Partner pt " +
		"WHERE pr.name = pt.name").list();
		
		System.out.println(list);
	}
	private static void selectEntitiesWithSameColumnNamesV2(Session session) {
		@SuppressWarnings("unchecked")
		List<Object[]> list = session.createNativeQuery(
			    "SELECT {pr.*}, {pt.*} " +
			    "FROM Person pr, Partner pt " +
			    "WHERE pr.name = pt.name" )
			.addEntity( "pr", Person.class)
			.addEntity( "pt", Partner.class)
			.list();
		
		for (Object[] objects : list) {
			System.out.println((Person)objects[0]);
			System.out.println((Partner)objects[1]);
		}
	}
}
