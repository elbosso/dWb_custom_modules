/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import de.netsysit.dataflowframework.ui.beans.PopupStateUpdater;
public class NumberStateUpdaterDemo extends PopupStateUpdater
{
private JLabel last;
private JLabel current;
private Object old;
public NumberStateUpdaterDemo(JLabel label)
{
super(label);
JPanel p=new JPanel(new GridLayout(0, 2));
JLabel l=new JLabel("letztes:");
l.setOpaque(false);
p.add(l);
last=new javax.swing.JLabel("");
p.add(last);
l=new JLabel("aktuelles:");
l.setOpaque(false);
p.add(l);
current=new javax.swing.JLabel("");
p.add(current);
toplevel.add(p);
last.setOpaque(false);
current.setOpaque(false);
p.setOpaque(false);
}
protected void update(Object object)
{
last.setText(old!=null?old.toString():"--");
current.setText(object!=null?object.toString():"--");
old=object;
}
}