package it.cascino.loyalty.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.loyalty.model.AsAnmag0f;
import it.cascino.loyalty.utils.Resources;
import it.cascino.loyalty.dao.AsAnmag0fDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class AsAnmag0fDaoMng implements AsAnmag0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsAnmag0fDaoMng.class);
	
	@SuppressWarnings("unchecked")
	public List<AsAnmag0f> getAll(){
		List<AsAnmag0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmag0f.findAll");
				cod = (List<AsAnmag0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
//	public void salva(AsAnmag0f a){
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
//	public void aggiorna(AsAnmag0f a){
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
//	public void elimina(AsAnmag0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsAnmag0f a = em.find(AsAnmag0f.class, aElimina.getMcoda());
//				log.info("elimina: " + a.toString());
//				em.remove(a);
//			}finally{
//				utx.commit();
//			}
//		}catch(Exception e){
//			log.fatal(e.toString());
//		}
//	}
	
	public AsAnmag0f getArticoloDaMcoda(String mcoda){
		AsAnmag0f cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsAnmag0f.findByMcoda");
				query.setParameter("mcoda", mcoda);
				cod = (AsAnmag0f)query.getSingleResult();
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
