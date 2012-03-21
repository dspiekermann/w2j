package de.rlm.w2j.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.rlm.wsdl.BLZServiceStub;
import de.rlm.wsdl.BLZServiceStub.DetailsType;
import de.rlm.wsdl.BLZServiceStub.GetBank;
import de.rlm.wsdl.BLZServiceStub.GetBankResponse;
import de.rlm.wsdl.BLZServiceStub.GetBankResponseType;
import de.rlm.wsdl.BLZServiceStub.GetBankType;

/**
 * Servlet implementation class W2JServlet
 */
@WebServlet("/W2JServlet")
public class W2JServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log LOG = LogFactory.getLog(W2JServlet.class); 
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		LOG.info("servlet init");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("servlet get");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("servlet post");
		
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
		
		String message = bezeichnung + ' ' + bic + ' ' + ort + ' ' + plz;
		LOG.info(message);
		response.getWriter().write(message);
	}

}
