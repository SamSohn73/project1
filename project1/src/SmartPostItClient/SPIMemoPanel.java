package SmartPostItClient;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JEditorPane;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;


/**
 * SmartPostIt 메모장 화면. rtf 문서 형식을 이용.
 * 
 * @author sam
 * @Version 0.1
 */
class SPIMemoPanel extends JPanel implements SPIPanel
{
	private static final long serialVersionUID = 8552143505046031394L;

	private final transient static Logger log = Logger.getLogger(SPIMemoPanel.class);
	private JEditorPane editorPane;
	private SPIMemoPopup popup;
	
	
	JEditorPane getEditorPane()
	{
		return editorPane;
	}
	void setEditorPane(JEditorPane editorPane)
	{
		this.editorPane = editorPane;
	}

	public SPIMemoPopup getPopup()
	{
		return popup;
	}
	public void setPopup(SPIMemoPopup popup)
	{
		this.popup = popup;
	}
	
	
	/**
	 * Create the panel.
	 */
	public SPIMemoPanel(SPIMemoPopup popup)
	{
		super();
		setSize(250, 250);
		setLocation(100, 100);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		editorPane = new JEditorPane("text/rtf", "");
		editorPane.setBackground(SPIUtil.YELLOW);
		editorPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(editorPane, BorderLayout.CENTER);
		
		this.popup = popup;
		
		if (popup != null) {		addPopup(editorPane, popup);
		} else {					log.fatal("popup object = null");}
	}
	
	
	private static void addPopup(Component component, final SPIMemoPopup popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
