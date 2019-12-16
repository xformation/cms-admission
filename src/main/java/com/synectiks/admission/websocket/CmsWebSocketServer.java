package com.synectiks.admission.websocket;

import java.io.IOException;
import java.util.Random;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Query Resolver for CMS Admission Queries
 *
 */
//@Component
//@ServerEndpoint("/actions")
public class CmsWebSocketServer { //extends WebSocketServer {

	private final static Logger logger = LoggerFactory.getLogger(CmsWebSocketServer.class);
    
//    @Autowired
//    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;
//
//    public List<CmsAdmissionEnquiryVo> getAdmissionEnquiryList(Long branchId, Long academicYearId, String enquiryStatus) throws Exception, Exception {
//    	logger.debug("Query - getAdmissionEnquiryList :- Branch Id : "+branchId+", academicYearId : "+academicYearId+", enquiryStatus : "+enquiryStatus);
//    	return this.cmsAdmissionEnquiryService.getAdmissionEnquiryList(branchId, academicYearId, enquiryStatus);
//    }

//	@OnOpen
//	public void open(Session session) {
////		conns.add(webSocket);
//
//        logger.info("Connection established from: " + session.getRequestURI());
//	}
////
//	@OnClose
//	public void close(Session session) {
////		conns.remove(conn);
//        // When connection is closed, remove the user.
////        try {
////            removeUser(conn);
////        } catch (JsonProcessingException e) {
////            e.printStackTrace();
////        }
//
//        logger.info("Connection closed to: " );
////        logger.info("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
//		
//	}
////
//	@OnMessage
//	public void handleMessage(String message, Session session) {
//		message="Helo from admission -- ";
//		try {
//	         session.getBasicRemote().sendText
//	            ("Received message "+": " + message);
//	         session.getBasicRemote().sendText
//	            ("Lucky number! "+new Random().nextInt(99));
//	         session.getBasicRemote().sendText
//	            ("-----------------------------------");
//	      } catch (IOException ex) {
//	    	  ex.printStackTrace();
//	      }
//		
//	}
////
//	@OnError
//	public void onError(Throwable error) {
////		if (conn != null) {
////            conns.remove(conn);
////        }
////		assert conn != null;
//        logger.error("ERROR from " + error);
//	}
    
	
}
