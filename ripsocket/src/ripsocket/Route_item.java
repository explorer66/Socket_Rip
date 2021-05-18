package ripsocket;

public class Route_item implements java.io.Serializable{
	public int targetnet;//目标网络号
	public int dis;
	public int nextrouter;//下一跳路由器号
	
	public Route_item(int targetnet,int dis,int nextrouter) {
		this.targetnet = targetnet;
		this.dis = dis;
		this.nextrouter = nextrouter;

	}
}
