/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import de.netsysit.dataflowframework.modules.ModuleBase;
public class Adder extends ModuleBase
{
Number[] numbers;
public Adder()
{
super();
numbers=new Number[0];
}
public void input (Number in, String spec)
{
java.lang.String remainder=spec.substring("input".length());
int i=remainder.length();
if(i>0)
i=java.lang.Integer.parseInt(remainder);
while(i>=numbers.length)
{
Number[] nt=new Number[i+1];
System.arraycopy(numbers, 0, nt, 0, numbers.length);
numbers=nt;
}
numbers[i]=in;
process();
}
private double processed;
public double getProcessed()
{
return processed;
}
private void process()
{
double old=getProcessed();
double t=0.0;
for (Number number : numbers)
{
if(number!=null)
{
t+=number.doubleValue();
}
}
processed=t;
send("processed", old, getProcessed());
}
}	
