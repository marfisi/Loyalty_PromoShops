package it.cascino.loyalty.model;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;
import it.cascino.loyalty.model.pkey.AsMovma0fPKey;

/**
* The persistent class for the cas_dat/movma0f database table.
* 
*/
@Entity(name = "Movma0f")
@NamedQueries({
	@NamedQuery(name = "AsMovma0f.findAll", query = "SELECT a FROM Movma0f a"),
	@NamedQuery(name = "AsMovma0f.findMovCassa", query = "SELECT a FROM Movma0f a WHERE a.id.vdatr = :vdatr and a.id.vcaus = :vcaus and a.id.vnura = :vnura and a.id.vnumd = :vnumd"),
	@NamedQuery(name = "AsMovma0f.findMovCassaConRigo", query = "SELECT a FROM Movma0f a WHERE a.id.vdatr = :vdatr and a.id.vcaus = :vcaus and a.id.vnura = :vnura and a.id.vnumd = :vnumd and a.id.vprog = :vprog"),
	@NamedQuery(name = "AsMovma0f.findMovCassaConArticolo", query = "SELECT a FROM Movma0f a WHERE a.id.vdatr = :vdatr and a.id.vcaus = :vcaus and a.id.vnura = :vnura and a.id.vnumd = :vnumd and a.vcoda = :vcoda")
})
public class AsMovma0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	// @Inject
	// private Logger log;
	
	@EmbeddedId
	private AsMovma0fPKey id;
	private String vcoda;
	private String vdesc;
	private Float vquan;
	private Float vprez;
	private Float vsco1;
	private Float vsco2;
	private Float vsco3;
	private String vciva;
	private Integer vtime;
	
	public AsMovma0f(){
		this.id = new AsMovma0fPKey();
	}
	
	public AsMovma0f(AsMovma0fPKey id, Integer vprog, String vcoda, String vdesc, Float vquan, Float vprez, Float vsco1, Float vsco2, Float vsco3, String vciva, Integer vtime){
		super();
		this.id = id;
		this.vcoda = vcoda;
		this.vdesc = vdesc;
		this.vquan = vquan;
		this.vprez = vprez;
		this.vsco1 = vsco1;
		this.vsco2 = vsco2;
		this.vsco3 = vsco3;
		this.vciva = vciva;
		this.vtime = vtime;
	}
	
	public AsMovma0fPKey getId(){
		return id;
	}
	
	public void setId(AsMovma0fPKey id){
		this.id = id;
	}
	
	public String getVcoda(){
		return vcoda;
	}
	
	public void setVcoda(String vcoda){
		this.vcoda = vcoda;
	}
	
	public String getVdesc(){
		return vdesc;
	}
	
	public void setVdesc(String vdesc){
		this.vdesc = vdesc;
	}
	
	public Float getVquan(){
		return vquan;
	}
	
	public void setVquan(Float vquan){
		this.vquan = vquan;
	}
	
	public Float getVprez(){
		return vprez;
	}
	
	public void setVprez(Float vprez){
		this.vprez = vprez;
	}
	
	public Float getVsco1(){
		return vsco1;
	}
	
	public void setVsco1(Float vsco1){
		this.vsco1 = vsco1;
	}
	
	public Float getVsco2(){
		return vsco2;
	}
	
	public void setVsco2(Float vsco2){
		this.vsco2 = vsco2;
	}
	
	public Float getVsco3(){
		return vsco3;
	}
	
	public void setVsco3(Float vsco3){
		this.vsco3 = vsco3;
	}
	
	public String getVciva(){
		return vciva;
	}
	
	public void setVciva(String vciva){
		this.vciva = vciva;
	}
	
	public Integer getVtime(){
		return vtime;
	}

	public void setVtime(Integer vtime){
		this.vtime = vtime;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((vciva == null) ? 0 : vciva.hashCode());
		result = prime * result + ((vcoda == null) ? 0 : vcoda.hashCode());
		result = prime * result + ((vdesc == null) ? 0 : vdesc.hashCode());
		result = prime * result + ((vprez == null) ? 0 : vprez.hashCode());
		result = prime * result + ((vquan == null) ? 0 : vquan.hashCode());
		result = prime * result + ((vsco1 == null) ? 0 : vsco1.hashCode());
		result = prime * result + ((vsco2 == null) ? 0 : vsco2.hashCode());
		result = prime * result + ((vsco3 == null) ? 0 : vsco3.hashCode());	
		result = prime * result + ((vtime == null) ? 0 : vtime.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsMovma0f) {
			if(this.id == ((AsMovma0f)obj).id) {
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
		stringBuilder.append("vcoda=" + StringUtils.trim(vcoda)).append(", ");
		stringBuilder.append("vdesc=" + StringUtils.trim(vdesc)).append(", ");
		stringBuilder.append("vquan=" + vquan).append(", ");
		stringBuilder.append("vprez=" + vprez).append(", ");
		stringBuilder.append("vsco1=" + vsco1).append(", ");
		stringBuilder.append("vsco2=" + vsco2).append(", ");
		stringBuilder.append("vsco3=" + vsco3).append(", ");
		stringBuilder.append("vtime=" + vtime).append(", ");
		stringBuilder.append("vciva=" + StringUtils.trim(vciva));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}