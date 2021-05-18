package ripsocket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class GUI extends JFrame {
    public static Router r1 = new Router(1);
	public static Router r2 = new Router(2);
	public static Router r3 = new Router(3);
	public static Router r4 = new Router(4);
	public static Router r5 = new Router(5);
	
	public static JFrame frame = new JFrame("rip模拟");
	public static JPanel panel = new JPanel(); 
	public static JLabel Label1 = new JLabel("选择崩溃网络号");
	public static JComboBox cmb=new JComboBox();    //创建JComboBox
	public static JButton collapse = new JButton("确定");
	public static JLabel img=new JLabel(new ImageIcon("src/ripsocket/net.png"));
	public static JButton start = new JButton("开始模拟");
	public static JTextArea info[]= {new JTextArea(),new JTextArea(),new JTextArea(),new JTextArea(),new JTextArea()};
	public static JLabel title[] =  {new JLabel("路由器1号"),new JLabel("路由器2号"),new JLabel("路由器3号"),
			new JLabel("路由器4号"),new JLabel("路由器5号")};
	public static JTextArea all_info = new JTextArea();
	public static JComboBox<String> sr=new JComboBox<String>();
	public static JButton select = new JButton("确定");
	
	public static void restart() {
		r1.near_routers.clear();
		r1.table.clear();
		r2.near_routers.clear();
		r2.table.clear();
		r3.near_routers.clear();
		r3.table.clear();
		r4.near_routers.clear();
		r4.table.clear();
		r5.near_routers.clear();
		r5.table.clear();
				
		for(int i=0;i<5;i++)
			info[i].setText("");
		all_info.setText("");
		
		r1.near_routers.add(r2);
		r1.table.add(new Route_item(1,1,0));
		
		r2.near_routers.add(r1);
		r2.near_routers.add(r3);
		r2.near_routers.add(r4);
		r2.table.add(new Route_item(1,1,0));
		r2.table.add(new Route_item(2,1,0));
		
		r3.near_routers.add(r2);
		r3.near_routers.add(r4);
		r3.table.add(new Route_item(2,1,0));
		r3.table.add(new Route_item(5,1,0));
		
		r4.near_routers.add(r2);
		r4.near_routers.add(r3);
		r4.near_routers.add(r5);
		r4.table.add(new Route_item(2,1,0));
		r4.table.add(new Route_item(4,1,0));
		
		r5.near_routers.add(r4);
		r5.table.add(new Route_item(3,1,0));
		r5.table.add(new Route_item(4,1,0));
	}
	public static void loadgui() {
        // Setting the width and height of frame
        frame.setSize(1250, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */   
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        panel.setLayout(null);

        // 创建 JLabel
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        Label1.setBounds(10,20,100,25);
        panel.add(Label1);

        
        cmb.setBounds(120,20,80,25);
        cmb.addItem("网络1");    //向下拉列表中添加一项
        cmb.addItem("网络2");
        cmb.addItem("网络3");
        cmb.addItem("网络4");
        cmb.addItem("网络5");
        panel.add(cmb);
        

        collapse.setBounds(210, 20,80,25);
        panel.add(collapse);
    
        start.setBounds(500,20,200,25);
        panel.add(start);
        
        img.setBounds(10,60,900,300);
        panel.add(img);
        

        
        title[0].setBounds(10,380,100,25);
        JScrollPane jsp1=new JScrollPane(info[0]); 
        jsp1.setBounds(10,410,400,200);
        panel.add(title[0]);
        panel.add(jsp1);
 
        title[1].setBounds(420,380,100,25);
        JScrollPane jsp2=new JScrollPane(info[1]); 
        jsp2.setBounds(420,410,400,200);
        panel.add(title[1]);
        panel.add(jsp2);
        
        title[2].setBounds(830,380,100,25);
        JScrollPane jsp3=new JScrollPane(info[2]); 
        jsp3.setBounds(830,410,400,200);
        panel.add(title[2]);
        panel.add(jsp3);
        
        title[3].setBounds(10,620,100,25);
        JScrollPane jsp4=new JScrollPane(info[3]); 
        jsp4.setBounds(10,650,400,200);
        panel.add(jsp4);
        panel.add(title[3]);
        
        
        title[4].setBounds(420,620,100,25);
        JScrollPane jsp5=new JScrollPane(info[4]);
        jsp5.setBounds(420,650,400,200);
        panel.add(jsp5);
        panel.add(title[4]);
       
        JLabel gl = new JLabel("路由表");
        gl.setBounds(830,620,80,25);
        panel.add(gl);
        
        sr.setBounds(920,620,80,25);
        sr.addItem("路由器1");    //向下拉列表中添加一项
        sr.addItem("路由器2");
        sr.addItem("路由器3");
        sr.addItem("路由器4");
        sr.addItem("路由器5");
        panel.add(sr);
        
        
        select.setBounds(1010,620,80,25);
        panel.add(select);
        
       all_info.setBounds(830, 650, 400, 200);
       all_info.setLineWrap(true);
       all_info.append("目的网络\t距离\t下一跳路由器\n");
       panel.add(all_info);
        
        frame.setVisible(true);
        
        start.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	restart();
            	r1.listen();
        		r1.exchangetable();
        				
        		r2.listen();
        		r2.exchangetable();
        		
        		r3.listen();
        		r3.exchangetable();
        		
        		r4.listen();
        		r4.exchangetable();

        		r5.listen();
        		r5.exchangetable();
            }});
        
        collapse.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	String net = (String)cmb.getSelectedItem();
            	switch(net) {
            	case "网络1":
            		r1.fault(2);
            		r2.fault(1);
            		break;
            	case "网络2":
            		r2.fault(3);
            		r2.fault(4);
            		r3.fault(2);
            		r3.fault(4);
            		r4.fault(2);
            		r4.fault(3);
            		break;
            	case "网络4":
            		r4.fault(5);
            		r5.fault(4);
            		break;
            	}
            	
            }});
        
        select.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String router = (String)sr.getSelectedItem();
        		all_info.setText(" ");
        		all_info.append("目的网络\t距离\t下一跳路由器\n");
        		switch(router) {
        		case "路由器1":
        			for(int i=0;i<r1.table.size();i++) {
        				all_info.append("    "+r1.table.get(i).targetnet+"          \t"+r1.table.get(i).dis+"          \t"+r1.table.get(i).nextrouter+"\n");
        			}
        		break;
        		case "路由器2":
        			for(int i=0;i<r2.table.size();i++) {
        				all_info.append("    "+r2.table.get(i).targetnet+"          \t"+r2.table.get(i).dis+"          \t"+r2.table.get(i).nextrouter+"\n");
        			}
        		break;
        		case "路由器3":
        			for(int i=0;i<r3.table.size();i++) {
        				all_info.append("    "+r3.table.get(i).targetnet+"          \t"+r3.table.get(i).dis+"          \t"+r3.table.get(i).nextrouter+"\n");
        			}
        		break;
        		case "路由器4":
        			for(int i=0;i<r4.table.size();i++) {
        				all_info.append("    "+r4.table.get(i).targetnet+"          \t"+r4.table.get(i).dis+"          \t"+r4.table.get(i).nextrouter+"\n");
        			}
        		break;
        		case "路由器5":
        			for(int i=0;i<r5.table.size();i++) {
        				all_info.append("    "+r5.table.get(i).targetnet+"          \t"+r5.table.get(i).dis+"          \t"+r5.table.get(i).nextrouter+"\n");
        			}
        		break;
        		}
        	}
        	}
        );
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadgui();
		
		r1.near_routers.add(r2);
		r1.table.add(new Route_item(1,1,0));
		
		r2.near_routers.add(r1);
		r2.near_routers.add(r3);
		r2.near_routers.add(r4);
		r2.table.add(new Route_item(1,1,0));
		r2.table.add(new Route_item(2,1,0));
		
		r3.near_routers.add(r2);
		r3.near_routers.add(r4);
		r3.table.add(new Route_item(2,1,0));
		r3.table.add(new Route_item(5,1,0));
		
		r4.near_routers.add(r2);
		r4.near_routers.add(r3);
		r4.near_routers.add(r5);
		r4.table.add(new Route_item(2,1,0));
		r4.table.add(new Route_item(4,1,0));
		
		r5.near_routers.add(r4);
		r5.table.add(new Route_item(3,1,0));
		r5.table.add(new Route_item(4,1,0));
		

	    
	}
	

}
