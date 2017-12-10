/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import de.netsysit.dataflowframework.modules.ModuleBase;
public class CharacterCounter extends ModuleBase
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
}