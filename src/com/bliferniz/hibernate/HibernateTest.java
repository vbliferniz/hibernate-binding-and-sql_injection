/**
 * 
 */
package com.bliferniz.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bliferniz.dto.UserDetails;

public class HibernateTest {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {

		Session session = factory.openSession();
		session.beginTransaction();
		
		String minUserId = "5";
		String userName = "User8";
		
		Query query = session.createQuery("from UserDetails where userId > ? and userName = :userName");
		query.setInteger(0, Integer.parseInt(minUserId)); //Substituting the "?" sign with "minUserId" value.
														  //first parameter is the position of value to insert. 	
		query.setString("userName", userName); 
		
		List<UserDetails> users = (List<UserDetails>) query.list();
		
		session.getTransaction().commit();
		session.close();

		for(UserDetails user : users){
			System.out.println(user.getUserId() + " : " + user.getUserName());
		}		
	}

	
}
