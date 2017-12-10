/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import de.elbosso.dataflowframework.modules.RemoteModule;
public class RemotingExample extends RemoteModule<Hello>
{
public RemotingExample()
{
super(RemotingExample.class.getName());
}
protected void installWorker(java.lang.String newLocation)
{
installWorker(newLocation, HelloImpl.class, new Class[]
{
Hello.class
});
}
protected Hello createLocalInstance()
{
return new HelloImpl();
}
public void input(Object in)
{
processData(in);//Hierdurch Start des Algorithmus
}
private Object data;
public synchronized Object getData()
{
return data;
}
public synchronized void setData(Object data)
{
java.lang.Object old = getData();
this.data = data;
send("data", old, getData());
}
@Override
protected void doWork(Object ref) throws InterruptedException
{
Hello h = getRemoteObject();
if (h != null)
{
setData(h.hello());
}
}
}