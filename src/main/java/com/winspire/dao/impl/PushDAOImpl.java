package com.winspire.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.winspire.dao.PushDAO;
import com.winspire.entity.FCMTokens;
import com.winspire.entity.NotificationDetails;
import com.winspire.entity.Questionnaire;
import com.winspire.entity.UserDetails;

@Repository


public class PushDAOImpl implements PushDAO {
	@Autowired
	
	private SessionFactory sessionFactory;
	


	


	@Override

	public List<FCMTokens> getAllFCMTokens() {
		System.out.println("query:"+sessionFactory );
		System.out.println("getAllFCMTokens:" );
		String hql = "";
		hql = " From FCMTokens ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		System.out.println("query:"+query );
		List<FCMTokens> listr =  query.list();
		System.out.println("listr:" +listr);		
	
		
		return listr;
	}


	@Override

	public List<NotificationDetails> getNotificationDetails() {
		System.out.println("query:"+sessionFactory );
		// TODO Auto-generated method stub
		String hql = "";
		hql = " From NotificationDetails ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		System.out.println("query:"+query );
		List<NotificationDetails> listr =  query.list();
		System.out.println("listr:" +listr);
		return listr;
	}


	@Override
	public void insertFcmToken(FCMTokens tokenObj) {
		// TODO Auto-generated method stub	
		sessionFactory.getCurrentSession().saveOrUpdate(tokenObj);
	}


	@Override
	public void registerUser(UserDetails user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}


	@Override
	public FCMTokens getFCMDetails(String token) {
	
		String hql = "";
		hql = " From FCMTokens where  FCMToken=:token order by CreatedDate desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		query.setParameter("token", token);
		System.out.println("query:"+query );
		query.setMaxResults(1);
		FCMTokens listr =  (FCMTokens) query.uniqueResult();
		System.out.println("listr:" +listr);
		return listr;
	}


	@Override
	public List<Questionnaire> getQuestionnaire(int i, String type) {
		// TODO Auto-generated method stub
	
		String hql = "";
		hql = " From Questionnaire where Type=:type and  SID=:sid";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);	
		
		query.setParameter("type", type);
		query.setParameter("sid", i);
		query.setMaxResults(10);
		List<Questionnaire> listr =  query.list();
		
		return listr;
	}





	

	



}
