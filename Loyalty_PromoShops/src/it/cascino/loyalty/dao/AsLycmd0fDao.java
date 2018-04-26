package it.cascino.loyalty.dao;

import java.util.List;
import it.cascino.loyalty.model.AsLycmd0f;

public interface AsLycmd0fDao{
	List<AsLycmd0f> getAll();
	
	void salva(AsLycmd0f c);
	
	void aggiorna(AsLycmd0f c);
	
	void elimina(AsLycmd0f c);

	List<AsLycmd0f> getCmdByLycass(String lycass);

	List<AsLycmd0f> getCmdToDo();

	String updateRis(AsLycmd0f cmd);
	
	void close();
}
