package br.com.lunarconsultoria.mel.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

public class ConnectionWS extends MessageInbound{

	public ConnectionWS() {
	}
	
	@Override
	protected void onOpen(WsOutbound outbound) {
		PainelWebSocketServlet.getConnections().add(this);
		System.out.println("Painel conectado");
	}

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new RuntimeException("Metodo nao aceito");
	}

	@Override
	protected void onTextMessage(CharBuffer msg) throws IOException {
		System.out.println("Proximo");
	}

}
