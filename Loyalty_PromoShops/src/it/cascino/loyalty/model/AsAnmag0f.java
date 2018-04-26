package it.cascino.loyalty.model;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;

/**
* The persistent class for the cas_dat/anmag0f database table.
* 
*/
@Entity(name="Anmag0f")
@NamedQueries({
	@NamedQuery(name = "AsAnmag0f.findAll", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and a.atama != 'S'"),
	@NamedQuery(name = "AsAnmag0f.findByMcoda", query = "SELECT a FROM Anmag0f a WHERE a.atama != 'A' and a.atama != 'S' and a.mcoda = :mcoda ")
})
public class AsAnmag0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
//	@Inject
//	private Logger log;
	
	private String atama;
	private String mcoda;
	private String mdesc;
	private Integer mcofo;
	private String mcoaf;
	
	public AsAnmag0f(){
	}
	
	public AsAnmag0f(String atama, String mcoda, String mdesc, Integer mcofo, String mcoaf){
		super();
		this.atama = atama;
		this.mcoda = mcoda;
		this.mdesc = mdesc;
		this.mcofo = mcofo;
		this.mcoaf = mcoaf;
	}

	public String getAtama(){
		return atama;
	}

	public void setAtama(String atama){
		this.atama = atama;
	}

	@Id
	public String getMcoda(){
		return mcoda;
	}

	public void setMcoda(String mcoda){
		this.mcoda = mcoda;
	}
	
	public String getMdesc(){
		return mdesc;
	}

	public void setMdesc(String mdesc){
		this.mdesc = mdesc;
	}

	public Integer getMcofo(){
		return mcofo;
	}

	public void setMcofo(Integer mcofo){
		this.mcofo = mcofo;
	}

	public String getMcoaf(){
		return mcoaf;
	}

	public void setMcoaf(String mcoaf){
		this.mcoaf = mcoaf;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAnmag0f){
			if(this.mcoda == ((AsAnmag0f)obj).mcoda){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atama == null) ? 0 : atama.hashCode());
		result = prime * result + ((mcoda == null) ? 0 : mcoda.hashCode());
		result = prime * result + ((mdesc == null) ? 0 : mdesc.hashCode());
		result = prime * result + ((mcofo == null) ? 0 : mcofo.hashCode());
		result = prime * result + ((mcoaf == null) ? 0 : mcoaf.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		stringBuilder.append("mcoda=" + StringUtils.trim(mcoda)).append(", ");
		stringBuilder.append("atama=" + StringUtils.trim(atama)).append(", ");
		stringBuilder.append("mcoda=" + StringUtils.trim(mcoda)).append(", ");
		stringBuilder.append("mcofo=" + mcofo).append(", ");
		stringBuilder.append("mcoaf=" + StringUtils.trim(mcoaf));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}