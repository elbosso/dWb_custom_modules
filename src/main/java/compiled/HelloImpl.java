/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import java.net.UnknownHostException;
import java.net.Inet4Address;
/**
*
* @author elbosso
*/
public class HelloImpl implements Hello
{
public String hello()
{
String rv="Hi there! - dont know where I am! ";
try
{
rv="Hi there! - from "+
Inet4Address.getLocalHost().getHostName()+
" ";
}
catch (UnknownHostException ex)
{
}
return rv+Thread.currentThread().getName()+" "+toString();
}
}