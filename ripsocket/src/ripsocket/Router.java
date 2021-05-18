package ripsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Router {
	  public int router_num;
	  public  List<Route_item> table = new ArrayList<Route_item>();
	  public List<Router> near_routers = new ArrayList<Router>();
	  ServerSocket s ;
	  
	  public Router(int router_num) {
		  this.router_num = router_num;
		  try {
			s = new ServerSocket(8888+router_num);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }


	 public void fault(int tnum) {
		 for(int i=0;i<table.size();i++) {
			 Route_item temp = table.get(i);
			 if(temp.nextrouter==tnum) {
				 table.set(i, new Route_item(temp.targetnet,16,temp.nextrouter));
			 }
		 }
		 for(int i=0;i<near_routers.size();i++) {
			 if(near_routers.get(i).router_num==tnum)
				near_routers.remove(i);
		 }
	 }
	  void update(int origin_router,List<Route_item> list) {
		  int flag ;
		  for(int i=0;i<list.size();i++) {		  
			  Route_item item = list.get(i);
			  item.nextrouter = origin_router;
			  if(item.dis<16)
				  item.dis++;
			  flag = 0;
			  for(int j=0;j<table.size();j++) {
				  Route_item temp = table.get(j);
				  if(item.targetnet==temp.targetnet) {
					  flag = 1;
					  if(item.nextrouter==temp.nextrouter) {
						  table.set(j,item);
						  GUI.info[router_num-1].append("更改 路由表项"+"  目标网络："+temp.targetnet
								  +"距离："+temp.dis+"下一跳路由器:"+temp.nextrouter+" 为 "+item.targetnet
								  +"距离："+item.dis+"下一跳路由器:"+item.nextrouter+"\n");
					  }else {
						  if(item.dis<temp.dis) {
							  table.set(j, item);
							  GUI.info[router_num-1].append("替换 路由表项"+"  目标网络："+temp.targetnet
									  +"距离："+temp.dis+"下一跳路由器:"+temp.nextrouter+" 为 "+item.targetnet
									  +"距离："+item.dis+"下一跳路由器:"+item.nextrouter+"\n");  
						  }
						  else {
							  GUI.info[router_num-1].append("不用 路由表项"+"  目标网络："+temp.targetnet
									  +"距离："+temp.dis+"下一跳路由器:"+temp.nextrouter+"\n");
						  }
					  }
					  break;
				  }
			  }
			  if(flag==0) {
				  table.add(item);
				  GUI.info[router_num-1].append("增加 路由表项："+"  目标网络："+item.targetnet
						  +"距离："+item.dis+"下一跳路由器:"+item.nextrouter+"\n");
			  }
		  }
	  }
	  void print(String s) {
		  System.out.println(s);
	  }

      public void listen() {
    	  new Thread(new Runnable() {
    		  public void run() {
    			   try{
    				    Socket socket = new Socket();
    					while(true) {		  					
    						socket = s.accept();
    						InputStreamReader reader = new InputStreamReader(socket.getInputStream());
    						BufferedReader br = new BufferedReader(reader);
    						int origin_router = br.read();
    						GUI.info[router_num-1].append("收到来自"+origin_router+"号路由器的路由表"+"\n");
    						List<Route_item> list = new ArrayList<Route_item>();
    						String line;
    						while((line =br.readLine())!=null) {
    							System.out.println(line);
    							String[] pars = line.split(",");
    							Route_item temp = new Route_item(Integer.parseInt(pars[0]),
    									Integer.parseInt(pars[1]),Integer.parseInt(pars[2]));
    						    list.add(temp);
    						}
    						update(origin_router,list);
    						socket.close();
    						
    					}
    		      }catch(Exception e) {
    		    	  e.printStackTrace();
    		      }
    		  }
    	  }).start();
	
	  }
	  void exchangetable() {//Socket交换路由表
		  new Thread(new Runnable() {
			  public void run() {
				  Socket socket;
				  try {
					  while(true) {
						  for(int i=0;i<near_routers.size();i++) {
										socket = new Socket("localhost",8888+near_routers.get(i).router_num);
									    PrintWriter writer = new PrintWriter(socket.getOutputStream());
									    writer.write(router_num);
									    String rip_table = "";
									    for(int j=0;j<table.size();j++) {
									        Route_item item = table.get(j);
									        rip_table = rip_table + item.targetnet+","+item.dis+","+item.nextrouter+"\n";
									    }
									    writer.write(rip_table);
									    writer.flush();
									    writer.close();			
							  }		
						  Thread.sleep(10000);
						  }
				  }catch(Exception e) {
					  e.printStackTrace();
				  }
				  
				
			  }
		  }).start();
		  

	  }
}
