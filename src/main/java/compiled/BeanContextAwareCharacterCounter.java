/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;


import de.netsysit.dataflowframework.modules.BeanContextChildModuleBase;
import de.netsysit.util.beans.context.service.Notification;
import java.beans.PropertyVetoException;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextServices;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
/**
*
* @author elbosso
*/
public class BeanContextAwareCharacterCounter extends
BeanContextChildModuleBase
{
private Notification notificationService;
private String lastInput;
public void input(String in)
{
lastInput=in;
if(lastInput!=null)
countCharacters();
else
{
if(notificationService!=null)
notificationService.notifyInfo(
this.getClass().getSimpleName(), "NULL input empfangen!");
}
}
private int characterCount;
public int getCharacterCount()
{
return characterCount;
}
private boolean ignoreSpaces;
public boolean isIgnoreSpaces()
{
return ignoreSpaces;
}
public void setIgnoreSpaces(boolean ignoreSpaces)
{
boolean old=isIgnoreSpaces();
this.ignoreSpaces = ignoreSpaces;
send("ignoreSpaces",old,isIgnoreSpaces());
}
private void countCharacters()
{
int old=getCharacterCount();
//Algorithmus-Implementierung hier
send("characterCount",old,getCharacterCount());
}
@Override
public void serviceAvailable(BeanContextServiceAvailableEvent bcsae)
{
super.serviceAvailable(bcsae);
if(notificationService==null)
{
if(bcsae.getServiceClass()==
de.netsysit.util.beans.context.service.Notification.class)
try
{
notificationService =
(Notification)(bcsae.getSourceAsBeanContextServices()).getService(
this, this,Notification.class, null, this);
}
catch(Exception e)
{
notificationService=null;
}
}
}
@Override
public void serviceRevoked(BeanContextServiceRevokedEvent bcsre)
{
super.serviceRevoked(bcsre);
if(bcsre.getServiceClass()==Notification.class)
{
notificationService=null;
}
}
@Override
public void setBeanContext(BeanContext bc) throws PropertyVetoException
{
if(notificationService!=null)
{
if(getBeanContext()!=null)
{
if(BeanContextServices.class.isAssignableFrom(
getBeanContext().getClass()))
{
BeanContextServices bcs=(BeanContextServices)getBeanContext();
bcs.releaseService(this, this, notificationService);
}
}
}
super.setBeanContext(bc);
}
}