import de.netsysit.dataflowframework.logic.services.workspace.WorkspaceExporter.Support;
import de.netsysit.dataflowframework.ui.LinkDescription;
import de.netsysit.dataflowframework.ui.ModuleWidgetDescription;
import de.netsysit.dataflowframework.ui.WorkspaceDescription;
import java.io.OutputStream;
import de.netsysit.dataflowframework.logic.services.workspace.WorkspaceExporter;
		
public class DemoWorkspaceExporter extends java.lang.Object implements
	WorkspaceExporter
{

	public DemoWorkspaceExporter()
	{
		super();
	}

	public boolean save(Support support, 
		WorkspaceDescription[] wda, OutputStream os)
	{
		boolean rv=false;
		java.io.PrintWriter pw=null;
		pw=new java.io.PrintWriter(os);
		if(wda!=null)
		{
			for (WorkspaceDescription workspaceDescription : wda)
			{
				ModuleWidgetDescription mwd[] =
					workspaceDescription.getModuleWidgetDescription();
				if(mwd!=null)
				{
					for (ModuleWidgetDescription moduleWidgetDescription : mwd)
					{
						pw.print(moduleWidgetDescription.getTitle());
						pw.print("\t");
						Object module=moduleWidgetDescription.getModule();
						pw.println(module.getClass().getName());
					}
				}
				LinkDescription ld[]=workspaceDescription.getLinkDescription();
				if(ld!=null)
				{
					for (LinkDescription linkDescription : ld)
					{
						pw.println(linkDescription);
					}
				}
			}
		}
		rv=true;
		if(pw!=null)
			pw.close();
		return rv;
	}

	public String getSuffix()
	{
		return "exdemo";
	}

}

