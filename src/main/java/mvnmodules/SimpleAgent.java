/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

/**
 *
 * @author elbosso
 */
public class SimpleAgent extends de.elbosso.dataflowframework.modules.base.multiagent.Agent<SimpleState,SimpleIndividuumConfiguration,SimplePopulationConfiguration,SimpleEnvironment>
{
	private java.awt.Point obshit;
	private double obsdist;
	private SimpleState aghit;
	private double agdist;

	public SimpleAgent(java.awt.geom.Point2D position,double initialVelocity,double lookAt,SimpleIndividuumConfiguration individuumConfiguration,SimplePopulationConfiguration populationConfiguration)
	{
		super(new SimpleState(position,initialVelocity,lookAt),individuumConfiguration,populationConfiguration);
	}
	public synchronized void updateState(final SimpleEnvironment environment, final SimpleState[] states,final int selfIndex)
	{
		de.elbosso.algorithms.geometry.Bresenham.Worker obsworker=new de.elbosso.algorithms.geometry.Bresenham.Worker()
		{
			public boolean doWork(java.awt.Point point)
			{
				boolean stopIt=environment.isBlocked(point.x,point.y);
				if(stopIt==true)
				{
					obsdist=point.distance(state.getPosition());
					obshit=point;
				}
				return stopIt;
			}
		};
		de.elbosso.algorithms.geometry.Bresenham.Worker agworker=new de.elbosso.algorithms.geometry.Bresenham.Worker()
		{
			public boolean doWork(java.awt.Point point)
			{
				boolean stopIt=false;
				for(int i=0;i<states.length;++i)
				{
					if(i!=selfIndex)
					{
						if(((int)states[i].getPosition().getX()==point.x)&&((int)states[i].getPosition().getY()==point.y))
						{
							agdist=states[i].getPosition().distance(state.getPosition());
							aghit=states[i];
							stopIt=true;
							break;
						}
					}
				}
				return stopIt;
			}
		};
		double len=java.lang.Math.sqrt(environment.getDimension().width*environment.getDimension().width+environment.getDimension().height*environment.getDimension().height);
		de.elbosso.algorithms.geometry.Bresenham bresenham=new de.elbosso.algorithms.geometry.Bresenham(1,obsworker);
		double mindist=java.lang.Double.MAX_VALUE;
		double la=state.getLookAt()-populationConfiguration.getAdditionalSensorCount()/2*populationConfiguration.getSensorSpacing();
		double directionOfNearestObstacle=state.getLookAt();
		for(int i=0;i<=populationConfiguration.getAdditionalSensorCount();++i)
		{
			double cosla=java.lang.Math.cos(la);
			double sinla=java.lang.Math.sin(la);
			obshit=null;
			java.awt.geom.Point2D target=new java.awt.geom.Point2D.Double(state.getPosition().getX()+len*cosla,state.getPosition().getY()+len*sinla);
			bresenham.perform(state.getPosition(),target);
			if(obshit!=null)
			{
				if(mindist>obsdist)
				{
					mindist=obsdist;
					directionOfNearestObstacle=la;
				}
			}
			la+=populationConfiguration.getSensorSpacing();
		}
		la=state.getLookAt()-populationConfiguration.getAdditionalSensorCount()/2*populationConfiguration.getSensorSpacing();
		bresenham=new de.elbosso.algorithms.geometry.Bresenham(1,agworker);
		double directionOfNearestAgent=state.getLookAt();
		double minagdist=java.lang.Double.MAX_VALUE;
//		for(int i=0;i<=additionalSensorCount;++i)
//		{
//			double cosla=java.lang.Math.cos(la);
//			double sinla=java.lang.Math.sin(la);
//			aghit=null;
//			java.awt.geom.Point2D target=new java.awt.geom.Point2D.Double(state.getPosition().getX()+len*cosla,state.getPosition().getY()+len*sinla);
//			bresenham.perform(state.getPosition(),target);
//			if(aghit!=null)
//			{
//				if(minagdist>agdist)
//				{
//					minagdist=agdist;
//					directionOfNearestAgent=la;
//				}
//			}
//			la+=sensorSpacing;
//		}
		double start=-state.getLookAt()-0.5;
		java.awt.geom.Arc2D arc=new java.awt.geom.Arc2D.Double();
		arc.setArcByCenter(state.getPosition().getX(),state.getPosition().getY(),len, start/(2*java.lang.Math.PI)*360.0, 2.0/(2*java.lang.Math.PI)*360.0,java.awt.geom.Arc2D.PIE );
		for(int i=0;i<states.length;++i)
		{
			if(i!=selfIndex)
			{
				if(arc.contains(states[i].getPosition()))
				{
					agdist=states[i].getPosition().distance(state.getPosition());
					if(minagdist>agdist)
					{
						minagdist=agdist;
						aghit=states[i];
						directionOfNearestAgent=java.lang.Math.atan2(states[i].getPosition().getY()-state.getPosition().getY(),states[i].getPosition().getX()-state.getPosition().getX());
					}
				}
			}
		}
		if((aghit!=null)&&(minagdist<populationConfiguration.getMinInterestingDistance()))
		{
			state.setLookAt(state.getLookAt()-(state.getLookAt()-directionOfNearestAgent)*populationConfiguration.getAttraction());
			if(minagdist>6)
				state.setV(state.getV()+individuumConfiguration.getDvAccel());
			else if(minagdist<3)
				state.setV(state.getV()-individuumConfiguration.getDvBrake());
			state.setTurndirection(0);
		}
		else
		{
//		if(obshit!=null)
//		{
//			if(mindist<populationConfiguration.getMinObstacleDist())
//			{
//				state.setV(state.getV()-individuumConfiguration.getDvBrake());
//				int td=state.getTurndirection();
//				if(td==0)
//				{
//					if(directionOfNearestObstacle<state.getLookAt())
//						td=1;
//					else
//						td=-1;
//				}
//				state.setLookAt(directionOfNearestObstacle+(double)td*populationConfiguration.getTurnAtObstacle());
//				state.setTurndirection(td);
//			}
//			else
//			{
//				state.setTurndirection(0);
////			if(mindist>6)
//				state.setV(state.getV()+individuumConfiguration.getDvAccel());
////			else if((aghit!=null)&&(minagdist<populationConfiguration.getMinInterestingDistance()))
////			{
////				state.setLookAt(state.getLookAt()-(state.getLookAt()-directionOfNearestAgent)*populationConfiguration.getAttraction());
////			}
//			}
//		}
//		else	
		{
			state.setTurndirection(0);
				state.setV(state.getV()+individuumConfiguration.getDvAccel());
		}
		}
//		else if((aghit!=null)&&(minagdist<populationConfiguration.getMinInterestingDistance()))
//		{
//			state.setLookAt(state.getLookAt()-(state.getLookAt()-directionOfNearestAgent)*populationConfiguration.getAttraction());
//		}
		double cosla=java.lang.Math.cos(state.getLookAt());
		double sinla=java.lang.Math.sin(state.getLookAt());
		double dsx=state.getV()*cosla;
		double dsy=state.getV()*sinla;
		double newposx=state.getPosition().getX()+dsx;
		double newposy=state.getPosition().getY()+dsy;
		if(state.getV()<0.01)
			state.setV(0.01);
		else if(state.getV()>0.3)
			state.setV(0.3);
		if(newposx>environment.getDimension().width)
			newposx-=environment.getDimension().width;
		if(newposx<0)
			newposx+=environment.getDimension().width;
		if(newposy>environment.getDimension().height)
			newposy-=environment.getDimension().height;
		if(newposy<0)
			newposy+=environment.getDimension().height;
/*		while(environment.isBlocked((int)newposx,(int)newposy))
		{
			state.setLookAt(state.getLookAt()+turnAtObstacle);
			dsx=state.getV()*java.lang.Math.cos(state.getLookAt());
			dsy=state.getV()*java.lang.Math.sin(state.getLookAt());
			newposx=state.getPosition().getX()+dsx;
			newposy=state.getPosition().getY()+dsy;
		}
*/		state.setPosition(new java.awt.geom.Point2D.Double(newposx, newposy));
	}
	public synchronized SimpleState getStateCopy() throws java.lang.CloneNotSupportedException
	{
		SimpleState copy=(SimpleState)((SimpleState)state).clone();
		return copy;
	}

}
