/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compiled;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JToggleButton;
public class IgnoreSpaceToggle extends JToggleButton implements
PropertyChangeListener,
ActionListener
{
private VCPCharacterCounter bean;
public IgnoreSpaceToggle(VCPCharacterCounter bean)
{
super("Ignore Spaces");
this.bean = bean;
this.setSelected(bean.isIgnoreSpaces());
bean.addPropertyChangeListener(this);
addActionListener(this);
}
public void propertyChange(PropertyChangeEvent evt)
{
this.setSelected(bean.isIgnoreSpaces());
}
public void actionPerformed(ActionEvent e)
{
bean.setIgnoreSpaces(this.isSelected());
}
}