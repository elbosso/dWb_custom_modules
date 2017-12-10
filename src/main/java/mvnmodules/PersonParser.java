/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

import com.example.tutorial.AddressBookProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elbosso
 */
public class PersonParser extends de.netsysit.dataflowframework.modules.ModuleBase
{

	private AddressBookProtos.Person result;
	public void trigger(byte[] in)
	{
		AddressBookProtos.Person old=getResult();
		result=null;
		if(in!=null)
		{
			try
			{
				result=AddressBookProtos.Person.parseFrom(in);
			}
			catch (InvalidProtocolBufferException ex)
			{
				Logger.getLogger(PersonParser.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		send("result",old,getResult());
	}

	public AddressBookProtos.Person getResult()
	{
		return result;
	}
	
}
