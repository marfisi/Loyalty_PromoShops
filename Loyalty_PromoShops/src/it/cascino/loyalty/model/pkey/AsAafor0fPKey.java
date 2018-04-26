package it.cascino.loyalty.model.pkey;

import java.io.Serializable;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.StringUtils;

@Embeddable
public class AsAafor0fPKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer aafor;
	private String aacoa;

	public AsAafor0fPKey(){
	}

	public AsAafor0fPKey(Integer aafor, String aacoa){
		super();
		this.aafor = aafor;
		this.aacoa = aacoa;
	}

	public Integer getAafor(){
		return aafor;
	}

	public void setAafor(Integer aafor){
		this.aafor = aafor;
	}

	public String getAacoa(){
		return aacoa;
	}

	public void setAacoa(String aacoa){
		this.aacoa = aacoa;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aacoa == null) ? 0 : aacoa.hashCode());
		result = prime * result + ((aafor == null) ? 0 : aafor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsAafor0fPKey) {
			if((this.aacoa == ((AsAafor0fPKey)obj).aacoa)&&(this.aafor == ((AsAafor0fPKey)obj).aafor)) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return "AsAafor0fPKey [aafor=" + aafor + ", aacoa=" + StringUtils.trim(aacoa) + "]";
	}	
}
