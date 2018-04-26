package it.cascino.loyalty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import it.cascino.loyalty.dao.AsAafor0fDao;
import it.cascino.loyalty.dao.AsAnmag0fDao;
import it.cascino.loyalty.dao.AsLycmd0fDao;
import it.cascino.loyalty.dao.AsLymov0fDao;
import it.cascino.loyalty.dao.AsMovma0fDao;
import it.cascino.loyalty.dao.AsNativeQueryDao;
import it.cascino.loyalty.managmentbean.AsAafor0fDaoMng;
import it.cascino.loyalty.managmentbean.AsAnmag0fDaoMng;
import it.cascino.loyalty.managmentbean.AsLycmd0fDaoMng;
import it.cascino.loyalty.managmentbean.AsLymov0fDaoMng;
import it.cascino.loyalty.managmentbean.AsMovma0fDaoMng;
import it.cascino.loyalty.managmentbean.AsNativeQueryDaoMng;
import it.cascino.loyalty.model.AsAafor0f;
import it.cascino.loyalty.model.AsAnmag0f;
import it.cascino.loyalty.model.AsLycmd0f;
import it.cascino.loyalty.model.AsLymov0f;
import it.cascino.loyalty.model.AsMovma0f;

public class Loyalty{
	
	private Logger log = Logger.getLogger(Loyalty.class);
	
	StringBuilder stringBuilder = new StringBuilder();
	
	private String password = "";
	private String codicePDV = "";
	private String codiceOperatore = "";
	private String codiceClient = "";
	private String funzione = "";
	private String codiceCard = "";
	private String idTransazione = "";
	
	private final String sepCampiRisposta = "|";
	private final String sepStatoRisposta = "§";
	
	private AsLycmd0fDao asLycmd0fDao = new AsLycmd0fDaoMng();
	private List<AsLycmd0f> cmdLs;
	
	private AsLymov0fDao asLymov0fDao = new AsLymov0fDaoMng();
	private List<AsLymov0f> lymovLs;
	
	private AsMovma0fDao asMovma0fDao = new AsMovma0fDaoMng();
	// private List<AsMovma0f> movmaLs;
	
	private AsAnmag0fDao asAnmag0fDao = new AsAnmag0fDaoMng();
	private AsAafor0fDao asAafor0fDao = new AsAafor0fDaoMng();
	
	private AsNativeQueryDao asNativeQueryDao = new AsNativeQueryDaoMng();

	Float totaleArticoliLoyalty = 0.0f;
	
	private String dataEsecuzione = "";	
	private String dataOraScontrino = "";	
	private String cassaScontrino = "";	
	private String numeroScontrino = "";	
	
	public Loyalty(String args[]){
		log.info("[" + "Loyalty");
		
		DateTimeFormatter formatter = null;
		String strTtimestampAs400 =  asNativeQueryDao.getDaSysdummy1_TimestampAs400().toString();
		// e' in formato "yyyy-MM-dd HH:mm:ss.SSSSSS"
		strTtimestampAs400 = StringUtils.substringBefore(strTtimestampAs400, ".");
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	
		LocalDateTime timestampAs400 = LocalDateTime.parse(strTtimestampAs400, formatter);
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");	// dd/MM/yyyy - HH:mm:ss
		dataEsecuzione = timestampAs400.format(formatter);
		
		Integer frequenza = 2000;
		Boolean stopProgramma = false;
		
		String argomenti = "";
		AsLycmd0f cmd = null;
		Iterator<AsLycmd0f> iter_cmd = null;
		while(true){
			cmdLs = asLycmd0fDao.getCmdToDo();
			iter_cmd = cmdLs.iterator();
			while(iter_cmd.hasNext()){
				cmd = iter_cmd.next();
				log.info("Comando: " + cmd.toString());
				
				argomenti = cmd.getId().getLycmd1();
				argomenti = StringUtils.normalizeSpace(argomenti);
				
				log.info("Parametri: " + argomenti);
				args = StringUtils.split(argomenti);
				for(int numArg = 0; numArg < args.length; numArg++){
					if(args[numArg].compareTo("-codpdv") == 0) {
						numArg++;
						codicePDV = args[numArg];
						if(StringUtils.equals(codicePDV, "EC00000002516")){	// temini
							codicePDV = "2";
						}else if(StringUtils.equals(codicePDV, "EC00000012094")){ 	// lascari
							codicePDV = "5";
						}else if(StringUtils.equals(codicePDV, "EC00000022152")){ 	// bagheria
							codicePDV = "6";
						}else if(StringUtils.equals(codicePDV, "EC00000023177")){ 	// brolo
							codicePDV = "8";
						}else if(StringUtils.equals(codicePDV, "EC00000025982")){ 	// enna
							codicePDV = "9";
						}else{	// termini
							codicePDV = "2";
						}
					}else if(args[numArg].compareTo("-codop") == 0) {
						numArg++;
						codiceOperatore = args[numArg];
					}else if(args[numArg].compareTo("-codcass") == 0) {
						numArg++;
						codiceClient = args[numArg];
					}else if(args[numArg].compareTo("-fun") == 0) {
						numArg++;
						funzione = args[numArg];
						// serie di parametri utili per le funzioni
					}else if(args[numArg].compareTo("-codiceCard") == 0) {
						numArg++;
						codiceCard = args[numArg];
//					}else if(args[numArg].compareTo("-idTrans") == 0) {
//						numArg++;
//						idTransazione = args[numArg];
					}else if(args[numArg].compareTo("-ms") == 0) {
						numArg++;
						frequenza = Integer.parseInt(args[numArg]);
					}else{ // se c'e' almeno un parametro e non e' tra quelli previsti stampo il messaggio d'aiuto
					}
				}
				
				// gestisco la chiamata a funzione
				if(funzione.compareTo("caricamentoPunti") == 0) {
					lymovLs = asLymov0fDao.getToElab();
					
					if(lymovLs.isEmpty()){
						stringBuilder = new StringBuilder();
						stringBuilder.append("nessuno scontrino da elaborare" + sepStatoRisposta + "nessuno scontrino da elaborare");
						log.info(stringBuilder.toString());
						scriviRispostaInDb(cmd);
					}
					
//					Scontrino scontrino = null;
					String lyprgScontrino = "";

					// lista in array
					AsLymov0f arrayLymov0f[] = lymovLs.toArray(new AsLymov0f[lymovLs.size()]);
					
					String idTransazioneSuccessiva = "";
					lyprgScontrino = "";
					for(int i = 0; i < arrayLymov0f.length; i++){
						log.info("giro con i = " + i);
						AsLymov0f lymov = arrayLymov0f[i];
						idTransazione = StringUtils.remove(lymov.getId().getLydat() + "-" + lymov.getId().getLycau() + "-" + lymov.getId().getLynuz() + "-" + lymov.getId().getLynum(), " ");
						AsLymov0f lymovSuccessiva = null;
						idTransazioneSuccessiva = "";
						if(i < (arrayLymov0f.length - 1)){
							lymovSuccessiva = arrayLymov0f[i + 1];
							idTransazioneSuccessiva = StringUtils.remove(lymovSuccessiva.getId().getLydat() + "-" + lymovSuccessiva.getId().getLycau() + "-" + lymovSuccessiva.getId().getLynuz() + "-" + lymovSuccessiva.getId().getLynum(), " ");
						}
						
						log.info("lymov: " + lymov.toString());
						log.info("lymovSuccessiva: " + ((lymovSuccessiva == null) ? "" : lymovSuccessiva.toString()));
						log.info("idTransazione: " + idTransazione);
						log.info("idTransazioneSuccessiva: " + idTransazioneSuccessiva);
						
						lyprgScontrino += "-" + lymov.getId().getLyprg();
						log.info("lyprgScontrino: " + lyprgScontrino);
						
						if(StringUtils.equals(idTransazione, idTransazioneSuccessiva)){
							// se sono uguali, anche il rigo successivo fa parte dello stesso scontrino
							// non lancio il caricamento che e' dopo e continuo con il giro successivo
							continue;							
						}
						
						totaleArticoliLoyalty = 0.0f;

						creaScontrino(lymov, idTransazione, StringUtils.split(lyprgScontrino, "-"));
						caricamentoPunti(lymov, cmd);
						
						lyprgScontrino = "";
					}
				}else if(funzione.compareTo("saldoPunti") == 0){
					setDatiClientPerScontrino(codicePDV);
					saldoPunti(codiceCard, cmd);
				}else if(funzione.compareTo("saldoPuntiPDV") == 0){
					setDatiClientPerScontrino(codicePDV);
					saldoPuntiPDV(cmd);
				}else if(funzione.compareTo("stopProgramma") == 0) {
					stringBuilder = new StringBuilder();
					stringBuilder.append("stoppato" + sepStatoRisposta + "stoppato");
					scriviRispostaInDb(cmd);
					stopProgramma = true;
				}else if(funzione.compareTo("setFrequenza") == 0) {
					stringBuilder = new StringBuilder();
					stringBuilder.append("frequenza modificata" + sepStatoRisposta + "frequenza modificata");
					scriviRispostaInDb(cmd);
					// frequenza viene settata con parametro -ms
				}
			}
			
			if(stopProgramma){
				break;
			}
			
			try{
				Thread.sleep(frequenza);
			}catch(InterruptedException e){
			}
		}
		
		asLycmd0fDao.close();
		asLymov0fDao.close();
		asMovma0fDao.close();
		asAnmag0fDao.close();
		asAafor0fDao.close();
		asNativeQueryDao.close();
		
		log.info("]" + "Loyalty");
	}
	
	private String connectUrl(String funzione){
		log.info("[" + "connectUrl");
		
		String strUrl = "";
		if(StringUtils.equals(funzione, "assegna_punti")){
			strUrl = "http://serv.promoshops.it/assegna_punti_test.asp?ver=3.2&az=*az*&card=*card*&data_ora=*data_ora*&cassa=*cassa*&scontrino=*scontrino*&importo=*importo*&sec=*sec*";
			strUrl = StringUtils.replace(strUrl, "*az*", codicePDV);
			strUrl = StringUtils.replace(strUrl, "*card*", codiceCard);
			strUrl = StringUtils.replace(strUrl, "*data_ora*", dataOraScontrino);
			strUrl = StringUtils.replace(strUrl, "*cassa*", cassaScontrino);
			strUrl = StringUtils.replace(strUrl, "*scontrino*", numeroScontrino);
			strUrl = StringUtils.replace(strUrl, "*importo*", floatToString(totaleArticoliLoyalty));
			strUrl = StringUtils.replace(strUrl, "*sec*", calcolaMD5(codicePDV + dataEsecuzione + password + codiceCard));
			log.info("Carica punti: " + StringUtils.substringAfter(strUrl, "?"));
		}else if(StringUtils.equals(funzione, "saldo_punti")){
			strUrl = "http://serv.promoshops.it/saldo_punti.asp?ver=2.1&az=*az*&card=*card*&sec=*sec*";
			strUrl = StringUtils.replace(strUrl, "*az*", codicePDV);
			strUrl = StringUtils.replace(strUrl, "*card*", codiceCard);
			strUrl = StringUtils.replace(strUrl, "*sec*", calcolaMD5(codicePDV + dataEsecuzione + password + codiceCard));
		}else if(StringUtils.equals(funzione, "check_saldo_azienda")){
			strUrl = "http://serv.promoshops.it/check_saldo_azienda.asp?ver=2.1&az=*az*&sec=*sec*";
			strUrl = StringUtils.replace(strUrl, "*az*", codicePDV);
			strUrl = StringUtils.replace(strUrl, "*sec*", calcolaMD5(codicePDV + dataEsecuzione + password));
		}else{
			log.fatal("funzione " + funzione + " non conosciuta");
			return "KO";
		}
		
		String risp = "";
		
		URL url;
        try{
        	url = new URL(strUrl);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	risp = inputLine;
            }
            br.close();
        }catch (MalformedURLException e) {
            e.printStackTrace();
            log.fatal(e.toString());
			return "KO";
        }catch (IOException e) {
            e.printStackTrace();
            log.fatal(e.toString());
			return "KO";
		}catch(Exception e){
			e.printStackTrace();
			log.fatal(e.toString());
			return "KO";
		}
		
        risp = StringUtils.replace(risp, "{", "");
        risp = StringUtils.replace(risp, "}", "");
        risp = StringUtils.replace(risp, "\"", "");
        risp = StringUtils.replace(risp, ",", sepCampiRisposta);
        String inizRisp = StringUtils.substringBefore(risp, sepCampiRisposta);
        risp = risp + sepStatoRisposta;
		if(StringUtils.equals(inizRisp, "esito: OK")){
			risp = risp + "0" + sepCampiRisposta + inizRisp;
			risp = StringUtils.removeStart(risp, inizRisp + sepCampiRisposta);
		}else{
			risp = risp + "9" + sepCampiRisposta + inizRisp + sepCampiRisposta + inizRisp + sepCampiRisposta;
		}
        
        log.info("Risposta: " + risp);
        
		log.info("]" + "connectUrl");
		return risp;
	}
	
	private String calcolaMD5(String str){
		log.info("[" + "calcolaMD5");
		
		String md5 = "";
	   	MessageDigest m = null;
		try{
			m = MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	    m.update(str.getBytes(), 0, str.length());
	    md5 = StringUtils.leftPad(new BigInteger(1, m.digest()).toString(16), 32, "0");
	  	log.info("MD5:  " + md5);
		
		log.info("]" + "calcolaMD5");
		return md5;
	}
	
	private void setDatiClientPerScontrino(String lycau){
		log.info("[" + "setDatiClientPerScontrino");
		
		Integer numDep = Integer.parseInt(StringUtils.right(StringUtils.trimToEmpty(lycau), 1));
		switch(numDep){
			case 2: 	// termini
				codiceOperatore = "4042";	// sono i parametri di Test
				codicePDV = "4042";
				password = "gshgsyysjh2h238723hhjh82yuhhsjhivcvHggsb1234";
				break;
			case 5: 	// lascari
				codiceOperatore = "";
				codicePDV = "";
				password = "";
				break;
			case 6: 	// bagheria
				codiceOperatore = "2965749";
				codicePDV = "2965749";
				password = "5jTjd5QCJFwJ";
				break;
			case 8: 	// brolo
				codiceOperatore = "";
				codicePDV = "";
				password = "";
				break;
			case 9: 	// enna
				codiceOperatore = "";
				codicePDV = "";
				password = "";
				break;
			case 10: 	// sciacca
				codiceOperatore = "";
				codicePDV = "";
				password = "";
				break;
			default:	// termini
				codiceOperatore = "";
				codicePDV = "";
				password = "";
				break;
		}
		codiceClient = "1";
		
		log.info("]" + "setDatiClientPerScontrino");
	}
	
	private void manageRisposta(String strRisposta, AsLycmd0f cmd){
		log.info("[" + "manageRisposta");
		stringBuilder = new StringBuilder();
		stringBuilder.append(strRisposta);
		
		scriviRispostaInDb(cmd);
		
		log.info("]" + "manageRisposta");
	}
	
	private void scriviRispostaInDb(AsLycmd0f cmd){
		log.info("[" + "scriviRispostaInDb");
		String risp = stringBuilder.substring(0, stringBuilder.indexOf(sepStatoRisposta));
		risp = risp + "|";
		String statoRisposta = stringBuilder.substring(stringBuilder.indexOf(sepStatoRisposta) + 1);		
		statoRisposta = StringUtils.left(statoRisposta, 256);
		
		String rispostaSplit[] = risp.split("(?<=\\G.{256})");
		
		cmd.setLyris1(rispostaSplit[0]);
		
		cmd.setLyris8(statoRisposta);
		asLycmd0fDao.updateRis(cmd);
		log.info("]" + "scriviRispostaInDb");
	}
	
	private void saldoPunti(String codiceCard, AsLycmd0f cmd){
		log.info("[" + "saldoPunti");
		
		String risParz = connectUrl("saldo_punti");
		
		manageRisposta(risParz, cmd);
		log.info("]" + "saldoPunti");
	}

	
	private void saldoPuntiPDV(AsLycmd0f cmd){
		log.info("[" + "saldoPuntiPDV");
		
		String risParz = connectUrl("check_saldo_azienda");

		manageRisposta(risParz, cmd);
		log.info("]" + "saldoPuntiPDV");
	}

	private String creaScontrino(AsLymov0f lymov, String idTransazione, String righeScontrino[]){
		log.info("[" + "creaScontrino");
		String scontrino = new String();
		
		log.info("lymov: " + lymov);
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
//		Date now = Calendar.getInstance().getTime();
//		String orario = dateFormat.format(now);
		
		
		setDatiClientPerScontrino(lymov.getId().getLycau());
		dataOraScontrino = Integer.toString(lymov.getId().getLydat());
		dataOraScontrino = StringUtils.join(StringUtils.left(dataOraScontrino, 4), "-", StringUtils.substring(dataOraScontrino, 4, 6), "-", StringUtils.right(dataOraScontrino, 2), "%20", "00:00:00");
		cassaScontrino =  Integer.toString(lymov.getId().getLynuz());
		numeroScontrino =  Integer.toString(lymov.getId().getLynum());
		codiceCard = lymov.getLyean();

		creaArrayOfDettaglioScontrino(lymov, righeScontrino);
		
		String importo = floatToString(totaleArticoliLoyalty);
		log.info("importo: " + importo);
		log.info("]" + "creaScontrino");
		return scontrino;
	}
	
	private void creaArrayOfDettaglioScontrino(AsLymov0f lymov, String righeScontrino[]){
		log.info("[" + "creaArrayOfDettaglioScontrino");

		for(int i = 0; i < righeScontrino.length; i++){
			log.info("rigo scontrino: " + righeScontrino[i]);
			creaDettaglioScontrino(lymov, righeScontrino[i]);
		}

		log.info("]" + "creaArrayOfDettaglioScontrino");
	}
	
	private void creaDettaglioScontrino(AsLymov0f lymov, String numRigoScontrino){
		log.info("[" + "creaDettaglioScontrino");
		
		AsMovma0f movma = new AsMovma0f();
		movma = asMovma0fDao.getMovCassaConRigo(lymov.getId().getLydat(), lymov.getId().getLycau(), lymov.getId().getLynuz(), lymov.getId().getLynum(), Integer.parseInt(numRigoScontrino));
		log.info("articolo: " + movma.getVcoda());
		
		String vtime = StringUtils.right("0" + Integer.toString(movma.getVtime()), 4);
		vtime = StringUtils.join(StringUtils.left(vtime, 2), ":", StringUtils.right(vtime, 2), ":", "00");
		dataOraScontrino = StringUtils.replace(dataOraScontrino, "00:00:00", vtime);
		
		AsAnmag0f anmag = new AsAnmag0f();
		AsAafor0f aafor = new AsAafor0f();
		
		anmag = asAnmag0fDao.getArticoloDaMcoda(movma.getVcoda());
		if(anmag == null){
			log.warn(movma.getVcoda() + " non presente o annullato in Anmag0f");
			aafor = null;
		}else{
			aafor = asAafor0fDao.getArticoloDaAaforAacoa(anmag.getMcofo(), anmag.getMcoaf());
		}
		// in caso non trovassi l'articolo in AsAafor0f
		if(aafor == null){
			log.warn(anmag + " non presente in Aafor0f");
			aafor = new AsAafor0f();
			aafor.getId().setAacoa("artNoInAafor");
			aafor.getId().setAafor(777);
			aafor.setAasfa("artNoInAafor");
		}
				
		log.info("movma: " + movma);
		log.info("anmag: " + anmag);
		log.info("aafor: " + aafor);

		Float prezzoTot = movma.getVquan() * movma.getVprez();
		prezzoTot = prezzoTot - (prezzoTot * movma.getVsco1() / 100.0f);
		prezzoTot = prezzoTot - (prezzoTot * movma.getVsco2() / 100.0f);
		prezzoTot = prezzoTot - (prezzoTot * movma.getVsco3() / 100.0f);
		String iva = movma.getVciva();
		try{
			Integer.parseInt(iva);
		}catch(NumberFormatException e){
			iva = "0.0";
		}
		prezzoTot = prezzoTot + (prezzoTot * Float.parseFloat(iva) / 100.0f);
		
		// aggiorno il totale dell'intero scontrino
		totaleArticoliLoyalty += prezzoTot;
		
		log.info("]" + "creaDettaglioScontrino");
	}
	
	private void caricamentoPunti(AsLymov0f lymov, AsLycmd0f cmd){
		log.info("[" + "caricamentoPunti");
		
		setDatiClientPerScontrino(lymov.getId().getLycau());		
		
		String risParz = connectUrl("assegna_punti");
		manageRisposta(risParz, cmd);
		
		String risposta[] = StringUtils.split(stringBuilder.toString(), sepStatoRisposta);
		risposta[0] = StringUtils.split(risposta[1], sepCampiRisposta)[0];
		if(StringUtils.equals(risposta[0], "0")){
			asLymov0fDao.updateElab(lymov);
		}
		log.info("]" + "caricamentoPunti");
	}
	
	private String floatToString(Float f){
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ITALY);
		otherSymbols.setDecimalSeparator('.');
		DecimalFormat decimalFormatPrezzo = new DecimalFormat("#.##", otherSymbols);
		decimalFormatPrezzo.setRoundingMode(RoundingMode.HALF_UP);
		String fToStr = decimalFormatPrezzo.format(f);
		return fToStr;
	}
}