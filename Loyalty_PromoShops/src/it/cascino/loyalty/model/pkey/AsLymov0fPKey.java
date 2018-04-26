package it.cascino.loyalty.model.pkey;

import java.io.Serializable;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;

@Embeddable
public class AsLymov0fPKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer lydat;
	private String lycau;
	private Integer lynuz;
	private Integer lynum;
	private Integer lyprg;
	
	public AsLymov0fPKey(){
	}
	
	public Integer getLydat(){
		return lydat;
	}
	
	public void setLydat(Integer lydat){
		this.lydat = lydat;
	}
	
	public String getLycau(){
		return lycau;
	}
	
	public void setLycau(String lycau){
		this.lycau = lycau;
	}
	
	public Integer getLynuz(){
		return lynuz;
	}
	
	public void setLynuz(Integer lynuz){
		this.lynuz = lynuz;
	}
	
	public Integer getLynum(){
		return lynum;
	}
	
	public void setLynum(Integer lynum){
		this.lynum = lynum;
	}
	
	public Integer getLyprg(){
		return lyprg;
	}
	
	public void setLyprg(Integer lyprg){
		this.lyprg = lyprg;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lydat == null) ? 0 : lydat.hashCode());
		result = prime * result + ((lycau == null) ? 0 : lycau.hashCode());
		result = prime * result + ((lynuz == null) ? 0 : lynuz.hashCode());
		result = prime * result + ((lynum == null) ? 0 : lynum.hashCode());
		result = prime * result + ((lyprg == null) ? 0 : lyprg.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsLymov0fPKey) {
			if((this.lydat == ((AsLymov0fPKey)obj).lydat) && (this.lycau == ((AsLymov0fPKey)obj).lycau) && (this.lynuz == ((AsLymov0fPKey)obj).lynuz) && (this.lynum == ((AsLymov0fPKey)obj).lynum) && (this.lyprg == ((AsLymov0fPKey)obj).lyprg)) {
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
		stringBuilder.append("AsMovma0fPKey");
		stringBuilder.append("[");
		stringBuilder.append("lydat=" + lydat).append(", ");
		stringBuilder.append("lycau=" + StringUtils.trim(lycau)).append(", ");
		stringBuilder.append("lynuz=" + lynuz).append(", ");
		stringBuilder.append("lynum=" + lynum).append(", ");
		stringBuilder.append("lyprg=" + lyprg);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
