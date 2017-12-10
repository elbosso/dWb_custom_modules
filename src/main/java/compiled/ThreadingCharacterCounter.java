/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import de.netsysit.dataflowframework.modules.ThreadingModuleBase;
import de.netsysit.util.threads.CubbyHole;
import de.netsysit.util.threads.SimpleBufferingCubbyHole;
public class ThreadingCharacterCounter extends ThreadingModuleBase
{
public ThreadingCharacterCounter()
{
super(ThreadingCharacterCounter.class.getName());
}
private String lastInput;
public void input(String in)
{
setLastInput(in);
processData(in);
}
private synchronized String getLastInput()
{
return lastInput;
}
private synchronized void setLastInput(String lastInput)
{
this.lastInput = lastInput;
}
private int characterCount;
public synchronized int getCharacterCount()
{
return characterCount;
}
private synchronized void setCharacterCount(int characterCount)
{
this.characterCount = characterCount;
}
private boolean ignoreSpaces;
public synchronized boolean isIgnoreSpaces()
{
return ignoreSpaces;
}
public synchronized void setIgnoreSpaces(boolean ignoreSpaces)
{
boolean old=isIgnoreSpaces();
this.ignoreSpaces = ignoreSpaces;
send("ignoreSpaces",old,isIgnoreSpaces());
}
@Override
protected CubbyHole createCubbyHole()
{
return new SimpleBufferingCubbyHole();
}
@Override
protected void doWork(Object ref) throws InterruptedException
{
int old=getCharacterCount();
java.lang.String data=getLastInput();
int cc=0;
if(isIgnoreSpaces())
{
//Algorithmus-Implementierung ohne Leerzeichen hier
}
else
{
cc=data.length();
}
setCharacterCount(cc);
send("characterCount",old,getCharacterCount());
}
}