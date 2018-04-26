package it.cascino.loyalty.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.loyalty.model.AsMovma0f;
import it.cascino.loyalty.utils.Resources;
import it.cascino.loyalty.dao.AsMovma0fDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class AsMovma0fDaoMng implements AsMovma0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsMovma0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsMovma0f> getAll(){
		List<AsMovma0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsMovma0f.findAll");
				cod = (List<AsMovma0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
//	public void salva(AsMovma0f a){
//		try{
//			try{
//				utx.begin();
//				// precodice.setId(null);
//				log.info("salva: " + a.toString());
//				em.persist(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void aggiorna(AsMovma0f a){
//		try{
//			try{
//				utx.begin();
//				log.info("aggiorna: " + a.toString());
//				em.merge(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
//	
//	public void elimina(AsMovma0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsMovma0f a = em.find(AsMovma0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public List<AsMovma0f> getMovCassa(Integer vdatr, String vcaus, Integer vnura, Integer vnumd){
		List<AsMovma0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsMovma0f.findMovCassa");
				query.setParameter("vdatr", vdatr);
				query.setParameter("vcaus", vcaus);
				query.setParameter("vnura", vnura);
				query.setParameter("vnumd", vnumd);
				cod = (List<AsMovma0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
	public AsMovma0f getMovCassaConRigo(Integer vdatr, String vcaus, Integer vnura, Integer vnumd, Integer vprog){
		AsMovma0f cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsMovma0f.findMovCassaConRigo");
				query.setParameter("vdatr", vdatr);
				query.setParameter("vcaus", vcaus);
				query.setParameter("vnura", vnura);
				query.setParameter("vnumd", vnumd);
				query.setParameter("vprog", vprog);
				cod = (AsMovma0f)query.getSingleResult();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}

	public AsMovma0f getMovCassaConArticolo(Integer vdatr, String vcaus, Integer vnura, Integer vnumd, String vcoda){
		AsMovma0f cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsMovma0f.findMovCassaConArticolo");
				query.setParameter("vdatr", vdatr);
				query.setParameter("vcaus", vcaus);
				query.setParameter("vnura", vnura);
				query.setParameter("vnumd", vnumd);
				query.setParameter("vcoda", vcoda);
				cod = (AsMovma0f)query.getSingleResult();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
	public void close(){
		res.close();
		log.info("chiuso");
	}
}
