/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import de.netsysit.dataflowframework.logic.Generic;
import de.netsysit.dataflowframework.modules.ModuleBase;
public class Generics extends ModuleBase implements
Generic
{
private int counter;
private Object lastInput;
public void input(Object in)
{
lastInput=in;
++counter;
process(in);
}
private void process(Object in)
{
if(counter%2==0)
{
Object old=getProcessed();
send("processed", old, getProcessed());
}
}
private Object processed;
public Object getProcessed()
{
return processed;
}
}