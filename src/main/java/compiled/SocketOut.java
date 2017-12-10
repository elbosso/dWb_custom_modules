/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.net.InetAddress;
public class SocketOut extends
de.netsysit.dataflowframework.modules.CommunicationTemplate
{
public void sendData(java.lang.String data)
{
if(isConnectionEstablished())
{
pw.println(data);
}
}
private String host;
public String getHost()
{
return host;
}
public void setHost(String adr)
{
String oldAdr = getHost();
this.host = adr;
send("host", oldAdr, getHost());
}
private int port;
public void setPort(int p)
{
int oldPort = getPort();
this.port = p;
send("port", oldPort, getPort());
}
public int getPort()
{
return port;
}
private Socket socket;
private PrintWriter pw;
protected void manageConnectionImpl()
{
boolean old=isConnectionEstablished();
try
{
if(port>-1)
{
socket = new Socket(InetAddress.getByName(host), port);
pw=new PrintWriter(socket.getOutputStream());
}
}
catch (IOException ex)
{
//warn für die Meldung eines Problems
warn(null,ex.getMessage());
}
manageConnectionEstablished(old,socket!=null);
}
protected void closeDown()
{
boolean old=isConnectionEstablished();
if(socket!=null)
{
try
{
pw.close();
socket.close();
}
catch (IOException ex)
{
//error für die Meldung eines kritischen Fehlers
	
	error(null,ex.getMessage());
}
socket=null;
}
manageConnectionEstablished(old,socket!=null);
}
}