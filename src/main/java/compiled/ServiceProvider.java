/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;


import de.netsysit.dataflowframework.modules.BeanContextChildModuleBase;
import java.beans.PropertyVetoException;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceProvider;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextServices;

import java.util.Iterator;
public class ServiceProvider extends BeanContextChildModuleBase implements
BeanContextServiceProvider
{
@Override
public void setBeanContext(BeanContext bc) throws PropertyVetoException
{
BeanContext former=getBeanContext();
super.setBeanContext(bc);
if(bc==null)
{
if(former!=null)
{
if(BeanContextServices.class.isAssignableFrom(former.getClass()))
{
((BeanContextServices)former).revokeService(
Service.class, this, true);
}
}
}
if(getBeanContext()!=null)
{
if(BeanContextServices.class.isAssignableFrom(
getBeanContext().getClass()))
{
((BeanContextServices)getBeanContext()).addService(
Service.class, this);
}
}
}
@Override
public void serviceAvailable(BeanContextServiceAvailableEvent bcsae)
{
super.serviceAvailable(bcsae);
}
@Override
public void serviceRevoked(BeanContextServiceRevokedEvent bcsre)
{
super.serviceRevoked(bcsre);
if(getBeanContext()!=null)
{
if(BeanContextServices.class.isAssignableFrom(
getBeanContext().getClass()))
{
BeanContextServices bcs=(BeanContextServices)getBeanContext();
if(bcsre.isServiceClass(Service.class))
{
try
{
bcs.addService(Service.class, this);
}
catch (Exception e)
	{
}
}
}
}
}
public Object getService(BeanContextServices bcs, Object requestor,
Class serviceClass, Object serviceSelector)
{
return new HashImpl();
}
public void releaseService(BeanContextServices bcs, Object requestor,
Object service)
{
}
public Iterator getCurrentServiceSelectors(BeanContextServices bcs,
Class serviceClass)
{
return java.util.Collections.emptyIterator();
}
}