
import java.util.Arrays;
public class MyOwnTester {
	
	
	public static void main(String[] args) {
		CatInfo c1= new CatInfo("Alice", 87, 55, 250,35);
		CatInfo c2= new CatInfo("Bob", 88, 60, 248,50);
		CatInfo c3= new CatInfo("Doughnut", 85, 60, 247,5);
		CatInfo c4= new CatInfo("Eleanor", 85, 45, 246,42);
		CatInfo c5= new CatInfo("Felix", 85, 60, 249,26);
		CatInfo c6= new CatInfo("Hilda", 95, 55, 244,46);
		CatInfo c7= new CatInfo("Coco", 87, 60, 249,23);
		CatInfo c8= new CatInfo("Gaia", 86, 55, 249,11);
		CatInfo c9= new CatInfo("danii", 86, 55, 253,11);
		
/*		
		CatInfo c1= new CatInfo("Alice", 87, 55, 250,35);
		CatInfo c7= new CatInfo("Coco", 87, 60, 249,23);
		
		CatInfo c2= new CatInfo("Bob", 88, 60, 248,50);
		CatInfo c3= new CatInfo("Doughnut", 85, 60, 247,5);
		CatInfo c4= new CatInfo("Eleanor", 85, 45, 246,42);
		CatInfo c5= new CatInfo("Felix", 85, 60, 249,26);
		CatInfo c6= new CatInfo("Hilda", 95, 55, 244,46);
		CatInfo c8= new CatInfo("Gaia", 86, 55, 249,11);
		CatInfo c9= new CatInfo("daniel", 85,30,0,0);
	*/		
		
		
		
		CatTree cTree= new CatTree(c1);
		cTree.addCat(c2);
		cTree.addCat(c3);
		cTree.addCat(c4);
		cTree.addCat(c5);
		cTree.addCat(c6);
		cTree.addCat(c7);
		cTree.addCat(c8);
		cTree.addCat(c9);
		

/*		cTree.removeCat(c9);
		cTree.root.removeCat(c1);
		cTree.root.removeCat(c2);
		cTree.root.removeCat(c3);
		cTree.root.removeCat(c4);
		cTree.root.removeCat(c5);
		cTree.root.removeCat(c6);
		cTree.root.removeCat(c7);
		cTree.root.removeCat(c8);	*/
		//cTree.root.removrCat();
		System.out.println(cTree.root.toString());
		
		//System.out.println(Arrays.toString(cTree.costPlanning(11)));
		//System.out.println(cTree.root.data.name);
		//System.out.println(cTree.root.same.data.name);
		//System.out.println(cTree.root.junior.data.name);
		//System.out.println(cTree.root.junior.junior.data.name);
		//System.out.println(cTree.root.senior.data.name);
/*		System.out.println(cTree.root.senior.junior.data.name);
		System.out.println(cTree.root.senior.same.data.name);
		System.out.println(cTree.root.senior.same.same.data.name);			*/
		//System.out.println(cTree.mostSenior());
		//System.out.println(cTree.fluffiest());
		//System.out.println(cTree.hiredFromMonths(85, 82));
		//System.out.println(cTree.fluffiestFromMonth(85));
		
	}
	



}
