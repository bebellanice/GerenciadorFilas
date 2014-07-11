package br.com.lunarconsultoria.mel.websocket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet("/filas/painelSocket")
public class PainelWebSocketServlet extends WebSocketServlet {

	private static final List<ConnectionWS> connections = new ArrayList<ConnectionWS>();
	
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		return new ConnectionWS();
	}

	public static final List<ConnectionWS> getConnections() {
		return connections;
	}
	
	public static final void broadcast(String message){
		
		for (ConnectionWS connection : PainelWebSocketServlet.getConnections()) {
			try {
				connection.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				System.out.println("Enviando mensagem de texto ("+message+")");
			} catch (IOException e) {
				System.out.println("Ocorreu um erro ao enviar a mensagem");
			}
			
		}
		
	}
	

}
