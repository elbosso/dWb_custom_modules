package compiled;

import de.netsysit.dataflowframework.modules.ModuleBase;
import javax.management.AttributeChangeNotification;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationEmitter;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;

public class JMXBean extends ModuleBase implements JMXBeanMXBean
		, NotificationEmitter
{
	private double threshold;
	public double getThreshold()
	{
		return threshold;
	}
	public void setThreshold(double threshold)
	{
		double old = getThreshold();
		this.threshold = threshold;
		send("threshold", old, getThreshold());
	}
	private long sequenceNumber = 1;
	private NotificationBroadcasterSupport notificationBroadcasterSupport;
	public JMXBean()
	{
		super();
		String[] types = new String[]
				{
						javax.management.AttributeChangeNotification.ATTRIBUTE_CHANGE
				};
		String name = AttributeChangeNotification.class.getName();
		String description = "Threshold violated";
		MBeanNotificationInfo info = new MBeanNotificationInfo(types,
				name, description);
		notificationBroadcasterSupport
				= new NotificationBroadcasterSupport(new MBeanNotificationInfo[]
				{
						info
				});
	}
	public void removeNotificationListener(NotificationListener listener,
										   NotificationFilter filter, Object handback) throws ListenerNotFoundException
	{
		notificationBroadcasterSupport.removeNotificationListener(listener,
				filter, handback);
	}
	public void addNotificationListener(NotificationListener listener,
										NotificationFilter filter, Object handback) throws IllegalArgumentException
	{
		notificationBroadcasterSupport.addNotificationListener(listener,
				filter, handback);
	}
	public void removeNotificationListener(NotificationListener listener)
			throws ListenerNotFoundException
	{
		notificationBroadcasterSupport.removeNotificationListener(listener);
	}
	public MBeanNotificationInfo[] getNotificationInfo()
	{
		return notificationBroadcasterSupport.getNotificationInfo();
	}
	private boolean state;
	public boolean isState()
	{
		return state;
	}
	public void input(Number in)
	{
		boolean old = isState();
		state = in.doubleValue() < threshold;
		send("state", old, isState());
		if (state)
		{
			Notification n = new AttributeChangeNotification(this,
					sequenceNumber++, System.currentTimeMillis(), "State changed",
					"State", "boolean", old, isState());
			notificationBroadcasterSupport.sendNotification(n);
		}
	}
}