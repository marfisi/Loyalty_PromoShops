package it.cascino.loyalty.dao;

import java.util.List;
import it.cascino.loyalty.model.AsLymov0f;

public interface AsLymov0fDao{
	List<AsLymov0f> getAll();
	
//	void salva(AsLymov0f a);
//	
//	void aggiorna(AsLymov0f a);
//	
//	void elimina(AsLymov0f a);

	List<AsLymov0f> getToElab();
	
	String updateElab(AsLymov0f lymov);
	
	void close();
}
