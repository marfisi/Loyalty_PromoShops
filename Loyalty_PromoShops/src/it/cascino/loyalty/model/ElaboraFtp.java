package it.cascino.loyalty.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import it.cascino.loyalty.dao.AsLyanc0fDao;
import it.cascino.loyalty.dao.AsLycmd0fDao;
import it.cascino.loyalty.managmentbean.AsLyanc0fDaoMng;
import it.cascino.loyalty.managmentbean.AsLycmd0fDaoMng;

public class ElaboraFtp{
	private Logger log = Logger.getLogger(ElaboraFtp.class);
	
	private String basePathInput = "C:/dev/Loyalty/ftp/";
	private String fileNameInput = basePathInput + "estrazione_card_V1_1.txt";
	
	private File fileInput;
	
	private List<String> lstRowInput = new ArrayList<String>();
	
	private List<AsLyanc0f> asLyanc0fDaFileLs = new ArrayList<AsLyanc0f>();
	
	private final String separator = "\t"; // tab
	
	private AsLycmd0fDao asLycmd0fDao = new AsLycmd0fDaoMng();
	
	public void elaboraTracciato(String args[], AsLycmd0f cmd){
		for(int numArg = 0; numArg < args.length; numArg++){
			if(args[numArg].compareTo("-dI") == 0) {
				numArg++;
				basePathInput = args[numArg];
			}else if(args[numArg].compareTo("-fI") == 0) {
				numArg++;
				fileNameInput = basePathInput + args[numArg];
			}else{ // se c'e' almeno un parametro e non e' tra quelli previsti stampo il messaggio d'aiuto
			}
		}
		
		fileInput = new File(fileNameInput);
		
		String line = null;
		try{
			FileReader fstream = new FileReader(fileInput);
			BufferedReader in = new BufferedReader(fstream);
			
			int rigo = 0;
			while((line = in.readLine()) != null){
				rigo++;
				
				// System.out.println("rigo: " + rigo);
				log.info("rigo: " + rigo);
				
				if(line.isEmpty()) {
					continue;
				}
				lstRowInput.add(line);
			}
			
			// Close the input stream
			in.close();
			
			elaboraLstRowInput();
			
			aggiornaAsLyanc0f();
			
			cmd.setLyris1("OK-Caricamento FTP eseguito");
			cmd.setLyris8("OK-Caricamento FTP eseguito");
			asLycmd0fDao.updateRis(cmd);
			asLycmd0fDao.close();
		}catch(IOException ioe){// Catch exception if any
			// System.err.println("Error: " + ioe.getMessage());
			log.fatal("Error: " + ioe.getMessage());
			cmd.setLyris1("Error: " + ioe.getMessage());
			cmd.setLyris8("Error: " + ioe.getMessage());
			asLycmd0fDao.updateRis(cmd);
		}
	}
	
	private void elaboraLstRowInput(){
		Iterator<String> iter_fileFtp;
		iter_fileFtp = lstRowInput.iterator();
		String value = null;
		String values[] = null;
		
		AsLyanc0f a = null;
		while(iter_fileFtp.hasNext()){
			value = (String)iter_fileFtp.next();
			
			if(value == null) {
				break;
			}
			
			a = new AsLyanc0f();
			
			// gestisto il multi tab senza contenuto nel mezzo
			value = StringUtils.replace(value, separator + separator + separator + separator, separator + " " + separator + " " + separator + " " + separator);
			value = StringUtils.replace(value, separator + separator + separator, separator + " " + separator + " " + separator);
			value = StringUtils.replace(value, separator + separator, separator + " " + separator);
			values = StringUtils.split(value, separator);
			
			// values[0]; // e' il RS079
			
			// letti da file
			a.setLypdv((StringUtils.isEmpty(StringUtils.trimToEmpty(values[1]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[1], 50)));
			try{
				a.setLycli((StringUtils.isEmpty(StringUtils.trimToEmpty(values[2]))) ? 0 : Integer.parseInt(StringUtils.trimToEmpty(StringUtils.left(values[2], 5))));
			}catch(NumberFormatException e){
				a.setLycli(0);
			}
			a.setLyean((StringUtils.isEmpty(StringUtils.trimToEmpty(values[3]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[3], 13)));
			a.setLynom((StringUtils.isEmpty(StringUtils.trimToEmpty(values[4]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[4], 50)));
			a.setLycog((StringUtils.isEmpty(StringUtils.trimToEmpty(values[5]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[5], 50)));
			a.setLydna(dataDD_MM_YYYYtoYYYYMMDD(StringUtils.trim(StringUtils.left(values[6], 10))));
			a.setLyind((StringUtils.isEmpty(StringUtils.trimToEmpty(values[7]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[7], 50)));
			a.setLynci((StringUtils.isEmpty(StringUtils.trimToEmpty(values[8]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[8], 10)));
			a.setLycap((StringUtils.isEmpty(StringUtils.trimToEmpty(values[9]))) ? 0 : Integer.parseInt(StringUtils.trimToEmpty(StringUtils.left(values[9], 5))));
			a.setLyloc(StringUtils.upperCase((StringUtils.isEmpty(StringUtils.trimToEmpty(values[10]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[10], 50))));
			a.setLypro((StringUtils.isEmpty(StringUtils.trimToEmpty(values[11]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[11], 2)));
			a.setLypry((StringUtils.isEmpty(StringUtils.trimToEmpty(values[12]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[12], 10)));
			a.setLypun((StringUtils.isEmpty(StringUtils.trimToEmpty(values[13]))) ? 0 : Integer.parseInt(StringUtils.trimToEmpty(StringUtils.left(values[13], 5))));
			a.setLysta((StringUtils.isEmpty(StringUtils.trimToEmpty(values[14]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[14], 20)));
			a.setLyste((StringUtils.isEmpty(StringUtils.trimToEmpty(values[15]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[15], 256)));
			a.setLybuo((StringUtils.isEmpty(StringUtils.trimToEmpty(values[16]))) ? "" : StringUtils.trimToEmpty(StringUtils.left(values[16], 10)));
			a.setLymai(StringUtils.left(StringUtils.lowerCase((StringUtils.isEmpty(StringUtils.trimToEmpty(values[17]))) ? "" : StringUtils.trimToEmpty(values[17])) + StringUtils.trimToEmpty(values[18]), 100));
			a.setLytel(StringUtils.left(StringUtils.lowerCase((StringUtils.isEmpty(StringUtils.trimToEmpty(values[19]))) ? "" : StringUtils.trimToEmpty(values[19])) + StringUtils.trimToEmpty(values[20]), 20));
			
			// tutti i dati seguenti sono gia' aggiornati da alessandra e agostino
//			a.setLyses("M");
//			a.setLycfp("EEEEEEEEE");
//			a.setLydcr(20150101); // data creazione
//			a.setLydup(20150130); // data aggiornamento
//			a.setLyduv(20150115); // data ultima visita
//			a.setLyvis(123); // numero visite
//			a.setLytsp(123456.54f); // totale importo speso
//			a.setLyimo(187.13f); // importo massima operazione
//			a.setLynal(77); // numero articoli loyalty
//			a.setLytar(555); // numero totale articoli
			
			if(a != null) {
				asLyanc0fDaFileLs.add(a);
			}
		}
	}
	
	private void aggiornaAsLyanc0f(){
		AsLyanc0fDao asLyanc0fDao = new AsLyanc0fDaoMng();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date now = Calendar.getInstance().getTime();
		String data = dateFormat.format(now);

		Iterator<AsLyanc0f> iter_asLyanc0fDaFile;
		iter_asLyanc0fDaFile = asLyanc0fDaFileLs.iterator();
		AsLyanc0f aInFile = null;
		AsLyanc0f aInDb = null;
		int rigo = 0;
		while(iter_asLyanc0fDaFile.hasNext()){
			rigo++;
			aInFile = iter_asLyanc0fDaFile.next();
			log.info("rigo: " + rigo + ", codcli: " + aInFile.getLycli() + ", ean: " + aInFile.getLyean()  + ", cogn: " + aInFile.getLycog());
			
			aInDb = asLyanc0fDao.getAnagConLyean(aInFile.getLyean());
			if(aInDb == null) {
				aInDb = asLyanc0fDao.getAnagConLycli(aInFile.getLycli());
			}
			if(aInDb != null) {
				log.info(aInDb);
				Boolean daAggiornare = false;
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLypdv()), StringUtils.trimToEmpty(aInFile.getLypdv())))){
					aInDb.setLypdv(StringUtils.trimToEmpty(aInFile.getLypdv()));
					daAggiornare = true;
				}
				if(!(aInDb.getLycli().equals(aInFile.getLycli()))){
//					aInDb.setLycli(aInFile.getLycli());
//					daAggiornare = true;
					log.warn("lycli diversi in DB e File - DB: " + aInDb.getLycli() + " File: " + aInFile.getLycli());
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLyean()), StringUtils.trimToEmpty(aInFile.getLyean())))){
					aInDb.setLyean(StringUtils.trimToEmpty(aInFile.getLyean()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLynom()), StringUtils.trimToEmpty(aInFile.getLynom())))){
					aInDb.setLynom(StringUtils.trimToEmpty(aInFile.getLynom()));
					daAggiornare = true;
				}				
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLycog()), StringUtils.trimToEmpty(aInFile.getLycog())))){
					aInDb.setLycog(StringUtils.trimToEmpty(aInFile.getLycog()));
					daAggiornare = true;
				}
				// aInDb.setLyses	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLycfp	// rimane quello del db, dato che nel file non e' presente
				if(!(aInDb.getLydna().equals(aInFile.getLydna()))){
					aInDb.setLydna(aInFile.getLydna());
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLyind()), StringUtils.trimToEmpty(aInFile.getLyind())))){
					aInDb.setLyind(StringUtils.trimToEmpty(aInFile.getLyind()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLynci()), StringUtils.trimToEmpty(aInFile.getLynci())))){
					aInDb.setLynci(StringUtils.trimToEmpty(aInFile.getLynci()));
					daAggiornare = true;
				}
				if(!(aInDb.getLycap().equals(aInFile.getLycap()))){
					aInDb.setLycap(aInFile.getLycap());
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLyloc()), StringUtils.trimToEmpty(aInFile.getLyloc())))){
					aInDb.setLyloc(StringUtils.trimToEmpty(aInFile.getLyloc()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLypro()), StringUtils.trimToEmpty(aInFile.getLypro())))){
					aInDb.setLypro(StringUtils.trimToEmpty(aInFile.getLypro()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLypry()), StringUtils.trimToEmpty(aInFile.getLypry())))){
					aInDb.setLypry(StringUtils.trimToEmpty(aInFile.getLypry()));
					daAggiornare = true;
				}
				if(!(aInDb.getLypun().equals(aInFile.getLypun()))){
					aInDb.setLypun(aInFile.getLypun());
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLysta()), StringUtils.trimToEmpty(aInFile.getLysta())))){
					aInDb.setLysta(StringUtils.trimToEmpty(aInFile.getLysta()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLyste()), StringUtils.trimToEmpty(aInFile.getLyste())))){
					aInDb.setLyste(StringUtils.trimToEmpty(aInFile.getLyste()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLybuo()), StringUtils.trimToEmpty(aInFile.getLybuo())))){
					aInDb.setLybuo(StringUtils.trimToEmpty(aInFile.getLybuo()));
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLymai()), StringUtils.trimToEmpty(aInFile.getLymai())))){
					if(StringUtils.isBlank(aInDb.getLymai())){
						aInDb.setLymai(StringUtils.trimToEmpty(aInFile.getLymai()));
					}else{
						aInDb.setLyste(StringUtils.trimToEmpty(aInFile.getLymai()) + " | " + StringUtils.trimToEmpty(aInFile.getLytel()));						
					}
					daAggiornare = true;
				}
				if(!(StringUtils.equals(StringUtils.trimToEmpty(aInDb.getLytel()), StringUtils.trimToEmpty(aInFile.getLytel())))){
					if(StringUtils.isBlank(aInDb.getLytel())){
						aInDb.setLytel(StringUtils.trimToEmpty(aInFile.getLytel()));
					}else{
						aInDb.setLyste(StringUtils.trimToEmpty(aInFile.getLymai()) + " | " + StringUtils.trimToEmpty(aInFile.getLytel()));						
					}
					daAggiornare = true;
				}
				// aInDb.setLydcr	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLydup	// e' gestita alla fine, se c'e' almeno un aggiornamento
				// aInDb.setLyduv	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLyvis	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLytsp	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLyimo	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLynal	// rimane quello del db, dato che nel file non e' presente
				// aInDb.setLytar	// rimane quello del db, dato che nel file non e' presente

				if(daAggiornare){
					aInDb.setLydup(Integer.parseInt(data)); // data aggiornamento
					asLyanc0fDao.updateAnagrafica(aInDb);
					log.info("aggiorno con: " + aInDb);
				}
			}else if(aInDb == null) { // allora non c'e' nessuna entry con questo codice ean, quindi nuova registrazione (non dovrebbe capitare mai dato che alessandra dovrebbe inserire i dati appena registrati)
				aInFile.setLydcr(Integer.parseInt(data)); // data creazione
				aInFile.setLydup(Integer.parseInt(data)); // data aggiornamento
				aInFile.setLyses("");
				aInFile.setLycfp("");
				aInFile.setLyduv(20150101); // data ultima visita
				aInFile.setLyvis(1); // numero visite
				aInFile.setLytsp(0.0f); // totale importo speso
				aInFile.setLyimo(0.0f); // importo massima operazione
				aInFile.setLynal(0); // numero articoli loyalty
				aInFile.setLytar(0); // numero totale articoli
				
				// il log e' in error perche' non dovrebbe capitare mai di aggiungere un cliente da questa procedura, dato che dovrebbero essere inserite a priori dalla procedura di alessandra da php
				log.error("aggiunto cliente: " + aInFile.toString());
				asLyanc0fDao.salva(aInFile);
			}
		}
	}
	
	private Integer dataDD_MM_YYYYtoYYYYMMDD(String d){
		d = StringUtils.remove(d, "/");
		d = StringUtils.substring(d, 4, 8) + StringUtils.substring(d, 2, 4) + StringUtils.substring(d, 0, 2);
		return Integer.parseInt(d);
	}
}
