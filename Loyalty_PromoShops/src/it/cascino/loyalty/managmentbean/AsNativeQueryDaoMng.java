package it.cascino.loyalty.managmentbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import it.cascino.loyalty.dao.AsNativeQueryDao;
import it.cascino.loyalty.utils.Resources;

public class AsNativeQueryDaoMng implements AsNativeQueryDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsNativeQueryDaoMng.class);
	
	public Timestamp getDaSysdummy1_TimestampAs400(){
		Timestamp o = null;
		try{
			try{
				utx.begin();
				String sql = "select a.ts from (select current timestamp ts from sysibm.sysdummy1) a";
				Query query = em.createNativeQuery(sql);
				o = (Timestamp)query.getSingleResult();
			}catch(NoResultException e){
				o = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return o;
	}
	
	public void close(){
		res.close();
		log.info("chiuso");
	}
}
