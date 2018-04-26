package it.cascino.loyalty.managmentbean;

import java.io.Serializable;
import java.util.List;
import it.cascino.loyalty.model.AsLymov0f;
import it.cascino.loyalty.utils.Resources;
import it.cascino.loyalty.dao.AsLymov0fDao;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public class AsLymov0fDaoMng implements AsLymov0fDao, Serializable{
	private static final long serialVersionUID = 1L;
	private Resources res = new Resources();
	private EntityManager em = res.getEmAs();
	private EntityTransaction utx = res.getUtxAs();	
	
	Logger log = Logger.getLogger(AsLymov0fDaoMng.class);
	
	private final String lytip = "PS";
	
	@SuppressWarnings("unchecked")
	public List<AsLymov0f> getAll(){
		List<AsLymov0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsLymov0f.findAll");
				query.setParameter("lytip", lytip);
				cod = (List<AsLymov0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
//	public void salva(AsLymov0f a){
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
//	public void aggiorna(AsLymov0f a){
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
//	public void elimina(AsLymov0f aElimina){
//		// log.info("tmpDEBUGtmp: " + "> " + "elimina(" + produttoreElimina + ")");
//		try{
//			try{
//				utx.begin();
//				AsLymov0f a = em.find(AsLymov0f.class, aElimina.getMcoda());
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
	public List<AsLymov0f> getToElab(){
		List<AsLymov0f> cod = null;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsLymov0f.findToElab");
				query.setParameter("lysta", "0");
				query.setParameter("lytip", lytip);
				cod = (List<AsLymov0f>)query.getResultList();
			}catch(NoResultException e){
				cod = null;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod;
	}
	
	public String updateElab(AsLymov0f lymov){
		Integer cod = -1;
		try{
			try{
				utx.begin();
				Query query = em.createNamedQuery("AsLymov0f.updateElab");
				query.setParameter("lystaElab", "1");
				query.setParameter("lystaNonElab", "0");
				query.setParameter("lydat", lymov.getId().getLydat());
				query.setParameter("lycau",  lymov.getId().getLycau());
				query.setParameter("lynuz",  lymov.getId().getLynuz());
				query.setParameter("lynum",  lymov.getId().getLynum());
				query.setParameter("lytip", lytip);
				cod = query.executeUpdate();
			}catch(NoResultException e){
				cod = -1;
			}
			utx.commit();
		}catch(Exception e){
			log.fatal(e.toString());
		}
		return cod.toString();
	}
	
	public void close(){
		res.close();
		log.info("chiuso");
	}
}
