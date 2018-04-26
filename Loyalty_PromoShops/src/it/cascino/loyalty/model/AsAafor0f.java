package it.cascino.loyalty.model;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;
import it.cascino.loyalty.model.pkey.AsAafor0fPKey;

/**
* The persistent class for the cas_dat/aafor0f database table.
* 
*/
@Entity(name = "Aafor0f")
@NamedQueries({
	@NamedQuery(name = "AsAafor0f.findAll", query = "SELECT a FROM Aafor0f a"),
	@NamedQuery(name = "AsAafor0f.findByAaforAacoa", query = "SELECT a FROM Aafor0f a WHERE a.id.aafor = :aafor and a.id.aacoa = :aacoa")
})
public class AsAafor0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	// @Inject
	// private Logger log;
	
	@EmbeddedId
	private AsAafor0fPKey id;
	private String aamar;
	private String aades;
	private String aasfa;
	private String aamod;
	private String aacex;
	private String aaean;
	
	public AsAafor0f(){
		this.id = new AsAafor0fPKey();
	}

	public AsAafor0f(AsAafor0fPKey id, String aamar, String aades, String aasfa, String aamod, String aacex, String aaean){
		super();
		this.id = id;
		this.aamar = aamar;
		this.aades = aades;
		this.aasfa = aasfa;
		this.aamod = aamod;
		this.aacex = aacex;
		this.aaean = aaean;
	}

	public AsAafor0fPKey getId(){
		return id;
	}
	
	public void setId(AsAafor0fPKey id){
		this.id = id;
	}

	public String getAamar(){
		return aamar;
	}

	public void setAamar(String aamar){
		this.aamar = aamar;
	}

	public String getAades(){
		return aades;
	}

	public void setAades(String aades){
		this.aades = aades;
	}

	public String getAasfa(){
		return aasfa;
	}

	public void setAasfa(String aasfa){
		this.aasfa = aasfa;
	}

	public String getAamod(){
		return aamod;
	}

	public void setAamod(String aamod){
		this.aamod = aamod;
	}
	
	public String getAacex(){
		return aacex;
	}

	public void setAacex(String aacex){
		this.aacex = aacex;
	}

	public String getAaean(){
		return aaean;
	}

	public void setAaean(String aaean){
		this.aaean = aaean;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((aades == null) ? 0 : aades.hashCode());
		result = prime * result + ((aamar == null) ? 0 : aamar.hashCode());
		result = prime * result + ((aamod == null) ? 0 : aamod.hashCode());
		result = prime * result + ((aasfa == null) ? 0 : aasfa.hashCode());
		result = prime * result + ((aacex == null) ? 0 : aacex.hashCode());
		result = prime * result + ((aaean == null) ? 0 : aaean.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAafor0f) {
			if(this.id == ((AsAafor0f)obj).id) {
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
		stringBuilder.append("aamar=" + StringUtils.trim(aamar)).append(", ");
		stringBuilder.append("aades=" + StringUtils.trim(aades)).append(", ");
		stringBuilder.append("aasfa=" + StringUtils.trim(aasfa)).append(", ");
		stringBuilder.append("aamod=" + StringUtils.trim(aamod)).append(", ");
		stringBuilder.append("aacex=" + StringUtils.trim(aacex)).append(", ");
		stringBuilder.append("aaean=" + StringUtils.trim(aaean));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}