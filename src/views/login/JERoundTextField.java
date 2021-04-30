package views.login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class JERoundTextField extends JTextField{ 

	private static final long serialVersionUID = 1L;
	private int arcw=40; 
    private int arch=40; 
    private Image image=null; 
    private Icon icon; 

     
    public JERoundTextField() { 
        setOpaque(false); 
        setBorder(new EmptyBorder(0,5,0,2)); 
        setPreferredSize(new Dimension(100,20)); 
    } 

    @Override 
     protected void paintComponent(Graphics g) { 

        Graphics2D g2 = (Graphics2D) g; 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Paint oldPaint = g2.getPaint(); 
        
        
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0,0,getWidth(),getHeight(),arcw,arch); 
        g2.clip(r2d); 
        g2.setColor(Color.white);
//       g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(),0.0f, getHeight(), getBackground())); 
        g2.fillRect(0,0,getWidth(),getHeight()); 
        
        if(getImage()!=null){ 
            g2.drawImage(getImage(), 5, 3, getHeight()-1, getHeight()-1, null); 
            setBorder(new EmptyBorder(0,(int)(getHeight()*1.2),0,2)); 
        } 
        g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.BLACK,0.0f, getHeight(), Color.BLACK)); 
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), arcw, arch); 

        g2.setPaint(oldPaint); 
        super.paintComponent(g); 

    } 

    public int getArcw() { 
        return arcw; 
    } 

    public void setArcw(int arcw) { 
        this.arcw = arcw; 
    } 

    public int getArch() { 
        return arch; 
    } 

    public void setArch(int arch) { 
        this.arch = arch; 
    } 

    public Image getImage() { 
        return image; 
    } 

    public void setImage(Image image) { 
        this.image = image; 
    } 

    public Icon getIcon() { 
        return icon; 
    } 

    public void setIcon(Icon icon){ 
        this.icon=icon; 
        setImage(((ImageIcon)icon).getImage()); 
    } 

}