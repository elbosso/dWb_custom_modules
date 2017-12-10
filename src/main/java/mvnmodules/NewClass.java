/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

import com.example.tutorial.AddressBookProtos;

/**
 *
 * @author elbosso
 */
public class NewClass extends de.netsysit.dataflowframework.modules.ModuleBase
{

	private AddressBookProtos.Person result;
	public void trigger(Number in)
	{
		AddressBookProtos.Person old=getResult();
		result=null;
		if(in!=null)
		{
			result=AddressBookProtos.Person.newBuilder().setId(in.intValue()).setName(in.toString()).buildPartial();
		}
		send("result",old,getResult());
	}

	public AddressBookProtos.Person getResult()
	{
		return result;
	}
	
}
