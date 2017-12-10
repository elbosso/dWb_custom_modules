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
public class GenericPersonParser extends de.netsysit.dataflowframework.modules.ModuleBase
{

	private AddressBookProtos.Person result;
	public void trigger(byte[] in)
	{
		AddressBookProtos.Person old=getResult();
		result=null;
		if(in!=null)
		{
			result=createResult(in);
		}
		send("result",old,getResult());
	}

	public AddressBookProtos.Person getResult()
	{
		return result;
	}

//	public void trigger(byte[] in)
//	{
//		AddressBookProtos.Person old=getResult();
//		result=null;
//		if(in!=null)
//		{
//			result=createResult(in);
//		}
//		send("result",old,getResult());
//	}
//
//	public AddressBookProtos.Person getResult()
//	{
//		return result;
//	}

	protected AddressBookProtos.Person createResult(byte[] in)
	{
		AddressBookProtos.Person rv=null;
			try
			{
				rv=AddressBookProtos.Person.parseFrom(in);
			}
			catch (InvalidProtocolBufferException ex)
			{
				Logger.getLogger(GenericPersonParser.class.getName()).log(Level.SEVERE, null, ex);
			}
			return rv;
	}
	
}
