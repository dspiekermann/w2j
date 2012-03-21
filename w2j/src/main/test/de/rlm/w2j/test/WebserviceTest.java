package de.rlm.w2j.test;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.rlm.w2j.servlet.W2JServlet;
import de.rlm.wsdl.BLZServiceStub;
import de.rlm.wsdl.BLZServiceStub.DetailsType;
import de.rlm.wsdl.BLZServiceStub.GetBank;
import de.rlm.wsdl.BLZServiceStub.GetBankResponse;
import de.rlm.wsdl.BLZServiceStub.GetBankResponseType;
import de.rlm.wsdl.BLZServiceStub.GetBankType;

public class WebserviceTest extends TestCase {
	
	private static final Log LOG = LogFactory.getLog(W2JServlet.class); 
	
	public void test() throws RemoteException{
		
		BLZServiceStub blzService = new BLZServiceStub();
		GetBank getBank = new GetBank();
		GetBankType getBankType = new GetBankType();
		getBankType.setBlz("37050198");
		getBank.setGetBank(getBankType);
		GetBankResponse getBankResponse = blzService.getBank(getBank);
		GetBankResponseType getBankResponseType = getBankResponse.getGetBankResponse();
		DetailsType detailsType = getBankResponseType.getDetails();
		String bezeichnung = detailsType.getBezeichnung();
		String bic = detailsType.getBic();
		String ort = detailsType.getOrt();
		String plz = detailsType.getPlz();
		
		LOG.info(bezeichnung + ' ' + bic + ' ' + ort + ' ' + plz);
		
	}

}
