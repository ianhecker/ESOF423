package net.sf.jftp.gui.base.dir;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.jftp.JFtp;
import net.sf.jftp.config.Settings;
import net.sf.jftp.gui.framework.HImageButton;


public class DirComponent extends DirPanel implements ListSelectionListener {

	public JTable table = new JTable();
	DirPanel target;
	
	public DirComponent() {
	
		table.setDefaultRenderer(Object.class, new ColoredCellRenderer());
		table.getSelectionModel().addListSelectionListener(this);
		
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
	}
	
	public void update() {
        Vector colNames = new Vector();
        colNames.add("");
        colNames.add("Name");
        colNames.add("Size");
        colNames.add("##");

        TableUtils.layoutTable(jl, table, colNames);
	}

   /**
    * This manages the selections
    */
    public void valueChanged(ListSelectionEvent e)
    {
        if(e.getValueIsAdjusting() == false)
        {
       		TableUtils.copyTableSelectionsToJList(jl, table);

            //  ui refresh bugfix
            int index = jl.getSelectedIndex() - 1;

            if((index < 0) || (dirEntry == null) || (dirEntry.length < index) ||
                   (dirEntry[index] == null))
            {
                return;
            }
            else
            { // -------------------- local --------------------------

                String tgt = (String) jl.getSelectedValue().toString();

                for(int i = 0; i < dirEntry.length; i++)
                {
                    dirEntry[i].setSelected(jl.isSelectedIndex(i + 1));
                }
            }
        }
    }
    //REFACTORED
    public FlowLayout createFlowLayout(int flowLayoutConstant) {
    	FlowLayout f = new FlowLayout(flowLayoutConstant);
    	f.setHgap(1);
        f.setVgap(2);
    	return f;
    }
    //REFACTORED
    public JPanel createJPanel(String location, HImageButton b, JPanel p) {
    	JPanel j = new JPanel();
        j.setLayout(new BorderLayout());
        j.add("Center", p);                   
        j.add(location, b);
    	return j;
    }
    //REFACTORED
    public void setButtonPanel(JToolBar b) {
    	b.setVisible(true);
        b.setSize(getSize().width - 10, 32);
    }
    //REFACTORED
    public AdjustmentListener createAdjustmentListener(JScrollPane jsp) {
    	AdjustmentListener al = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                jsp.repaint();
                jsp.revalidate();
            }
        };
        return al;
    }
    //REFACTORED
    public void configureJsp(AdjustmentListener al, JScrollPane jsp) {
    	jsp.getHorizontalScrollBar().addAdjustmentListener(al);
        jsp.getVerticalScrollBar().addAdjustmentListener(al);        
        jsp.setSize(getSize().width - 20, getSize().height - 72);        
    }
}
