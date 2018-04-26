package it.cascino.loyalty.model;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.StringUtils;

/**
* The persistent class for the cas_dat/lyanc0f database table.
* 
*/
@Entity(name = "Lyanc0f")
@NamedQueries({
	@NamedQuery(name = "AsLyanc0f.findAll", query = "SELECT a FROM Lyanc0f a WHERE a.lytip = :lytip"),
	@NamedQuery(name = "AsLyanc0f.findByLyean", query = "SELECT a FROM Lyanc0f a WHERE a.lyean = :lyean and a.lytip = :lytip"),
	@NamedQuery(name = "AsLyanc0f.findByLycli", query = "SELECT a FROM Lyanc0f a WHERE a.lycli = :lycli and a.lycli != 0 and a.lytip = :lytip"),
	@NamedQuery(name = "AsLyanc0f.findByCognNom", query = "SELECT a FROM Lyanc0f a WHERE a.lycog = :lycog and a.lynom = :lynom and a.lytip = :lytip"),
	@NamedQuery(name = "AsLyanc0f.updateAnagrafica", query = "UPDATE Lyanc0f a SET a.lypdv = :lypdv, a.lyean = :lyean, a.lynom = :lynom, a.lycog = :lycog, a.lydna = :lydna, a.lyind = :lyind, a.lynci = :lynci, a.lycap = :lycap, a.lyloc = :lyloc, a.lypro = :lypro, a.lypry = :lypry, a.lypun = :lypun, a.lysta = :lysta, a.lyste = :lyste, a.lybuo = :lybuo, a.lydup = :lydup WHERE ((a.lycli = :lycli and a.lycli != 0) or a.lyean = :lyean) and a.lytip = :lytip")
})
public class AsLyanc0f implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	// @Inject
	// private Logger log;
	
	private String lypdv;
	private Integer lycli;
	private String lyean;
	private String lynom;
	private String lycog;
	private String lyses;
	private String lycfp;
	private Integer lydna;
	private String lyind;
	private String lynci;
	private Integer lycap;
	private String lyloc;
	private String lypro;
	private String lypry;
	private Integer lypun;
	private String lysta;
	private String lyste;
	private String lybuo;
	private Integer lydcr;
	private Integer lydup;
	private Integer lyduv;
	private String lymai;
	private String lytel;
	private Integer lyvis;
	private Float lytsp;
	private Float lyimo;
	private Integer lynal;
	private Integer lytar;
	private String lytip;
	
	public AsLyanc0f(){
	}
	
	public AsLyanc0f(String lypdv, Integer lycli, String lyean, String lynom, String lycog, String lyses, String lycfp, Integer lydna, String lyind, String lynci, Integer lycap, String lyloc, String lypro, String lypry, Integer lypun, String lysta, String lyste, String lybuo, Integer lydcr, Integer lydup, Integer lyduv, String lymai, String lytel, Integer lyvis, Float lytsp, Float lyimo, Integer lynal, Integer lytar, String lytip){
		super();
		this.lypdv = lypdv;
		this.lycli = lycli;
		this.lyean = lyean;
		this.lynom = lynom;
		this.lycog = lycog;
		this.lyses = lyses;
		this.lycfp = lycfp;
		this.lydna = lydna;
		this.lyind = lyind;
		this.lynci = lynci;
		this.lycap = lycap;
		this.lyloc = lyloc;
		this.lypro = lypro;
		this.lypry = lypry;
		this.lypun = lypun;
		this.lysta = lysta;
		this.lyste = lyste;
		this.lybuo = lybuo;
		this.lydcr = lydcr;
		this.lydup = lydup;
		this.lyduv = lyduv;
		this.lymai = lymai;
		this.lytel = lytel;
		this.lyvis = lyvis;
		this.lytsp = lytsp;
		this.lyimo = lyimo;
		this.lynal = lynal;
		this.lytar = lytar;
		this.lytip = lytip;
	}
	
	public String getLypdv(){
		return lypdv;
	}
	
	public void setLypdv(String lypdv){
		this.lypdv = lypdv;
	}
	
	@Id
	public Integer getLycli(){
		return lycli;
	}
	
	public void setLycli(Integer lycli){
		this.lycli = lycli;
	}
	
	public String getLyean(){
		return lyean;
	}
	
	public void setLyean(String lyean){
		this.lyean = lyean;
	}
	
	public String getLynom(){
		return lynom;
	}
	
	public void setLynom(String lynom){
		this.lynom = lynom;
	}
	
	public String getLycog(){
		return lycog;
	}
	
	public void setLycog(String lycog){
		this.lycog = lycog;
	}
	
	public String getLyses(){
		return lyses;
	}
	
	public void setLyses(String lyses){
		this.lyses = lyses;
	}
	
	public String getLycfp(){
		return lycfp;
	}
	
	public void setLycfp(String lycfp){
		this.lycfp = lycfp;
	}
	
	public Integer getLydna(){
		return lydna;
	}
	
	public void setLydna(Integer lydna){
		this.lydna = lydna;
	}
	
	public String getLyind(){
		return lyind;
	}
	
	public void setLyind(String lyind){
		this.lyind = lyind;
	}
	
	public String getLynci(){
		return lynci;
	}
	
	public void setLynci(String lynci){
		this.lynci = lynci;
	}
	
	public Integer getLycap(){
		return lycap;
	}
	
	public void setLycap(Integer lycap){
		this.lycap = lycap;
	}
	
	public String getLyloc(){
		return StringUtils.upperCase(lyloc);
	}
	
	public void setLyloc(String lyloc){
		this.lyloc = StringUtils.upperCase(lyloc);
	}
	
	public String getLypro(){
		return lypro;
	}
	
	public void setLypro(String lypro){
		this.lypro = lypro;
	}
	
	public String getLypry(){
		return lypry;
	}
	
	public void setLypry(String lypry){
		this.lypry = lypry;
	}
	
	public Integer getLypun(){
		return lypun;
	}
	
	public void setLypun(Integer lypun){
		this.lypun = lypun;
	}
	
	public String getLysta(){
		return lysta;
	}
	
	public void setLysta(String lysta){
		this.lysta = lysta;
	}
	
	public String getLyste(){
		return lyste;
	}
	
	public void setLyste(String lyste){
		this.lyste = lyste;
	}
	
	public String getLybuo(){
		return lybuo;
	}
	
	public void setLybuo(String lybuo){
		this.lybuo = lybuo;
	}
	
	public Integer getLydcr(){
		return lydcr;
	}
	
	public void setLydcr(Integer lydcr){
		this.lydcr = lydcr;
	}
	
	public Integer getLydup(){
		return lydup;
	}
	
	public void setLydup(Integer lydup){
		this.lydup = lydup;
	}
	
	public Integer getLyduv(){
		return lyduv;
	}
	
	public void setLyduv(Integer lyduv){
		this.lyduv = lyduv;
	}
	
	public String getLymai(){
		return lymai;
	}
	
	public void setLymai(String lymai){
		this.lymai = lymai;
	}
	
	public String getLytel(){
		return lytel;
	}
	
	public void setLytel(String lytel){
		this.lytel = lytel;
	}
	
	public Integer getLyvis(){
		return lyvis;
	}
	
	public void setLyvis(Integer lyvis){
		this.lyvis = lyvis;
	}
	
	public Float getLytsp(){
		return lytsp;
	}
	
	public void setLytsp(Float lytsp){
		this.lytsp = lytsp;
	}
	
	public Float getLyimo(){
		return lyimo;
	}
	
	public void setLyimo(Float lyimo){
		this.lyimo = lyimo;
	}
	
	public Integer getLynal(){
		return lynal;
	}
	
	public void setLynal(Integer lynal){
		this.lynal = lynal;
	}
	
	public Integer getLytar(){
		return lytar;
	}
	
	public void setLytar(Integer lytar){
		this.lytar = lytar;
	}
	
	public String getLytip(){
		return lytip;
	}
	
	public void setLytip(String lytip){
		this.lytip = lytip;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AsLyanc0f) {
			if(this.lyean == ((AsLyanc0f)obj).lyean) {
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
		result = prime * result + ((lybuo == null) ? 0 : lybuo.hashCode());
		result = prime * result + ((lycap == null) ? 0 : lycap.hashCode());
		result = prime * result + ((lycfp == null) ? 0 : lycfp.hashCode());
		result = prime * result + ((lycli == null) ? 0 : lycli.hashCode());
		result = prime * result + ((lycog == null) ? 0 : lycog.hashCode());
		result = prime * result + ((lydcr == null) ? 0 : lydcr.hashCode());
		result = prime * result + ((lydna == null) ? 0 : lydna.hashCode());
		result = prime * result + ((lydup == null) ? 0 : lydup.hashCode());
		result = prime * result + ((lyduv == null) ? 0 : lyduv.hashCode());
		result = prime * result + ((lyean == null) ? 0 : lyean.hashCode());
		result = prime * result + ((lyimo == null) ? 0 : lyimo.hashCode());
		result = prime * result + ((lyind == null) ? 0 : lyind.hashCode());
		result = prime * result + ((lyloc == null) ? 0 : lyloc.hashCode());
		result = prime * result + ((lymai == null) ? 0 : lymai.hashCode());
		result = prime * result + ((lynal == null) ? 0 : lynal.hashCode());
		result = prime * result + ((lynci == null) ? 0 : lynci.hashCode());
		result = prime * result + ((lynom == null) ? 0 : lynom.hashCode());
		result = prime * result + ((lypdv == null) ? 0 : lypdv.hashCode());
		result = prime * result + ((lypro == null) ? 0 : lypro.hashCode());
		result = prime * result + ((lypry == null) ? 0 : lypry.hashCode());
		result = prime * result + ((lypun == null) ? 0 : lypun.hashCode());
		result = prime * result + ((lyses == null) ? 0 : lyses.hashCode());
		result = prime * result + ((lysta == null) ? 0 : lysta.hashCode());
		result = prime * result + ((lyste == null) ? 0 : lyste.hashCode());
		result = prime * result + ((lytar == null) ? 0 : lytar.hashCode());
		result = prime * result + ((lytel == null) ? 0 : lytel.hashCode());
		result = prime * result + ((lytsp == null) ? 0 : lytsp.hashCode());
		result = prime * result + ((lyvis == null) ? 0 : lyvis.hashCode());
		result = prime * result + ((lytip == null) ? 0 : lytip.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1));
		stringBuilder.append("[");
		stringBuilder.append("lypdv=" + StringUtils.trim(lypdv)).append(", ");
		stringBuilder.append("lycli=" + lycli).append(", ");
		stringBuilder.append("lyean=" + StringUtils.trim(lyean)).append(", ");
		stringBuilder.append("lynom=" + StringUtils.trim(lynom)).append(", ");
		stringBuilder.append("lycog=" + StringUtils.trim(lycog)).append(", ");
		stringBuilder.append("lyses=" + StringUtils.trim(lyses)).append(", ");
		stringBuilder.append("lycfp=" + StringUtils.trim(lycfp)).append(", ");
		stringBuilder.append("lydna=" + lydna).append(", ");
		stringBuilder.append("lyind=" + StringUtils.trim(lyind)).append(", ");
		stringBuilder.append("lynci=" + StringUtils.trim(lynci)).append(", ");
		stringBuilder.append("lycap=" + lycap).append(", ");
		stringBuilder.append("lyloc=" + StringUtils.trim(lyloc)).append(", ");
		stringBuilder.append("lypro=" + StringUtils.trim(lypro)).append(", ");
		stringBuilder.append("lypry=" + StringUtils.trim(lypry)).append(", ");
		stringBuilder.append("lypun=" + lypun).append(", ");
		stringBuilder.append("lysta=" + StringUtils.trim(lysta)).append(", ");
		stringBuilder.append("lyste=" + StringUtils.trim(lyste)).append(", ");
		stringBuilder.append("lybuo=" + StringUtils.trim(lybuo)).append(", ");
		stringBuilder.append("lydcr=" + lydcr).append(", ");
		stringBuilder.append("lydup=" + lydup).append(", ");
		stringBuilder.append("lyduv=" + lyduv).append(", ");
		stringBuilder.append("lymai=" + StringUtils.trim(lymai)).append(", ");
		stringBuilder.append("lytel=" + StringUtils.trim(lytel)).append(", ");
		stringBuilder.append("lyvis=" + lyvis).append(", ");
		stringBuilder.append("lytsp=" + lytsp).append(", ");
		stringBuilder.append("lyimo=" + lyimo).append(", ");
		stringBuilder.append("lynal=" + lynal).append(", ");
		stringBuilder.append("lytar=" + lytar).append(", ");
		stringBuilder.append("lytip=" + lytip);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}