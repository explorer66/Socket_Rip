package ripsocket;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Router r1 = new Router(1);
		Router r2 = new Router(2);
		Router r3 = new Router(3);
		Router r4 = new Router(4);
		Router r5 = new Router(5);
		
		r1.near_routers.add(r1);
		r1.table.add(new Route_item(1,1,0));
		
		r2.near_routers.add(r1);
		r2.near_routers.add(r3);
		r2.near_routers.add(r4);
		r2.table.add(new Route_item(1,1,0));
		r2.table.add(new Route_item(2,1,0));
		
		r3.near_routers.add(r4);
		r3.near_routers.add(r2);
		r3.table.add(new Route_item(2,1,0));
		r3.table.add(new Route_item(5,1,0));
		
		r4.near_routers.add(r2);
		r4.near_routers.add(r3);
		r4.table.add(new Route_item(2,1,0));
		r4.table.add(new Route_item(4,1,0));
		
		r5.near_routers.add(r4);
		r5.table.add(new Route_item(3,1,0));
		r5.table.add(new Route_item(4,1,0));
	
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
	}

}
