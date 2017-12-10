/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import de.netsysit.dataflowframework.modules.ModuleBase;
import de.netsysit.dataflowframework.ui.VisualComponentProvider;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JPanel;
public class VCPCharacterCounter extends ModuleBase implements
VisualComponentProvider
{
private String lastInput;
public void input(String in)
{
lastInput=in;
countCharacters();
}
private int characterCount;
public int getCharacterCount()
{
return characterCount;
}
private boolean ignoreSpaces;
public boolean isIgnoreSpaces()
{
return ignoreSpaces;
}
public void setIgnoreSpaces(boolean ignoreSpaces)
{
boolean old=isIgnoreSpaces();
this.ignoreSpaces = ignoreSpaces;
send("ignoreSpaces",old,isIgnoreSpaces());
}
private void countCharacters()
{
int old=getCharacterCount();
//Algorithmus-Implementierung hier
send("characterCount",old,getCharacterCount());
}
public Container getVisualComponent()
{
IgnoreSpaceToggle ignoreSpaceToggle=new IgnoreSpaceToggle(this);
JPanel p=new JPanel(new BorderLayout());
p.add(ignoreSpaceToggle);
return p;
}
}
