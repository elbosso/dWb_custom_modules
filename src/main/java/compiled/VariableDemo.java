/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

 import de.netsysit.dataflowframework.logic.ConnectionEndPointDescription;
 import de.netsysit.dataflowframework.logic.ConnectionEndPointDescriptionCollections;
 import de.netsysit.dataflowframework.modules.ModuleBase;
 import de.netsysit.dataflowframework.ui.variable.VariableBean;
 import java.io.File;
public class VariableDemo extends ModuleBase implements VariableBean
{
private File definition;
public File getDefinition()
{
return definition;
}
public void setDefinition(File definition)
{
this.definition = definition;
reReadDocument();
}
private ConnectionEndPointDescriptionCollections inAndOuts;
private void reReadDocument()
{
ConnectionEndPointDescriptionCollections old=getInAndOuts();
inAndOuts=null;
java.util.Properties props=new java.util.Properties();
java.io.FileInputStream fis=null;
try
{
fis=new java.io.FileInputStream(definition);
props.load(fis);
java.util.List<ConnectionEndPointDescription> l=
new java.util.LinkedList();
for (java.lang.String key : props.stringPropertyNames())
{
ConnectionEndPointDescription cepd=
new ConnectionEndPointDescription();
cepd.setPortName(key);
cepd.setTypename(props.getProperty(key));
l.add(cepd);
}
inAndOuts=new ConnectionEndPointDescriptionCollections(null, l);
send("inAndOuts",old,getInAndOuts());
fis.close();
}
catch(java.io.IOException exp){}
}
public ConnectionEndPointDescriptionCollections getInAndOuts()
{
return inAndOuts;
}
public void genericOperation(Object input, String name)
{
}
}