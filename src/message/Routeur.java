package message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;

import database.PoolConnexion;
import model.ConnectionDB;
import model.ListPret;
import model.Pret;

public class Routeur {
	
	private DataOutputStream dataOutputStream = null;
	private PoolConnexion poolConnexion = null;

	public Routeur(DataOutputStream outputClient, PoolConnexion pc) {
		this.dataOutputStream = outputClient;
		this.poolConnexion = pc;
	}

	public void analyse(Message message) {
		
		System.out.println("Message analysé : "+message.toString());
		
		switch (message.getMethode()) {
		case "GET":
			this.dealingGet(message);
			break;
		case "POST":
			this.dealingPost(message);
			break;
		case "PUT":
			this.dealingPut(message);
			break;
		case "DELETE":
			this.dealingDelete(message);
			break;
		default:
			try {
				throw new Exception("Bad methode ...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}
	
	private void dealingGet(Message message) {

		/* get list of resource ELSE just a resource */
		if (message.getResourceId() == -1) {
			switch (message.getResourceType()) {
			case "pret":
				/* Need a new connectionDB for interact with it (Pool)*/
				ConnectionDB cdb = new ConnectionDB(this.poolConnexion.pop().getConnection());
				/* Get all the pret beacause we know client need it ;) */
				ListPret lp = new ListPret(cdb.getPrets());
				/* Send list of pret in xml over the socket */
				try {
					dataOutputStream.writeBytes(lp.toXML()+'\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(message.toString());
				break;

			default:
				break;
			}
		} else {
			switch (message.getResourceType()) {
			case "pret":
				/* Need a new connectionDB for interact with it (Pool)*/
				ConnectionDB cdb = new ConnectionDB(this.poolConnexion.pop().getConnection());
				/* Get all the pret beacause we know client need it ;) */
				Pret p = Pret.get(this.poolConnexion.pop().getConnection(), cdb, message.getResourceId());
				/* Send list of pret in xml over the socket */
				try {
					dataOutputStream.writeBytes(p.toXML()+'\n');
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(message.toString());
				break;

			default:
				break;
			}
		}

	}
	
	private void dealingPost(Message message) {
		
		Connection conn = this.poolConnexion.pop().getConnection(); 
		Integer res = new Integer(0);
		switch (message.getResourceType()) {
		case "pret":
			System.out.println("detect resource : pret");
			Pret p = new Pret();
			p.parseXML(message.getFullMsgString());
			res = p.insert(conn);
			break;

		default:
			break;
		}
		
		try {
			dataOutputStream.writeBytes("<message><code>"+res.toString()+"</code></message>"+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(message.toString());
	}
	
	private void dealingPut(Message message) {
		
	}
	
	private void dealingDelete(Message message) {
		
		System.out.println(message.toString());
		Connection conn = this.poolConnexion.pop().getConnection();
		
		switch (message.getResourceType()) {
		case "pret":
			//Integer res = Pret.deletePret(this.poolConnexion.pop().getConnection(), message.getResourceId());
			
			Pret p = new Pret();
			p.setIdPret(message.getResourceId());
			Integer res = p.delete(conn);
			
			try {
				dataOutputStream.writeBytes("<message><code>"+res.toString()+"</code></message>"+'\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}
		
		System.out.println(message.toString());
	}
}
