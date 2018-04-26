package it.cascino.loyalty.model;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;
import it.cascino.loyalty.model.pkey.AsLymov0fPKey;

/**
* The persistent class for the cas_dat/lymov0f database table.
* 
*/
@Entity(name = "Lymov0f")
@NamedQueries({
	@NamedQuery(name = "AsLymov0f.findAll", query = "SELECT c FROM Lymov0f c WHERE c.lytip = :lytip"),
	@NamedQuery(name = "AsLymov0f.findToElab", query = "SELECT c FROM Lymov0f c WHERE c.lysta = :lysta and c.lytip = :lytip order by c.id.lydat, c.id.lycau, c.id.lynuz, c.id.lynum, c.id.lyprg"),
	@NamedQuery(name = "AsLymov0f.updateElab", query = "UPDATE Lymov0f c SET c.lysta = :lystaElab WHERE c.lysta = :lystaNonElab and c.id.lydat = :lydat and c.id.lycau = :lycau and c.id.lynuz = :lynuz and c.id.lynum = :lynum and c.lytip = :lytip")
})
public class AsLymov0f implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	// @Inject
	// private Logger log;
	
	@EmbeddedId
	private AsLymov0fPKey id;
	private String lyean;
	private String lysta;
	private String lytip;
	
	public AsLymov0f(){
		this.id = new AsLymov0fPKey();
	}
	
	public AsLymov0f(AsLymov0fPKey id, String lyean, String lysta){
		super();
		this.id = id;
		this.lyean = lyean;
		this.lysta = lysta;
	}
	
	public AsLymov0fPKey getId(){
		return id;
	}

	public void setId(AsLymov0fPKey id){
		this.id = id;
	}

	public String getLyean(){
		return lyean;
	}
	
	public void setLyean(String lyean){
		this.lyean = lyean;
	}
	
	public String getLysta(){
		return lysta;
	}
	
	public void setLysta(String lysta){
		this.lysta = lysta;
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
		result = prime * result + ((lyean == null) ? 0 : lyean.hashCode());
		result = prime * result + ((lysta == null) ? 0 : lysta.hashCode());
		result = prime * result + ((lytip == null) ? 0 : lytip.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsLymov0f) {
			if(this.id == ((AsLymov0f)obj).id) {
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
		stringBuilder.append("lyean=" + StringUtils.trim(lyean)).append(", ");
		stringBuilder.append("lysta=" + StringUtils.trim(lysta)).append(", ");
		stringBuilder.append("lytip=" + StringUtils.trim(lytip));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}