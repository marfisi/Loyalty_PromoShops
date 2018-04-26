package it.cascino.loyalty.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.loyalty.model.AsAafor0f;
import it.cascino.loyalty.utils.Resources;
import it.cascino.loyalty.dao.AsAafor0fDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class AsAafor0fDaoMng implements AsAafor0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsAafor0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsAafor0f> getAll(){
		List<AsAafor0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAafor0f.findAll");
				cod = (List<AsAafor0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
//	public void salva(AsAafor0f a){
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
//	public void aggiorna(AsAafor0f a){
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
//	public void elimina(AsAafor0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsAafor0f a = em.find(AsAafor0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	public AsAafor0f getArticoloDaAaforAacoa(Integer aafor, String aacoa){
		AsAafor0f cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAafor0f.findByAaforAacoa");
				query.setParameter("aafor", aafor);
				query.setParameter("aacoa", aacoa);	// in aafor0f il campo aacoa e' di 14 mentre in anmag0f il campo mcoaf e' di 13
				cod = (AsAafor0f)query.getSingleResult();
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
