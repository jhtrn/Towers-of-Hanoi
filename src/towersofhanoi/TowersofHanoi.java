/*
* Name:           Justine Tran
* Project:        Towers of Hanoi
* Date:           December 15, 2016
*
* Purpose: The purpose of this program is to create an application which 
*          displays all the movements for the Towers of Hanoi game.
*/
package towersofhanoi;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class TowersofHanoi extends Applet implements ActionListener{
    
    ArrayList<disk> pegOne = new ArrayList<>();
    ArrayList<disk> pegTwo = new ArrayList<>();
    ArrayList<disk> pegThr = new ArrayList<>();
    int numD = 0;
    int movFr = 0,movTo = 0;
    Label lblEn = new Label("Enter the number of disks(<=7): ");
    TextField txtEn = new TextField();
    Button presBtn = new Button("Press to start display"),
            inBtn = new Button("Enter number of disks");
            
    public void init()
    {
        setLayout(null);
        resize(960,300);
        
        lblEn.setBounds(10,20,180,20);
        add(lblEn);
        
        txtEn.setBounds(200,20,40,20);
        add(txtEn);
        
        presBtn.setBounds(500,20,150,20);
        add(presBtn); 
        presBtn.addActionListener(this);
        
        inBtn.setBounds(280,20,150,20);
        add(inBtn); 
        inBtn.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==inBtn)
        {
            String numDisk = txtEn.getText();
            numD = Integer.parseInt(numDisk);
            repaint();
        }
        
        if(e.getSource()==presBtn)
        {
            towerOfHanoi(numD,1,3,2);
            //Clear array lists for new input
            pegOne.clear();
            pegTwo.clear();
            pegThr.clear(); 
        }
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        //Draw Base and Pegs
        drawBasePegs(g);
        //Draw Disks based on input and put them into array list
        initDisk(g);  
    }
    public void drawBasePegs(Graphics g)
    {
        //Draw base
        g.setColor(Color.gray);
        g.drawRect(30,280,890,10);
        g.fillRect(30,280,890,10);
        //Draw peg1
        g.setColor(Color.gray);
        g.drawRect(180,90,15,200);
        g.fillRect(180,90,15,200);
        //Draw peg2
        g.setColor(Color.gray);
        g.drawRect(471,90,15,200);
        g.fillRect(471,90,15,200);
        //Draw peg3
        g.setColor(Color.gray);
        g.drawRect(760,90,15,200);
        g.fillRect(760,90,15,200);
    }
    public void initDisk(Graphics g)
    {
        int x = 45,y = 250,width = 275,height = 15;
        for(int i=1; i<=numD; i++)
        {
            disk diskL = new disk();
            diskL.setX(x);
            diskL.setY(y);
            diskL.setW(width);
            diskL.setH(height);
            //Add dimensions of certain disk size to arrayList
            pegOne.add(diskL);
            g.setColor(Color.black);
            g.drawRoundRect(x,y,width,height,5,5);
            g.fillRoundRect(x,y,width,height,5,5);

            x+=20; y-=25;
            width-=38;  
        }
    }
    public void reDraw(Graphics g)
    {   
        disk diskL = new disk();
        //Clear frame
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        
        drawBasePegs(g);
        
        //Swap disk from peg 1 to peg 2
        if(movFr == 1 && movTo == 2)
        {
            diskL.setX(pegOne.get(pegOne.size()-1).getX());
            diskL.setY(pegOne.get(pegOne.size()-1).getY());
            diskL.setW(pegOne.get(pegOne.size()-1).getW());
            diskL.setH(pegOne.get(pegOne.size()-1).getH());
            pegTwo.add(diskL);
            //Remove disk from peg 1
            pegOne.remove(pegOne.size()-1);      
        }
        //Swap disk from peg 1 to peg 3
        else if(movFr == 1 && movTo == 3)
        {
            diskL.setX(pegOne.get(pegOne.size()-1).getX());
            diskL.setY(pegOne.get(pegOne.size()-1).getY());
            diskL.setW(pegOne.get(pegOne.size()-1).getW());
            diskL.setH(pegOne.get(pegOne.size()-1).getH());
            pegThr.add(diskL);
            //Remove disk from peg 3
            pegOne.remove(pegOne.size()-1);         
        }
         //Swap disk from peg 2 to peg 1
        else if(movFr == 2 && movTo == 1)
        {
            diskL.setX(pegTwo.get(pegTwo.size()-1).getX());
            diskL.setY(pegTwo.get(pegTwo.size()-1).getY());
            diskL.setW(pegTwo.get(pegTwo.size()-1).getW());
            diskL.setH(pegTwo.get(pegTwo.size()-1).getH());
            pegOne.add(diskL);
            //Remove disk from peg 2
            pegTwo.remove(pegTwo.size()-1);         
        }
        //Swap disk from peg 2 to peg 3
        else if(movFr == 2 && movTo == 3)
        {
            diskL.setX(pegTwo.get(pegTwo.size()-1).getX());
            diskL.setY(pegTwo.get(pegTwo.size()-1).getY());
            diskL.setW(pegTwo.get(pegTwo.size()-1).getW());
            diskL.setH(pegTwo.get(pegTwo.size()-1).getH());
            pegThr.add(diskL);
            //Remove disk from peg 2
            pegTwo.remove(pegTwo.size()-1);         
        }
        //Swap disk from peg 3 to peg 1
        else if(movFr == 3 && movTo == 1)
        {
            diskL.setX(pegThr.get(pegThr.size()-1).getX());
            diskL.setY(pegThr.get(pegThr.size()-1).getY());
            diskL.setW(pegThr.get(pegThr.size()-1).getW());
            diskL.setH(pegThr.get(pegThr.size()-1).getH());
            pegOne.add(diskL);
            //Remove disk from peg 1
            pegThr.remove(pegThr.size()-1);         
        }
        //Swap disk from peg 3 to peg 2
        else if(movFr == 3 && movTo == 2)
        {
            diskL.setX(pegThr.get(pegThr.size()-1).getX());
            diskL.setY(pegThr.get(pegThr.size()-1).getY());
            diskL.setW(pegThr.get(pegThr.size()-1).getW());
            diskL.setH(pegThr.get(pegThr.size()-1).getH());
            pegTwo.add(diskL);
            //Remove disk from peg 3
            pegThr.remove(pegThr.size()-1);         
        }
        //Draw disks on peg 1
        for(int i = 0; i < pegOne.size(); i++)
        {
            g.setColor(Color.black);
            g.drawRoundRect(pegOne.get(i).getX(),250-(i*25),
                    pegOne.get(i).getW(),pegOne.get(i).getH(),5,5);
            g.fillRoundRect(pegOne.get(i).getX(),250-(i*25),
                    pegOne.get(i).getW(),pegOne.get(i).getH(),5,5);
        }
        //Draw disks on peg 2
        for(int i = 0; i < pegTwo.size(); i++)
        {
            g.setColor(Color.black);
            g.drawRoundRect(pegTwo.get(i).getX()+290,250-(i*25),
                    pegTwo.get(i).getW(),pegTwo.get(i).getH(),5,5);
            g.fillRoundRect(pegTwo.get(i).getX()+290,250-(i*25),
                    pegTwo.get(i).getW(),pegTwo.get(i).getH(),5,5);
        }
        //Draw disks on peg 3
        for(int i = 0; i < pegThr.size(); i++)
        {
            g.setColor(Color.black);
            g.drawRoundRect(pegThr.get(i).getX()+580,250-(i*25),
                    pegThr.get(i).getW(),pegThr.get(i).getH(),5,5);
            g.fillRoundRect(pegThr.get(i).getX()+580,250-(i*25),
                    pegThr.get(i).getW(),pegThr.get(i).getH(),5,5);
        }
    }
    public void towerOfHanoi(int N, int from, int to, int temp)
    {
        if(N==1) moveTo(from,to);
        else
        {
            towerOfHanoi(N-1,from,temp,to);
            moveTo(from,to);
            towerOfHanoi(N-1,temp,to,from);
        }
    }
    public void moveTo(int from, int to)
    {
        System.out.println(from+"->"+to);
        movFr = from;
        movTo = to;

        //timer.start();
        //empty delay loop
        long delay = System.currentTimeMillis();
        while(System.currentTimeMillis()< delay+300){}
        reDraw(this.getGraphics());
    }
}

class disk
{
    private int x,y,width,height;
    
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setW(int width){this.width=width;}
    public void setH(int height){this.height=height;}
    
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public int getW(){return this.width;}
    public int getH(){return this.height;}
}