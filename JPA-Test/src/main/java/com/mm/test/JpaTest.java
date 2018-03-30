package com.mm.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mm.dao.UserDao;
import com.mm.pojo.User;
import com.querydsl.jpa.impl.JPAQuery;

@Service
@Transactional
public class JpaTest {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private UserDao userDao;
	public void testAdd(User u){
		userDao.saveAndFlush(u);
	}
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		JpaTest jpaTest = (JpaTest) ctx.getBean("jpaTest");
		EntityManagerFactory entityFactory = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		EntityManager em = entityFactory.createEntityManager();
		User u = new User();
		u.setUuid(1);
		u.setAge("1");
		u.setName("aaa");
		jpaTest.testAdd(u);
		
		JPAQuery<Object> query = new JPAQuery<>(em);
		List<Object[]> channelList = em.createNativeQuery("select distinct"
				+ " compare_channel ,channel_desc from ccs_compared_back").getResultList();
		for (Object[] objects : channelList) {
			System.out.println(objects[0]+","+objects[1]);
		}
		
		Query succQuery = em.createNativeQuery("select "
				+ " compare_channel ,channel_desc,count(txn_amt),count(count) from ccs_compared_back"
				+ " where return_code = ? group by compare_channel,channel_desc");
		succQuery.setParameter(1, 1);
		List<Object[]> succList = succQuery.getResultList();
		
		Query failQuery = em.createNativeQuery("select "
				+ " compare_channel ,channel_desc,count(txn_amt),count(count) from ccs_compared_back"
				+ " where return_code = ? group by compare_channel,channel_desc");
		succQuery.setParameter(1, 2);
		List<Object[]> failList = failQuery.getResultList();
		for (Object[] objects : channelList) {
			comparedBackCount backCount = new comparedBackCount();
			String compareChannel = (String) objects[0];
			backCount.setCompareChannel(compareChannel);
			backCount.setChannelDesc((String)objects[1]);
//			计算成功、失败的笔数和金额
			for (Object[] succ : succList) {
				if(succ[0].equals(compareChannel)){
					backCount.setRepaySuccAmt(Integer.parseInt((String) succ[2]));
					backCount.setRepaySuccCount(Integer.parseInt((String) succ[3]));
					break;
				}
			}
			for (Object[] fail : failList) {
				if(fail[0].equals(compareChannel)){
					backCount.setRepaySuccAmt(Integer.parseInt((String) fail[2]));
					backCount.setRepaySuccCount(Integer.parseInt((String) fail[3]));
					break;
				}
			}
			em.persist(backCount);
		}
		
			
		
		
		
	}
	static class Channel{
		private String compareChannel;
		private String channelDesc;
		public String getCompareChannel() {
			return compareChannel;
		}
		public void setCompareChannel(String compareChannel) {
			this.compareChannel = compareChannel;
		}
		public String getChannelDesc() {
			return channelDesc;
		}
		public void setChannelDesc(String channelDesc) {
			this.channelDesc = channelDesc;
		}
		
	}
	static class comparedBackCount{
		private String compareChannel;
		private String channelDesc;
		private int repaySuccAmt;
		private int repaySuccCount;
		private int repayFailAmt;
		private int repayFailCount;
		
		public String getCompareChannel() {
			return compareChannel;
		}
		public void setCompareChannel(String compareChannel) {
			this.compareChannel = compareChannel;
		}
		public String getChannelDesc() {
			return channelDesc;
		}
		public void setChannelDesc(String channelDesc) {
			this.channelDesc = channelDesc;
		}
		public int getRepaySuccAmt() {
			return repaySuccAmt;
		}
		public void setRepaySuccAmt(int repaySuccAmt) {
			this.repaySuccAmt = repaySuccAmt;
		}
		public int getRepaySuccCount() {
			return repaySuccCount;
		}
		public void setRepaySuccCount(int repaySuccCount) {
			this.repaySuccCount = repaySuccCount;
		}
		public int getRepayFailAmt() {
			return repayFailAmt;
		}
		public void setRepayFailAmt(int repayFailAmt) {
			this.repayFailAmt = repayFailAmt;
		}
		public int getRepayFailCount() {
			return repayFailCount;
		}
		public void setRepayFailCount(int repayFailCount) {
			this.repayFailCount = repayFailCount;
		}
		
	}
}
