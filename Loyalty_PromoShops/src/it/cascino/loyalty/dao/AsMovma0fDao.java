package it.cascino.loyalty.dao;

import java.util.List;
import it.cascino.loyalty.model.AsMovma0f;

public interface AsMovma0fDao{
	List<AsMovma0f> getAll();
	
//	void salva(AsMovma0f a);
//	
//	void aggiorna(AsMovma0f a);
//	
//	void elimina(AsMovma0f a);

	List<AsMovma0f> getMovCassa(Integer vdatr, String vcaus, Integer vnura, Integer vnumd);

	AsMovma0f getMovCassaConRigo(Integer vdatr, String vcaus, Integer vnura, Integer vnumd, Integer vprog);

	AsMovma0f getMovCassaConArticolo(Integer vdatr, String vcaus, Integer vnura, Integer vnumd, String vcoda);

	void close();
}
