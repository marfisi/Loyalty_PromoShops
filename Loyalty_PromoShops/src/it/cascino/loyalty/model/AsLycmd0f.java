package it.cascino.loyalty.model;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;
import it.cascino.loyalty.model.pkey.AsLycmd0fPKey;

/**
* The persistent class for the cas_dat/lycmd0f database table.
* 
*/
@Entity(name = "Lycmd0f")
@NamedQueries({
	@NamedQuery(name = "AsLycmd0f.findAll", query = "SELECT c FROM Lycmd0f c WHERE c.lytip = :lytip"),
	@NamedQuery(name = "AsLycmd0f.findByLycass", query = "SELECT c FROM Lycmd0f c WHERE c.id.lycass = :lycass and c.lytip = :lytip"),
	@NamedQuery(name = "AsLycmd0f.findByToDo", query = "SELECT c FROM Lycmd0f c WHERE c.lyris1 = '' and c.lytip = :lytip"),
	@NamedQuery(name = "AsLycmd0f.updateRis", query = "UPDATE Lycmd0f c SET c.lyris1 = :lyris1, c.lyris8 = :lyris8 WHERE c.id.lycass = :lycass and c.id.lycmd1 = :lycmd1 and c.lyris1 = '' and c.id.lyidtr = :lyidtr and c.lytip = :lytip")
})
public class AsLycmd0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	// @Inject
	// private Logger log;
	
	@EmbeddedId
	private AsLycmd0fPKey id;
	private String lyris1;
	private String lyris8;
	private String lytip;
	
	public AsLycmd0f(){
		this.id = new AsLycmd0fPKey();
	}
	
	public AsLycmd0f(AsLycmd0fPKey id, String lyris1, String lyris8){
		super();
		this.id = id;
		this.lyris1 = lyris1;
		this.lyris8 = lyris8;
	}
	
	public AsLycmd0fPKey getId(){
		return id;
	}
	
	public void setId(AsLycmd0fPKey id){
		this.id = id;
	}
	
	public String getLyris1(){
		return lyris1;
	}
	
	public void setLyris1(String lyris1){
		this.lyris1 = lyris1;
	}
	
	public String getLyris8(){
		return lyris8;
	}
	
	public void setLyris8(String lyris8){
		this.lyris8 = lyris8;
	}
	
	public String getLytip(){
		return lytip;
	}
	
	public void setLytip(String lytip){
		this.lytip = lytip;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lyris1 == null) ? 0 : lyris1.hashCode());
		result = prime * result + ((lyris8 == null) ? 0 : lyris8.hashCode());
		result = prime * result + ((lytip == null) ? 0 : lytip.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsLycmd0f) {
			if(this.id == ((AsLycmd0f)obj).id) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		stringBuilder.append("id=" + StringUtils.trim(id.toString())).append(", ");
		stringBuilder.append("lyris1=" + StringUtils.trim(lyris1)).append(", ");
		stringBuilder.append("lyris8=" + StringUtils.trim(lyris8)).append(", ");
		stringBuilder.append("lytip=" + StringUtils.trim(lytip));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}